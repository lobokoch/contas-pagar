/**********************************************************************************************
Code generated by MKL Plug-in
Copyright: Kerubin - kerubin.platform@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.contapagar;

// import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.lang3.StringUtils;


import br.com.kerubin.api.financeiro.contaspagar.entity.planoconta.PlanoContaAutoComplete;
import br.com.kerubin.api.financeiro.contaspagar.entity.contabancaria.ContaBancariaAutoComplete;
import br.com.kerubin.api.financeiro.contaspagar.entity.cartaocredito.CartaoCreditoAutoComplete;
import br.com.kerubin.api.financeiro.contaspagar.entity.fornecedor.FornecedorAutoComplete;

import br.com.kerubin.api.financeiro.contaspagar.entity.planoconta.PlanoContaRepository;
import br.com.kerubin.api.financeiro.contaspagar.entity.contabancaria.ContaBancariaRepository;
import br.com.kerubin.api.financeiro.contaspagar.entity.cartaocredito.CartaoCreditoRepository;
import br.com.kerubin.api.financeiro.contaspagar.entity.fornecedor.FornecedorRepository;

import java.util.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Objects;
import br.com.kerubin.api.financeiro.contaspagar.TipoPagamentoConta;
import java.time.LocalDate;
import java.text.MessageFormat;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;
import java.time.temporal.ChronoUnit;
 
@Service
public class ContaPagarServiceImpl implements ContaPagarService {
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private ContaPagarBaseRepository contaPagarBaseRepository;
	
	@Autowired
	private ContaPagarListFilterPredicate contaPagarListFilterPredicate;
	
	
	@Autowired
	private PlanoContaRepository planoContaRepository;
	
	@Autowired
	private ContaBancariaRepository contaBancariaRepository;
	
	@Autowired
	private CartaoCreditoRepository cartaoCreditoRepository;
	
	@Autowired
	private FornecedorRepository fornecedorRepository;
	
	
	@Transactional
	@Override
	public ContaPagarEntity create(ContaPagarEntity contaPagarEntity) {
		doRulesFormBeforeSave(contaPagarEntity);
		return contaPagarBaseRepository.save(contaPagarEntity);
	}
	
	@Transactional(readOnly = true)
	@Override
	public ContaPagarEntity read(java.util.UUID id) {
		return getContaPagarEntity(id);
	}
	
	@Transactional
	@Override
	public ContaPagarEntity update(java.util.UUID id, ContaPagarEntity contaPagarEntity) {
		doRulesFormBeforeSave(contaPagarEntity);
		// ContaPagarEntity entity = getContaPagarEntity(id);
		// BeanUtils.copyProperties(contaPagarEntity, entity, "id");
		// entity = contaPagarBaseRepository.save(entity);
		
		ContaPagarEntity entity = contaPagarBaseRepository.save(contaPagarEntity);
		
		return entity;
	}
	
	private void doRulesFormBeforeSave(ContaPagarEntity contaPagar) {
		
		if ((!Boolean.TRUE.equals(contaPagar.getContaPaga())) && Objects.nonNull(contaPagar.getDataPagamento())) {
			throw new IllegalStateException("Foi informada a data de pagamento para a conta, porém ela não foi marcada como conta paga.");
		}
		
		
		if ((Boolean.TRUE.equals(contaPagar.getContaPaga())) && contaPagar.getTipoPagamento().equals(TipoPagamentoConta.SINGLE) && Objects.isNull(contaPagar.getDataPagamento())) {
			throw new IllegalStateException("A data do pagamento deve ser informada para poder pagar a conta.");
		}
		
		
		if ((Boolean.TRUE.equals(contaPagar.getContaPaga())) && contaPagar.getTipoPagamento().equals(TipoPagamentoConta.SINGLE) && contaPagar.getDataPagamento().isAfter(LocalDate.now())) {
			throw new IllegalStateException(MessageFormat.format("A data do pagamento não pode ser maior do que a data de hoje ({0}).", LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
		}
		
		
		if ((Boolean.TRUE.equals(contaPagar.getContaPaga())) && contaPagar.getTipoPagamento().equals(TipoPagamentoConta.SINGLE) && Objects.isNull(contaPagar.getValorPago())) {
			throw new IllegalStateException("O valor total pago deve ser informado para poder pagar a conta.");
		}
		
	}
	
	
	@Transactional
	@Override
	public void delete(java.util.UUID id) {
		
		// Delete it.
		contaPagarBaseRepository.deleteById(id);
		
		// Force flush to the database, for relationship validation and must throw exception because of this here.
		contaPagarBaseRepository.flush();
		
	}
	
	@Transactional
	@Override
	public void deleteInBulk(java.util.List<java.util.UUID> idList) {
		// Delete it.
		contaPagarBaseRepository.deleteInBulk(idList);
		
		// Force flush to the database, for relationship validation and must throw exception because of this here.
		contaPagarBaseRepository.flush();
	}
	
	
	@Transactional(readOnly = true)
	@Override
	public Page<ContaPagarEntity> list(ContaPagarListFilter contaPagarListFilter, Pageable pageable) {
		Predicate predicate = contaPagarListFilterPredicate.mountAndGetPredicate(contaPagarListFilter);
		
		Page<ContaPagarEntity> resultPage = contaPagarBaseRepository.findAll(predicate, pageable);
		return resultPage;
	}
	
	@Transactional(readOnly = true)
	protected ContaPagarEntity getContaPagarEntity(java.util.UUID id) {
		Optional<ContaPagarEntity> contaPagarEntity = contaPagarBaseRepository.findById(id);
		if (!contaPagarEntity.isPresent()) {
			throw new IllegalArgumentException("ContaPagar not found:" + id.toString());
		}
		return contaPagarEntity.get();
	}
	
	@Transactional(readOnly = true)
	@Override
	public Collection<ContaPagarAutoComplete> autoComplete(String query) {
		Collection<ContaPagarAutoComplete> result = contaPagarBaseRepository.autoComplete(query);
		return result;
	}
	
	// Begin relationships autoComplete 
	@Transactional(readOnly = true)
	@Override
	public Collection<PlanoContaAutoComplete> planoContaPlanoContasAutoComplete(String query) {
		Collection<PlanoContaAutoComplete> result = planoContaRepository.autoComplete(query);
		return result;
	}
	
	@Transactional(readOnly = true)
	@Override
	public Collection<ContaBancariaAutoComplete> contaBancariaContaBancariaAutoComplete(String query) {
		Collection<ContaBancariaAutoComplete> result = contaBancariaRepository.autoComplete(query);
		return result;
	}
	
	@Transactional(readOnly = true)
	@Override
	public Collection<CartaoCreditoAutoComplete> cartaoCreditoCartaoCreditoAutoComplete(String query) {
		Collection<CartaoCreditoAutoComplete> result = cartaoCreditoRepository.autoComplete(query);
		return result;
	}
	
	@Transactional(readOnly = true)
	@Override
	public Collection<FornecedorAutoComplete> fornecedorFornecedorAutoComplete(String query) {
		Collection<FornecedorAutoComplete> result = fornecedorRepository.autoComplete(query);
		return result;
	}
	
	// End relationships autoComplete
	
	
	@Transactional(readOnly = true)
	@Override
	public Collection<ContaPagarDescricaoAutoComplete> contaPagarDescricaoAutoComplete(String query) {
		Collection<ContaPagarDescricaoAutoComplete> result = contaPagarBaseRepository.contaPagarDescricaoAutoComplete(query);
		return result;
	}
	
	@Transactional(readOnly = true)
	@Override
	public Collection<ContaPagarHistConcBancariaAutoComplete> contaPagarHistConcBancariaAutoComplete(String query) {
		Collection<ContaPagarHistConcBancariaAutoComplete> result = contaPagarBaseRepository.contaPagarHistConcBancariaAutoComplete(query);
		return result;
	}
	
	@Transactional(readOnly = true)
	@Override
	public Collection<ContaPagarAgrupadorAutoComplete> contaPagarAgrupadorAutoComplete(String query) {
		Collection<ContaPagarAgrupadorAutoComplete> result = contaPagarBaseRepository.contaPagarAgrupadorAutoComplete(query);
		return result;
	}
	
	@Transactional(readOnly = true)
	@Override
	public ContaPagarSumFields getContaPagarSumFields(ContaPagarListFilter contaPagarListFilter) {
		Predicate predicate = contaPagarListFilterPredicate.mountAndGetPredicate(contaPagarListFilter);
		
		QContaPagarEntity qEntity = QContaPagarEntity.contaPagarEntity;
		JPAQueryFactory query = new JPAQueryFactory(em);
		ContaPagarSumFields result = query.select(
				Projections.bean(ContaPagarSumFields.class, 
						qEntity.valor.sum().coalesce(BigDecimal.ZERO).as("sumValor"), 
						qEntity.valorDesconto.sum().coalesce(BigDecimal.ZERO).as("sumValorDesconto"), 
						qEntity.valorMulta.sum().coalesce(BigDecimal.ZERO).as("sumValorMulta"), 
						qEntity.valorJuros.sum().coalesce(BigDecimal.ZERO).as("sumValorJuros"), 
						qEntity.valorAcrescimos.sum().coalesce(BigDecimal.ZERO).as("sumValorAcrescimos"), 
						qEntity.valorPago.sum().coalesce(BigDecimal.ZERO).as("sumValorPago")
				))
		.from(qEntity)
		.where(predicate)
		.fetchOne();
		
		return result;
	}
	
	
	@Transactional
	@Override
	public void actionBaixarContaComDataPagamentoHoje(java.util.UUID id) {
		ContaPagarEntity contaPagar = getContaPagarEntity(id);
		
		if ((Boolean.FALSE.equals(contaPagar.getContaPaga()))) {
			contaPagar.setContaPaga(Boolean.TRUE);
			contaPagar.setDataPagamento(LocalDate.now());
			contaPagar.setValorPago(contaPagar.getValor());
			
			contaPagar = contaPagarBaseRepository.save(contaPagar);
		}
		else {
			throw new IllegalStateException("Condição inválida para executar a ação: actionBaixarContaComDataPagamentoHoje.");
		}
		
	}
	
	@Transactional
	@Override
	public void actionBaixarContaComDataPagamentoIgualDataVenciento(java.util.UUID id) {
		ContaPagarEntity contaPagar = getContaPagarEntity(id);
		
		if ((Boolean.FALSE.equals(contaPagar.getContaPaga())) && contaPagar.getDataVencimento().isBefore(LocalDate.now())) {
			contaPagar.setContaPaga(Boolean.TRUE);
			contaPagar.setDataPagamento(contaPagar.getDataVencimento());
			contaPagar.setValorPago(contaPagar.getValor());
			
			contaPagar = contaPagarBaseRepository.save(contaPagar);
		}
		else {
			throw new IllegalStateException("Condição inválida para executar a ação: actionBaixarContaComDataPagamentoIgualDataVenciento.");
		}
		
	}
	
	@Transactional
	@Override
	public void actionEstornarPagamentoContaComUmClique(java.util.UUID id) {
		ContaPagarEntity contaPagar = getContaPagarEntity(id);
		
		if ((Boolean.TRUE.equals(contaPagar.getContaPaga())) && contaPagar.getTipoPagamento().equals(TipoPagamentoConta.SINGLE)) {
			contaPagar.setDataPagamento(null);
			contaPagar.setContaPaga(Boolean.FALSE);
			contaPagar.setValorPago(null);
			contaPagar.setTipoPagamento(TipoPagamentoConta.SINGLE);
			
			contaPagar = contaPagarBaseRepository.save(contaPagar);
		}
		else {
			throw new IllegalStateException("Condição inválida para executar a ação: actionEstornarPagamentoContaComUmClique.");
		}
		
	}
	
	@Transactional
	@Override
	public void actionFazerCopiasContaPagar(ContaPagarMakeCopies contaPagarMakeCopies) {
		if (StringUtils.isBlank(contaPagarMakeCopies.getAgrupador())) {
			throw new IllegalArgumentException("O campo 'Agrupador' deve ser informado.");
		}
		
		ContaPagarEntity contaPagar = getContaPagarEntity(contaPagarMakeCopies.getId());
		contaPagar.setAgrupador(contaPagarMakeCopies.getAgrupador());
		
		LocalDate lastDate = contaPagar.getDataVencimento();
		List<ContaPagarEntity> copies = new ArrayList<>();
		long interval = contaPagarMakeCopies.getReferenceFieldInterval();
		int fixedDay = lastDate.getDayOfMonth();
		int fixedDayCopy = fixedDay;
		for (int i = 0; i < contaPagarMakeCopies.getNumberOfCopies(); i++) {
			ContaPagarEntity copiedEntity = contaPagar.clone();
			copies.add(copiedEntity);
			copiedEntity.setId(null);
			lastDate = lastDate.plus(interval, ChronoUnit.DAYS);
			if (interval == 30) {
				int length = lastDate.lengthOfMonth();
				while (fixedDay > length) {
				    fixedDay--;
				}
				lastDate = lastDate.withDayOfMonth(fixedDay);
				fixedDay = fixedDayCopy;
			}
			copiedEntity.setDataVencimento(lastDate);
		}
		
		copies.add(contaPagar);
		contaPagarBaseRepository.saveAll(copies);
	}
}
