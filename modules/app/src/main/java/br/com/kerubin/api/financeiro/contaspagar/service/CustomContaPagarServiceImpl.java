package br.com.kerubin.api.financeiro.contaspagar.service;

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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import br.com.kerubin.api.financeiro.contaspagar.entity.contapagar.ContaPagarListFilter;
import br.com.kerubin.api.financeiro.contaspagar.entity.contapagar.ContaPagarServiceImpl;
import br.com.kerubin.api.financeiro.contaspagar.entity.planoconta.PlanoContaAutoComplete;
import br.com.kerubin.api.financeiro.contaspagar.entity.planoconta.PlanoContaAutoCompleteImpl;
import br.com.kerubin.api.financeiro.contaspagar.entity.planoconta.PlanoContaEntity;
import br.com.kerubin.api.financeiro.contaspagar.entity.planoconta.QPlanoContaEntity;
import br.com.kerubin.api.financeiro.contaspagar.event.ContaPagarEvent;
import br.com.kerubin.api.messaging.core.DomainEntityEventsPublisher;
import br.com.kerubin.api.messaging.core.DomainEvent;
import br.com.kerubin.api.messaging.core.DomainEventEnvelope;
import br.com.kerubin.api.messaging.core.DomainEventEnvelopeBuilder;

@Primary
@Service
public class CustomContaPagarServiceImpl extends ContaPagarServiceImpl {
	
	private static final String ENTITY_KEY = "entity.ContaPagar";
	private static final UUID PLANO_CONTA_DESPESA_ID = UUID.fromString("5cd7d81e-7e69-4c26-bf2f-12ad2e286fc5");
	
	@Autowired
	private DomainEntityEventsPublisher publisher;
	
	@PersistenceContext
	private EntityManager em;
	
	@Transactional(readOnly = true)
	@Override
	public Page<ContaPagarEntity> list(ContaPagarListFilter contaPagarListFilter, Pageable pageable) {
		Page<ContaPagarEntity> result = super.list(contaPagarListFilter, pageable);
		
		if (isNotEmpty(result)) {
			result.forEach(item -> decoratePlanoContas(item.getPlanoContas()));
		}
		
		return result;
	}
	
	@Transactional(readOnly = true)
	@Override
	public ContaPagarEntity read(UUID id) {
		ContaPagarEntity result = super.read(id);
		if (isNotEmpty(result)) {
			decoratePlanoContas(result.getPlanoContas());
		}
		return result;
	}
	
	private void decoratePlanoContas(PlanoContaEntity planoContas) {
		if (isNotEmpty(planoContas)) { // Adjusts the field descricao of plano de contas and plano de contas pai.
			String descricao = planoContas.getCodigo() + " - " + planoContas.getDescricao();
			PlanoContaEntity planoContasPai = planoContas.getPlanoContaPai();
			if (isNotEmpty(planoContasPai)) {
				descricao = planoContasPai.getCodigo() + " - " + planoContasPai.getDescricao() + " / " + descricao;
			}
			planoContas.setDescricao(descricao);
		} 
	}
	
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
	public void actionBaixarContaComUmClique(UUID id) {
		// Baixa a conta
		super.actionBaixarContaComUmClique(id);
		
		// Busca a conta atualziada
		ContaPagarEntity entity = getContaPagarEntity(id);
		
		// Publica a mensagem de conta paga
		if (entity.getDataPagamento() != null) {
			publishEvent(entity, ContaPagarEvent.CONTA_PAGAR_CONTAPAGA);
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
		publishEvent(entity, ContaPagarEvent.CONTA_PAGAR_CONTAESTORNADA);
	}
	
	protected void publishEvent(ContaPagarEntity entity, String eventName) {
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

}
