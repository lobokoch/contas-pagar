package br.com.kerubin.api.financeiro.contaspagar.service;

import static br.com.kerubin.api.servicecore.util.CoreUtils.isEmpty;
import static br.com.kerubin.api.servicecore.util.CoreUtils.isZero;
import static br.com.kerubin.api.servicecore.util.CoreUtils.isNotEmpty;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
import br.com.kerubin.api.financeiro.contaspagar.TipoPagamentoConta;
import br.com.kerubin.api.financeiro.contaspagar.entity.contapagar.ContaPagarBaseRepository;
import br.com.kerubin.api.financeiro.contaspagar.entity.contapagar.ContaPagarEntity;
import br.com.kerubin.api.financeiro.contaspagar.entity.contapagar.ContaPagarServiceImpl;
import br.com.kerubin.api.financeiro.contaspagar.entity.planoconta.PlanoContaAutoComplete;
import br.com.kerubin.api.financeiro.contaspagar.entity.planoconta.PlanoContaAutoCompleteImpl;
import br.com.kerubin.api.financeiro.contaspagar.entity.planoconta.QPlanoContaEntity;
import br.com.kerubin.api.financeiro.contaspagar.event.ContaPagarEvent;
import br.com.kerubin.api.financeiro.contaspagar.model.ContaPagarMultipleResumo;
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
	// private static final UUID PLANO_CONTA_DESPESA_ID = UUID.fromString("5cd7d81e-7e69-4c26-bf2f-12ad2e286fc5");
	
	private static final String EMPTY_PLANO_CONTA_PAI_DESC =  " -  / ";
	
	@Inject
	private DomainEntityEventsPublisher publisher;
	
	@PersistenceContext
	private EntityManager em;
	
	@Inject
	private CustomContaPagarMultipleServiceImpl customContaPagarMultipleServiceImpl;
	
	@Inject
	private ContaPagarBaseRepository contaPagarRepository;
	
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
		
		items = items.stream()
				.peek(it -> it.setDescricao(it.getDescricao().replace(EMPTY_PLANO_CONTA_PAI_DESC, "")))
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
		
		if (contaPagarEntity.getContaPaga()) {
			publishEventContaPaga(entity);
		}
		
		return entity;
	}
	
	@Transactional
	@Override
	public ContaPagarEntity update(UUID id, ContaPagarEntity contaPagarEntity) {
		// Controle para múltiplos pagamentos.
		ContaPagarMultipleResumo resumo = customContaPagarMultipleServiceImpl.getResumoPelaContaPagar(id);
		if (resumo.isTemResumo() && !TipoPagamentoConta.MULTIPLE.equals(contaPagarEntity.getTipoPagamento())) {
			throw new IllegalStateException("Esta conta não poder ser do tipo \"Pagamento único\" porque ela possui \"Pagamentos múltiplos\".");
		}
		
		if (TipoPagamentoConta.MULTIPLE.equals(contaPagarEntity.getTipoPagamento())) {
			contaPagarEntity.setValorPago(resumo.getValorTotalPago());
			
			if (contaPagarEntity.getContaPaga()) {
				contaPagarEntity.setDataPagamento(resumo.getDataUltimoPagamento());				
			}
			
		}
		
		ContaPagarEntity beforeUpdateEntity = read(id);
		em.detach(beforeUpdateEntity);
		
		beforeUpdateEntity = beforeUpdateEntity.clone();
		
		if (resumo.isTemResumo() && beforeUpdateEntity.getContaPaga() && !contaPagarEntity.getContaPaga()) {
			// Safadinho, tentando despagar a conta que tem ainda pagamentos multiplos associados.
			throw new IllegalStateException("Para remover a marcação de \"Conta paga\", você deve primeiro excluir os pagamentos já realizados," +
			          " assim eles serão estornados no caixa.");
			
		}
		
		
		ContaPagarEntity entity = super.update(id, contaPagarEntity);
		
		// Publicação de evento para lançamento no caixa.
		if (beforeUpdateEntity.getContaPaga() && !entity.getContaPaga()) { // Despagou a conta.
			if (isEmpty(entity.getValorPago()) || isZero(entity.getValorPago())) {
				entity.setValorPago(beforeUpdateEntity.getValorPago());
			}
			
			publishEventContaEstornada(entity);
		} else if (!beforeUpdateEntity.getContaPaga() && entity.getContaPaga()) { // Pagou a conta
			publishEventContaPaga(entity);
		}
		
		return entity;
		
	}
	
	@Transactional
	@Override
	public void actionBaixarContaComDataPagamentoHoje(java.util.UUID id) {
		ContaPagarEntity contaPagar = getContaPagarEntity(id);
		boolean isMultiple = TipoPagamentoConta.MULTIPLE.equals(contaPagar.getTipoPagamento());
		
		ContaPagarMultipleResumo resumo = customContaPagarMultipleServiceImpl.getResumoPelaContaPagar(id);
		
		if (resumo.isTemResumo() && !isMultiple) {
			throw new IllegalStateException("Esta conta não poder ser baixada com o tipo \"Pagamento único\" porque ela possui \"Pagamentos múltiplos\".");
		}
		
		// Controle para múltiplos pagamentos.
		BigDecimal valor = contaPagar.getValor(); // Gambi temporária devido ao actionBaixarContaComDataPagamentoHoje
		if (isMultiple) {
			contaPagar.setValor(resumo.getValorTotalPago());
		}
				
		// Baixa a conta
		super.actionBaixarContaComDataPagamentoHoje(id);
		
		if (isMultiple) {
			contaPagar.setValor(valor);			
			contaPagar = contaPagarRepository.save(contaPagar);
		}
		
		// Publica mensagem que a conta foi paga.
		publicarContaPaga(id);
	}
	
	
	@Transactional
	@Override
	public void actionBaixarContaComDataPagamentoIgualDataVenciento(UUID id) {
		// Controle para múltiplos pagamentos.
		ContaPagarEntity contaPagar = getContaPagarEntity(id);
		boolean isMultiple = TipoPagamentoConta.MULTIPLE.equals(contaPagar.getTipoPagamento());
		
		ContaPagarMultipleResumo resumo = customContaPagarMultipleServiceImpl.getResumoPelaContaPagar(id);
		
		if (resumo.isTemResumo() && !isMultiple) {
			throw new IllegalStateException("Esta conta não poder ser baixada com o tipo \"Pagamento único\" porque ela possui \"Pagamentos múltiplos\".");
		}
		
		BigDecimal valor = contaPagar.getValor(); // Gambi temporária devido ao actionBaixarContaComDataPagamentoHoje
		if (isMultiple) {
			contaPagar.setValor(resumo.getValorTotalPago());
		}
				
		// Baixa a conta
		super.actionBaixarContaComDataPagamentoIgualDataVenciento(id);
		
		// Controle para múltiplos pagamentos.
		if (isMultiple) {
			contaPagar.setValor(valor);			
			contaPagar = contaPagarRepository.save(contaPagar);
		}
		
		// Publica mensagem que a conta foi paga.
		publicarContaPaga(id);
	}
	
	private void publicarContaPaga(UUID id) {
		// Busca a conta atualziada
		ContaPagarEntity entity = getContaPagarEntity(id);
		
		// Publica a mensagem de conta paga
		if (isNotEmpty(entity.getContaPaga())) {
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
	
	public void publishEventContaPaga(ContaPagarEntity entity) {
		// Publica a mensagem de conta paga
		publishEvent(entity, ContaPagarEvent.CONTA_PAGAR_CONTAPAGA);
	}
	
	public void publishEventContaEstornada(ContaPagarEntity entity) {
		// Pública estorno
		publishEvent(entity, ContaPagarEvent.CONTA_PAGAR_CONTAESTORNADA);
	}
	
	protected void publishEvent(ContaPagarEntity entity, String eventName) {
		try {
			
			DomainEvent event = ContaPagarEvent.builder()
					.id(entity.getId())
					.descricao(entity.getDescricao())
					.planoContas(entity.getPlanoContas() != null ? entity.getPlanoContas().getId() : null)
					.formaPagamento(entity.getFormaPagamento())
					.contaBancaria(entity.getContaBancaria() != null ? entity.getContaBancaria().getId() : null)
					.cartaoCredito(entity.getCartaoCredito() != null ? entity.getCartaoCredito().getId() : null)
					.fornecedor(entity.getFornecedor() != null ? entity.getFornecedor().getId() : null)
					.contaPaga(entity.getContaPaga() != null ? entity.getContaPaga() : false)
					.dataPagamento(entity.getDataPagamento())
					.valorPago(entity.getValorPago())
					.numDocumento(entity.getNumDocumento())
					.tipoPagamento(entity.getTipoPagamento())
					.contaPagarPai(entity.getContaPagarPai())
					.build();
			
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
			log.error("ERROR PUBLISHING EVENT: " + eventName + ", entity: " + entity);
		}
		
	}
	

}
