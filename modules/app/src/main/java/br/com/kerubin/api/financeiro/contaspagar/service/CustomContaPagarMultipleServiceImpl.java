package br.com.kerubin.api.financeiro.contaspagar.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;

import br.com.kerubin.api.financeiro.contaspagar.TipoPagamentoConta;
import br.com.kerubin.api.financeiro.contaspagar.entity.contapagar.ContaPagarEntity;
import br.com.kerubin.api.financeiro.contaspagar.entity.contapagar.QContaPagarEntity;
import br.com.kerubin.api.financeiro.contaspagar.entity.contapagarmultiple.ContaPagarMultipleEntity;
import br.com.kerubin.api.financeiro.contaspagar.entity.contapagarmultiple.ContaPagarMultipleListFilter;
import br.com.kerubin.api.financeiro.contaspagar.entity.contapagarmultiple.ContaPagarMultipleServiceImpl;
import br.com.kerubin.api.financeiro.contaspagar.entity.contapagarmultiple.QContaPagarMultipleEntity;
import br.com.kerubin.api.financeiro.contaspagar.model.ContaPagarMultipleResumo;
import lombok.extern.slf4j.Slf4j;
import static br.com.kerubin.api.servicecore.util.CoreUtils.isNotEmpty;
import static br.com.kerubin.api.servicecore.util.CoreUtils.isNotEquals;

@Slf4j
@Primary
@Service
public class CustomContaPagarMultipleServiceImpl extends ContaPagarMultipleServiceImpl {
	
	@PersistenceContext	
	private EntityManager em;
	
	@Inject
	private CustomContaPagarServiceImpl contaPagarService;
	
	@Transactional
	@Override
	public ContaPagarMultipleEntity create(ContaPagarMultipleEntity contaPagarMultipleEntity) {
		ContaPagarMultipleEntity result = super.create(contaPagarMultipleEntity);
		updateContaPagar(result.getContaPagar().getId());
		
		ContaPagarEntity contaPagarEntity = buildContaPagar(contaPagarMultipleEntity);
		contaPagarService.publishEventContaPaga(contaPagarEntity);
		
		return result;
	}

	@Transactional
	@Override
	public ContaPagarMultipleEntity update(UUID id, ContaPagarMultipleEntity contaPagarMultipleEntity) {
		
		// Controle pra estorno
		ContaPagarMultipleEntity oldContaPagarMultipleEntity = read(id);
		em.detach(oldContaPagarMultipleEntity);
		oldContaPagarMultipleEntity = oldContaPagarMultipleEntity.clone();
		
		ContaPagarMultipleEntity result = super.update(id, contaPagarMultipleEntity);
		updateContaPagar(result.getContaPagar().getId());
		
		// Verifica se alterou a data de pagamento, valor pago ou o plano de contas, ai estorna e relança no caixa.
		if (!oldContaPagarMultipleEntity.getDataPagamento().equals(result.getDataPagamento()) ||
			isNotEquals(oldContaPagarMultipleEntity.getValorPago(), result.getValorPago()) ||
			!oldContaPagarMultipleEntity.getPlanoContas().getId().equals(result.getPlanoContas().getId()) ) {
			
			//Publica estorno do pagamento anterior
			ContaPagarEntity contaPagarEntity = buildContaPagar(oldContaPagarMultipleEntity);
			contaPagarService.publishEventContaEstornada(contaPagarEntity);
			
			// Publica novo pagamento
			contaPagarEntity = buildContaPagar(contaPagarMultipleEntity);
			contaPagarService.publishEventContaPaga(contaPagarEntity);
		}
		
		return result;
	}
	
	@Transactional
	@Override
	public void delete(UUID id) {
		// Controle para estorno.
		ContaPagarMultipleEntity oldContaPagarMultipleEntity = read(id);
		em.detach(oldContaPagarMultipleEntity);
		oldContaPagarMultipleEntity = oldContaPagarMultipleEntity.clone();
		
		JPAQueryFactory query = new JPAQueryFactory(em);
		QContaPagarMultipleEntity cpm = QContaPagarMultipleEntity.contaPagarMultipleEntity;
		UUID contaPagarId = query.select(cpm.contaPagar.id).from(cpm).where(cpm.id.eq(id)).fetchFirst();
		
		super.delete(id);
		updateContaPagar(contaPagarId);
		
		ContaPagarEntity contaPagarEntity = buildContaPagar(oldContaPagarMultipleEntity);
		contaPagarService.publishEventContaEstornada(contaPagarEntity);
	}
	
	@Transactional(readOnly = true)
	@Override
	public Page<ContaPagarMultipleEntity> list(ContaPagarMultipleListFilter contaPagarMultipleListFilter,
			Pageable pageable) {
		
		if (contaPagarMultipleListFilter == null || contaPagarMultipleListFilter.getContaPagarId() == null) {
			throw new IllegalArgumentException("O identificador da Conta a Pagar pai deve ser informado para poder listar os lançamentos de pagamentos múltiplos."); 
		}
		
		return super.list(contaPagarMultipleListFilter, pageable);
	}
	
	private void updateContaPagar(UUID contaPagarId) {
		JPAQueryFactory query = new JPAQueryFactory(em);
		QContaPagarEntity cp = QContaPagarEntity.contaPagarEntity;
		
		ContaPagarMultipleResumo resumo = getResumoPelaContaPagar(contaPagarId);
		
		if (resumo.isTemResumo()) {
			long affectedRows = query.update(cp) //
					.set(cp.valorPago, resumo.getValorTotalPago()) //
					// .set(cp.dataPagamento, resumo.getDataUltimoPagamento()) // Fica estranho ter data de pagamento e a conta sai da listagem, teria que mudar a flag da lsitagem pra não olhar pra data.
					.where(cp.id.eq(contaPagarId)) //
					.execute();
			
			log.debug("{} contas a pagar atualizadas.", affectedRows);
		}
	}
	
	@Transactional(readOnly = true)
	public ContaPagarMultipleResumo getResumoPelaContaPagar(UUID contaPagarId) {
		JPAQueryFactory query = new JPAQueryFactory(em);
		QContaPagarMultipleEntity cpm = QContaPagarMultipleEntity.contaPagarMultipleEntity;
		
		Tuple tuple = query.select(cpm.valorPago.sum().coalesce(BigDecimal.ZERO), cpm.dataPagamento.max())/*.distinct()=fetchFirst*/ //
				.from(cpm) //
				.where(cpm.contaPagar.id.eq(contaPagarId)) //
				.fetchFirst(); // limit 1
		
		ContaPagarMultipleResumo result = new ContaPagarMultipleResumo();
		if (tuple != null) {
			BigDecimal valorTotalPago = tuple.get(0, BigDecimal.class);
			LocalDate dataUltimoPagamento = tuple.get(1, LocalDate.class);
			
			result.setTemResumo(isNotEmpty(dataUltimoPagamento) || isNotEmpty(valorTotalPago));
			result.setValorTotalPago(valorTotalPago);
			result.setDataUltimoPagamento(dataUltimoPagamento);
			
		}
		
		return result;
	}
	
	private ContaPagarEntity buildContaPagar(ContaPagarMultipleEntity source) {
		ContaPagarEntity result = new ContaPagarEntity();
		
		result.setId(source.getId());
		result.setDescricao(source.getDescricao());
		result.setPlanoContas(source.getPlanoContas());
		
		result.setDataVencimento(source.getContaPagar().getDataVencimento());
		result.setValor(source.getContaPagar().getValor());
		
		result.setFormaPagamento(source.getFormaPagamento());
		result.setContaBancaria(source.getContaBancaria());
		result.setCartaoCredito(source.getCartaoCredito());
		result.setOutrosDescricao(source.getOutrosDescricao());
		result.setFornecedor(source.getFornecedor());
		result.setContaPaga(true);
		result.setDataPagamento(source.getDataPagamento());
		result.setValorPago(source.getValorPago());
		
		result.setNumDocumento("CP_PAI:" + source.getContaPagar().getId().toString());
		result.setAgrupador(source.getContaPagar().getAgrupador());
		
		result.setContaPagarPai(source.getContaPagar().getId());
		result.setTipoPagamento(TipoPagamentoConta.MULTIPLE);
		
		// Conciliação bancária
		result.setIdConcBancaria(source.getIdConcBancaria());
		result.setHistConcBancaria(source.getHistConcBancaria());
		result.setNumDocConcBancaria(source.getNumDocConcBancaria());
		
		return result;
	}
	
	public ContaPagarMultipleEntity buildContaPagarMultiple(ContaPagarEntity source) {
		ContaPagarMultipleEntity result = new ContaPagarMultipleEntity();
		
		result.setDataPagamento(source.getDataPagamento());
		result.setValorPago(source.getValorPago());
		result.setDescricao(source.getDescricao());
		result.setFornecedor(source.getFornecedor());
		result.setPlanoContas(source.getPlanoContas());
		result.setFormaPagamento(source.getFormaPagamento());
		result.setContaBancaria(source.getContaBancaria());
		result.setCartaoCredito(source.getCartaoCredito());
		result.setOutrosDescricao(source.getOutrosDescricao());
		result.setContaPagar(source);
		
		// Conciliação bancária
		result.setIdConcBancaria(source.getIdConcBancaria());
		result.setHistConcBancaria(source.getHistConcBancaria());
		result.setNumDocConcBancaria(source.getNumDocConcBancaria());
		
		return result;
	}

}
