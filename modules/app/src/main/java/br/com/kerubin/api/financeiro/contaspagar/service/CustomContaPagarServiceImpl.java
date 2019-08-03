package br.com.kerubin.api.financeiro.contaspagar.service;

import static br.com.kerubin.api.servicecore.util.CoreUtils.isEmpty;
import static br.com.kerubin.api.servicecore.util.CoreUtils.isNotEmpty;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import br.com.kerubin.api.database.core.ServiceContext;
import br.com.kerubin.api.financeiro.contaspagar.FinanceiroContasPagarConstants;
import br.com.kerubin.api.financeiro.contaspagar.entity.contapagar.ContaPagarEntity;
import br.com.kerubin.api.financeiro.contaspagar.entity.contapagar.ContaPagarServiceImpl;
import br.com.kerubin.api.financeiro.contaspagar.entity.planoconta.PlanoContaAutoComplete;
import br.com.kerubin.api.financeiro.contaspagar.entity.planoconta.PlanoContaAutoCompleteImpl;
import br.com.kerubin.api.financeiro.contaspagar.entity.planoconta.QPlanoContaEntity;
import br.com.kerubin.api.financeiro.contaspagar.event.ContaPagarEvent;
import br.com.kerubin.api.messaging.core.DomainEntityEventsPublisher;
import br.com.kerubin.api.messaging.core.DomainEvent;
import br.com.kerubin.api.messaging.core.DomainEventEnvelope;
import br.com.kerubin.api.messaging.core.DomainEventEnvelopeBuilder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Primary
@Service
public class CustomContaPagarServiceImpl extends ContaPagarServiceImpl {
	
	private static final String ENTITY_KEY = "entity.ContaPagar";
	private static final UUID PLANO_CONTA_DESPESA_ID = UUID.fromString("5cd7d81e-7e69-4c26-bf2f-12ad2e286fc5");
	
	@Autowired
	private DomainEntityEventsPublisher publisher;
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Collection<PlanoContaAutoComplete> planoContaPlanoContasAutoComplete(String query) {
		
		JPAQueryFactory queryDSL = new JPAQueryFactory(em);
		QPlanoContaEntity qPlanoConta = QPlanoContaEntity.planoContaEntity;
		QPlanoContaEntity qPlanoContaPai = new QPlanoContaEntity("planoContaPai");
		
		BooleanBuilder where = new BooleanBuilder();
		if (isNotEmpty(query)) {
			where.and(qPlanoConta.descricao.containsIgnoreCase(query).or(qPlanoContaPai.descricao.containsIgnoreCase(query)));
		}
		
		where.and(qPlanoConta.ativo.isTrue())
		.and(qPlanoConta.deleted.isFalse().or(qPlanoConta.deleted.isNull()));
		
		StringExpression descricaoConcatenation = emptyIfNull(qPlanoContaPai.codigo)
				.concat(" - ")
				.concat(emptyIfNull(qPlanoContaPai.descricao))
				.concat(" / ")
				.concat(qPlanoConta.codigo)
				.concat(" - ")
				.concat(qPlanoConta.descricao);
		
		JPAQuery<Tuple> query_ = queryDSL.select(
				qPlanoConta.id, 
				qPlanoConta.codigo,
				descricaoConcatenation
				)
		.from(qPlanoConta)
		.leftJoin(qPlanoConta.planoContaPai, qPlanoContaPai).on(qPlanoContaPai.id.eq(qPlanoConta.planoContaPai.id))
		.where(where)
		.orderBy(qPlanoConta.codigo.asc());
		
		List<Tuple> tuples = query_.fetch();
		
		List<PlanoContaAutoComplete> items = new ArrayList<>();
		if (tuples != null && !tuples.isEmpty()) {
			for (Tuple tuple: tuples) {
				PlanoContaAutoCompleteImpl plano = new PlanoContaAutoCompleteImpl();
				plano.setId(tuple.get(0, UUID.class));
				plano.setCodigo(tuple.get(1, String.class));
				plano.setDescricao(tuple.get(2, String.class));
				items.add(plano);
			} // for
		}
		
		if (items.size() > 0) {
			PlanoContaAutoComplete plano = items.get(0);
			if (PLANO_CONTA_DESPESA_ID.equals(plano.getId())) { // "descricao": "- / 1-DESPESAS"
				((PlanoContaAutoCompleteImpl) plano).setDescricao(plano.getDescricao().replace(" -  / ", ""));
			}
		}
		
		items = items.stream()
				.sorted(Comparator.comparingInt(this::codigoToInt))
				.collect(Collectors.toList());
		
		return items;
		
	}
	
	private int codigoToInt(Object toOrderObj) {
		if (toOrderObj != null && toOrderObj instanceof PlanoContaAutoComplete) {
			PlanoContaAutoComplete toOrder = (PlanoContaAutoComplete) toOrderObj;
			String codigo = toOrder.getCodigo();
			if (isNotEmpty(codigo)) {
				codigo = codigo.replace(".", "");
				try {
					return Integer.parseInt(codigo);
				} catch(Exception e) {
					return 0;
				}
			}
		}
		
		return 0;
	}
	
	private static StringExpression emptyIfNull(StringExpression expression) {
	    return expression.coalesce("").asString();
	}
	
	@Transactional
	@Override
	public ContaPagarEntity create(ContaPagarEntity contaPagarEntity) {
		ContaPagarEntity entity = super.create(contaPagarEntity);
		
		if (isNotEmpty(entity.getDataPagamento())) {
			publishEventContaPaga(entity);
		}
		
		return entity;
	}
	
	@Transactional
	@Override
	public ContaPagarEntity update(UUID id, ContaPagarEntity contaPagarEntity) {
		ContaPagarEntity beforeUpdateEntity = read(id);
		beforeUpdateEntity = beforeUpdateEntity.clone();
		
		ContaPagarEntity entity = super.update(id, contaPagarEntity);
		
		if (isNotEmpty(beforeUpdateEntity.getDataPagamento()) && isEmpty(entity.getDataPagamento())) { // Estornou
			if (isEmpty(entity.getValorPago())) {
				entity.setValorPago(beforeUpdateEntity.getValorPago());
			}
			publishEventContaEstornada(entity);
		}
		else if (isEmpty(beforeUpdateEntity.getDataPagamento()) && isNotEmpty(entity.getDataPagamento())) { // Pagou
			publishEventContaPaga(entity);
		}
		
		return entity;
		
	}
	
	@Transactional
	@Override
	public void actionBaixarContaComDataPagamentoHoje(java.util.UUID id) {
		// Baixa a conta
		super.actionBaixarContaComDataPagamentoHoje(id);
		
		// Publica mensagem que a conta foi paga.
		publicarContaPaga(id);
	}
	
	
	@Transactional
	@Override
	public void actionBaixarContaComDataPagamentoIgualDataVenciento(UUID id) {
		// Baixa a conta
		super.actionBaixarContaComDataPagamentoIgualDataVenciento(id);
		
		// Publica mensagem que a conta foi paga.
		publicarContaPaga(id);
	}
	
	private void publicarContaPaga(UUID id) {
		// Busca a conta atualziada
		ContaPagarEntity entity = getContaPagarEntity(id);
		
		// Publica a mensagem de conta paga
		if (isNotEmpty(entity.getDataPagamento())) {
			publishEventContaPaga(entity);
		}
	}
	
	@Transactional
	@Override
	public void actionEstornarPagamentoContaComUmClique(UUID id) {
		// Pega os dados da conta antes de ser estornada.
		ContaPagarEntity entity = getContaPagarEntity(id).clone();
		
		// Estorna
		super.actionEstornarPagamentoContaComUmClique(id);
		
		// Publica o estorno
		publishEventContaEstornada(entity);
	}
	
	private void publishEventContaPaga(ContaPagarEntity entity) {
		// Publica a mensagem de conta paga
		publishEvent(entity, ContaPagarEvent.CONTA_PAGAR_CONTAPAGA);
	}
	
	private void publishEventContaEstornada(ContaPagarEntity entity) {
		// Pública estorno
		publishEvent(entity, ContaPagarEvent.CONTA_PAGAR_CONTAESTORNADA);
	}
	
	protected void publishEvent(ContaPagarEntity entity, String eventName) {
		try {
			DomainEvent event = new ContaPagarEvent(entity.getId(), 
				entity.getPlanoContas() != null ? entity.getPlanoContas().getId() : null, 
				entity.getDescricao(), 
				entity.getFormaPagamento(), 
				entity.getContaBancaria() != null ? entity.getContaBancaria().getId() : null, 
				entity.getCartaoCredito() != null ? entity.getCartaoCredito().getId() : null, 
				entity.getDataPagamento(), 
				entity.getValorPago(), 
				entity.getFornecedor() != null ? entity.getFornecedor().getId() : null, 
				entity.getNumDocumento());
			
			DomainEventEnvelope<DomainEvent> envelope = DomainEventEnvelopeBuilder
					.getBuilder(eventName, event)
					.domain(FinanceiroContasPagarConstants.DOMAIN)
					.service(FinanceiroContasPagarConstants.SERVICE)
					.key(ENTITY_KEY)
					.tenant(ServiceContext.getTenant())
					.user(ServiceContext.getUser())
					.build();
			
			publisher.publish(envelope);
		}
		catch (Exception e) {
			log.error("Error publishing event: " + eventName + ", entity: " + entity);
		}
		
	}

}
