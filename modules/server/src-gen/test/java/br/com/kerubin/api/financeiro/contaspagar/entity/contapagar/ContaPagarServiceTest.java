/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-22T06:59:30.300
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.contapagar;

import br.com.kerubin.api.financeiro.contaspagar.entity.planoconta.PlanoContaEntity;
import br.com.kerubin.api.financeiro.contaspagar.entity.contabancaria.ContaBancariaEntity;
import br.com.kerubin.api.financeiro.contaspagar.entity.cartaocredito.CartaoCreditoEntity;
import br.com.kerubin.api.financeiro.contaspagar.entity.fornecedor.FornecedorEntity;
import br.com.kerubin.api.financeiro.contaspagar.entity.planoconta.PlanoContaLookupResult;
import br.com.kerubin.api.financeiro.contaspagar.entity.contabancaria.ContaBancariaLookupResult;
import br.com.kerubin.api.financeiro.contaspagar.entity.cartaocredito.CartaoCreditoLookupResult;
import br.com.kerubin.api.financeiro.contaspagar.entity.fornecedor.FornecedorLookupResult;
import br.com.kerubin.api.financeiro.contaspagar.FormaPagamento;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import javax.inject.Inject;
import br.com.kerubin.api.financeiro.contaspagar.entity.planoconta.PlanoContaRepository;
import br.com.kerubin.api.financeiro.contaspagar.entity.contabancaria.ContaBancariaRepository;
import br.com.kerubin.api.financeiro.contaspagar.entity.cartaocredito.CartaoCreditoRepository;
import br.com.kerubin.api.financeiro.contaspagar.entity.fornecedor.FornecedorRepository;
import org.springframework.boot.test.mock.mockito.MockBean;
import br.com.kerubin.api.messaging.core.DomainEntityEventsPublisher;
import br.com.kerubin.api.financeiro.contaspagar.TipoPlanoContaFinanceiro;
import br.com.kerubin.api.financeiro.contaspagar.TipoReceitaDespesa;
import br.com.kerubin.api.financeiro.contaspagar.entity.agenciabancaria.AgenciaBancariaEntity;
import br.com.kerubin.api.financeiro.contaspagar.entity.agenciabancaria.AgenciaBancariaLookupResult;
import br.com.kerubin.api.financeiro.contaspagar.TipoContaBancaria;
import br.com.kerubin.api.financeiro.contaspagar.entity.banco.BancoEntity;
import br.com.kerubin.api.financeiro.contaspagar.entity.bandeiracartao.BandeiraCartaoEntity;
import br.com.kerubin.api.financeiro.contaspagar.entity.banco.BancoLookupResult;
import br.com.kerubin.api.financeiro.contaspagar.entity.bandeiracartao.BandeiraCartaoLookupResult;
import br.com.kerubin.api.financeiro.contaspagar.TipoPessoa;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.ArgumentMatchers.any;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import br.com.kerubin.api.financeiro.contaspagar.common.PageResult;
import java.util.Arrays;
import java.util.Collections;
import org.springframework.data.domain.Sort;
import java.util.Collection;
import br.com.kerubin.api.financeiro.contaspagar.entity.planoconta.PlanoContaAutoComplete;
import br.com.kerubin.api.financeiro.contaspagar.entity.contabancaria.ContaBancariaAutoComplete;
import br.com.kerubin.api.financeiro.contaspagar.entity.cartaocredito.CartaoCreditoAutoComplete;
import br.com.kerubin.api.financeiro.contaspagar.entity.fornecedor.FornecedorAutoComplete;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;
import br.com.kerubin.api.financeiro.contaspagar.FinanceiroContasPagarBaseEntityTest;


@RunWith(SpringRunner.class)
public class ContaPagarServiceTest extends FinanceiroContasPagarBaseEntityTest {
	
	private static final String[] IGNORED_FIELDS = { "id", "createdBy", "createdDate", "lastModifiedBy", "lastModifiedDate" };
	
	@TestConfiguration
	static class ContaPagarServiceTestConfig {
		
		@Bean
		public ContaPagarListFilterPredicate contaPagarListFilterPredicate() {
			return new ContaPagarListFilterPredicateImpl();
		}
		
		@Bean
		public ContaPagarService contaPagarService() {
			return new ContaPagarServiceImpl();
		}
		
		@Bean
		public ContaPagarDTOConverter contaPagarDTOConverter() {
			return new ContaPagarDTOConverter();
		}
		
	}
	
	
	@Inject
	protected ContaPagarService contaPagarService;
	
	@Inject
	protected ContaPagarDTOConverter contaPagarDTOConverter;
	
	@Inject
	protected ContaPagarBaseRepository contaPagarBaseRepository;
	
	@Inject
	protected PlanoContaRepository planoContaRepository;
	
	@Inject
	protected ContaBancariaRepository contaBancariaRepository;
	
	@Inject
	protected CartaoCreditoRepository cartaoCreditoRepository;
	
	@Inject
	protected FornecedorRepository fornecedorRepository;
	
	@MockBean
	protected DomainEntityEventsPublisher publisher;
	
	// BEGIN CREATE TESTS
	
	@Test
	public void testCreateWithAllFields() throws Exception {
		ContaPagar contaPagar = new ContaPagar();
		
		contaPagar.setId(java.util.UUID.randomUUID());
		
		PlanoContaEntity planoContaEntityParam = newPlanoContaEntity();
		PlanoContaLookupResult planoContas = newPlanoContaLookupResult(planoContaEntityParam);
		contaPagar.setPlanoContas(planoContas);
		
		contaPagar.setDescricao(generateRandomString(255));
		contaPagar.setDataVencimento(getNextDate());
		contaPagar.setValor(new java.math.BigDecimal("10555.29209"));
		contaPagar.setFormaPagamento(FormaPagamento.DINHEIRO);
		
		ContaBancariaEntity contaBancariaEntityParam = newContaBancariaEntity();
		ContaBancariaLookupResult contaBancaria = newContaBancariaLookupResult(contaBancariaEntityParam);
		contaPagar.setContaBancaria(contaBancaria);
		
		
		CartaoCreditoEntity cartaoCreditoEntityParam = newCartaoCreditoEntity();
		CartaoCreditoLookupResult cartaoCredito = newCartaoCreditoLookupResult(cartaoCreditoEntityParam);
		contaPagar.setCartaoCredito(cartaoCredito);
		
		contaPagar.setOutrosDescricao(generateRandomString(255));
		contaPagar.setDataPagamento(getNextDate());
		contaPagar.setValorDesconto(new java.math.BigDecimal("31939.25935"));
		contaPagar.setValorMulta(new java.math.BigDecimal("17249.14637"));
		contaPagar.setValorJuros(new java.math.BigDecimal("25025.26870"));
		contaPagar.setValorAcrescimos(new java.math.BigDecimal("18339.19387"));
		contaPagar.setValorPago(new java.math.BigDecimal("6869.153"));
		
		FornecedorEntity fornecedorEntityParam = newFornecedorEntity();
		FornecedorLookupResult fornecedor = newFornecedorLookupResult(fornecedorEntityParam);
		contaPagar.setFornecedor(fornecedor);
		
		contaPagar.setNumDocumento(generateRandomString(255));
		contaPagar.setIdConcBancaria(generateRandomString(255));
		contaPagar.setHistConcBancaria(generateRandomString(255));
		contaPagar.setNumDocConcBancaria(generateRandomString(255));
		contaPagar.setObservacoes(generateRandomString(1000));
		contaPagar.setAgrupador(generateRandomString(255));
		ContaPagarEntity contaPagarEntity = contaPagarService.create(contaPagarDTOConverter.convertDtoToEntity(contaPagar));
		em.flush();
		verify(publisher, times(0)).publish(any());
		ContaPagar actual = contaPagarDTOConverter.convertEntityToDto(contaPagarEntity);
		
		
		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isNotNull();
		assertThat(actual).isEqualToIgnoringGivenFields(contaPagar, IGNORED_FIELDS);
		
		
		assertThat(actual.getPlanoContas().getId()).isNotNull();
		assertThat(actual.getPlanoContas()).isEqualToIgnoringGivenFields(contaPagar.getPlanoContas(), IGNORED_FIELDS);
		
		
		assertThat(actual.getContaBancaria().getId()).isNotNull();
		assertThat(actual.getContaBancaria()).isEqualToIgnoringGivenFields(contaPagar.getContaBancaria(), IGNORED_FIELDS);
		
		
		assertThat(actual.getCartaoCredito().getId()).isNotNull();
		assertThat(actual.getCartaoCredito()).isEqualToIgnoringGivenFields(contaPagar.getCartaoCredito(), IGNORED_FIELDS);
		
		
		assertThat(actual.getFornecedor().getId()).isNotNull();
		assertThat(actual.getFornecedor()).isEqualToIgnoringGivenFields(contaPagar.getFornecedor(), IGNORED_FIELDS);
		
		
	}
	
	@Test
	public void testCreateWithOnlyRecairedFields() throws Exception {
		ContaPagar contaPagar = new ContaPagar();
		
		contaPagar.setId(java.util.UUID.randomUUID());
		
		PlanoContaEntity planoContaEntityParam = newPlanoContaEntity();
		PlanoContaLookupResult planoContas = newPlanoContaLookupResult(planoContaEntityParam);
		contaPagar.setPlanoContas(planoContas);
		
		contaPagar.setDescricao(generateRandomString(255));
		contaPagar.setDataVencimento(getNextDate());
		contaPagar.setValor(new java.math.BigDecimal("31930.30105"));
		contaPagar.setFormaPagamento(FormaPagamento.DINHEIRO);
		ContaPagarEntity contaPagarEntity = contaPagarService.create(contaPagarDTOConverter.convertDtoToEntity(contaPagar));
		em.flush();
		verify(publisher, times(0)).publish(any());
		ContaPagar actual = contaPagarDTOConverter.convertEntityToDto(contaPagarEntity);
		
		
		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isNotNull();
		assertThat(actual).isEqualToIgnoringGivenFields(contaPagar, IGNORED_FIELDS);
		
		
		assertThat(actual.getPlanoContas().getId()).isNotNull();
		assertThat(actual.getPlanoContas()).isEqualToIgnoringGivenFields(contaPagar.getPlanoContas(), IGNORED_FIELDS);
		
		assertThat(actual.getContaBancaria()).isNull();
		assertThat(actual.getCartaoCredito()).isNull();
		assertThat(actual.getFornecedor()).isNull();
		
	}
	// END CREATE TESTS
	
	// BEGIN READ TESTS
	
	@Test
	public void testRead1() {
		ContaPagarEntity expectedContaPagarEntity = newContaPagarEntity();
		java.util.UUID id = expectedContaPagarEntity.getId();
		ContaPagar expected = contaPagarDTOConverter.convertEntityToDto(expectedContaPagarEntity);
		ContaPagarEntity readContaPagarEntity = contaPagarService.read(id);
		ContaPagar actual = contaPagarDTOConverter.convertEntityToDto(readContaPagarEntity);
		
		assertThat(actual).isEqualToComparingFieldByField(expected);
		
	}
	// END READ TESTS
	
	// BEGIN UPDATE TESTS
	
	@Test
	public void testUpdateWithAllFields() throws Exception {
		ContaPagarEntity oldContaPagarEntity = newContaPagarEntity();
		java.util.UUID id = oldContaPagarEntity.getId();
				
		ContaPagar contaPagar = new ContaPagar();
		contaPagar.setId(id);
		
		
		PlanoContaEntity planoContaEntityParam = newPlanoContaEntity();
		PlanoContaLookupResult planoContas = newPlanoContaLookupResult(planoContaEntityParam);
		contaPagar.setPlanoContas(planoContas);
		
		contaPagar.setDescricao(generateRandomString(255));
		contaPagar.setDataVencimento(getNextDate());
		contaPagar.setValor(new java.math.BigDecimal("30057.18033"));
		contaPagar.setFormaPagamento(FormaPagamento.DINHEIRO);
		
		ContaBancariaEntity contaBancariaEntityParam = newContaBancariaEntity();
		ContaBancariaLookupResult contaBancaria = newContaBancariaLookupResult(contaBancariaEntityParam);
		contaPagar.setContaBancaria(contaBancaria);
		
		
		CartaoCreditoEntity cartaoCreditoEntityParam = newCartaoCreditoEntity();
		CartaoCreditoLookupResult cartaoCredito = newCartaoCreditoLookupResult(cartaoCreditoEntityParam);
		contaPagar.setCartaoCredito(cartaoCredito);
		
		contaPagar.setOutrosDescricao(generateRandomString(255));
		contaPagar.setDataPagamento(getNextDate());
		contaPagar.setValorDesconto(new java.math.BigDecimal("26885.23281"));
		contaPagar.setValorMulta(new java.math.BigDecimal("21246.15845"));
		contaPagar.setValorJuros(new java.math.BigDecimal("28600.16330"));
		contaPagar.setValorAcrescimos(new java.math.BigDecimal("24838.30524"));
		contaPagar.setValorPago(new java.math.BigDecimal("8755.11169"));
		
		FornecedorEntity fornecedorEntityParam = newFornecedorEntity();
		FornecedorLookupResult fornecedor = newFornecedorLookupResult(fornecedorEntityParam);
		contaPagar.setFornecedor(fornecedor);
		
		contaPagar.setNumDocumento(generateRandomString(255));
		contaPagar.setIdConcBancaria(generateRandomString(255));
		contaPagar.setHistConcBancaria(generateRandomString(255));
		contaPagar.setNumDocConcBancaria(generateRandomString(255));
		contaPagar.setObservacoes(generateRandomString(1000));
		contaPagar.setAgrupador(generateRandomString(255));
		ContaPagarEntity contaPagarEntity = contaPagarService.update(id, contaPagarDTOConverter.convertDtoToEntity(contaPagar));
		em.flush();
		verify(publisher, times(0)).publish(any());
		
		ContaPagar actual = contaPagarDTOConverter.convertEntityToDto(contaPagarEntity);
		
		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isNotNull();
		assertThat(actual).isEqualToIgnoringGivenFields(contaPagar, IGNORED_FIELDS);
		
		
		assertThat(actual.getPlanoContas().getId()).isNotNull();
		assertThat(actual.getPlanoContas()).isEqualToIgnoringGivenFields(contaPagar.getPlanoContas(), IGNORED_FIELDS);
		
		
		assertThat(actual.getContaBancaria().getId()).isNotNull();
		assertThat(actual.getContaBancaria()).isEqualToIgnoringGivenFields(contaPagar.getContaBancaria(), IGNORED_FIELDS);
		
		
		assertThat(actual.getCartaoCredito().getId()).isNotNull();
		assertThat(actual.getCartaoCredito()).isEqualToIgnoringGivenFields(contaPagar.getCartaoCredito(), IGNORED_FIELDS);
		
		
		assertThat(actual.getFornecedor().getId()).isNotNull();
		assertThat(actual.getFornecedor()).isEqualToIgnoringGivenFields(contaPagar.getFornecedor(), IGNORED_FIELDS);
		
		
	}
	
	@Test
	public void testUpdateWithOnlyRecairedFields() throws Exception {
		ContaPagarEntity oldContaPagarEntity = newContaPagarEntity();
		java.util.UUID id = oldContaPagarEntity.getId();
				
		ContaPagar contaPagar = new ContaPagar();
		contaPagar.setId(id);
		
		
		PlanoContaEntity planoContaEntityParam = newPlanoContaEntity();
		PlanoContaLookupResult planoContas = newPlanoContaLookupResult(planoContaEntityParam);
		contaPagar.setPlanoContas(planoContas);
		
		contaPagar.setDescricao(generateRandomString(255));
		contaPagar.setDataVencimento(getNextDate());
		contaPagar.setValor(new java.math.BigDecimal("24427.6238"));
		contaPagar.setFormaPagamento(FormaPagamento.DINHEIRO);
		ContaPagarEntity contaPagarEntity = contaPagarService.update(id, contaPagarDTOConverter.convertDtoToEntity(contaPagar));
		em.flush();
		verify(publisher, times(0)).publish(any());
		
		ContaPagar actual = contaPagarDTOConverter.convertEntityToDto(contaPagarEntity);
		
		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isNotNull();
		assertThat(actual).isEqualToIgnoringGivenFields(contaPagar, IGNORED_FIELDS);
		
		
		assertThat(actual.getPlanoContas().getId()).isNotNull();
		assertThat(actual.getPlanoContas()).isEqualToIgnoringGivenFields(contaPagar.getPlanoContas(), IGNORED_FIELDS);
		
		assertThat(actual.getContaBancaria()).isNull();
		assertThat(actual.getCartaoCredito()).isNull();
		assertThat(actual.getFornecedor()).isNull();
		
	}
	// END UPDATE TESTS
	
	// BEGIN DELETE TESTS
	
	@Test
	public void testDelete1() {
		ContaPagarEntity expected = newContaPagarEntity();
		java.util.UUID id = expected.getId();
		
		
		expected = em.find(ContaPagarEntity.class, id);
		assertThat(expected).isNotNull();
		contaPagarService.delete(id);
		verify(publisher, times(0)).publish(any());
		
		expected = em.find(ContaPagarEntity.class, id);
		assertThat(expected).isNull();
	}
	// END DELETE TESTS
	
	// BEGIN LIST TESTS
	
	@Test
	public void testList_FilteringByDescricao() {
		// Reset lastDate field to start LocalDate fields with today in this test. 
		resetNextDate();
		
		// Generate 33 records of data for ContaPagarEntity for this test.
		List<ContaPagarEntity> testData = new ArrayList<>();
		final int lastRecord = 33;
		final int firstRecord = 1;
		for (int i = firstRecord; i <= lastRecord; i++) {
			testData.add(newContaPagarEntity());
		}
		
		// Check if 33 records of ContaPagarEntity was generated.
		long count = contaPagarBaseRepository.count();
		assertThat(count).isEqualTo(lastRecord);
		
		// Creates a list filter for entity ContaPagar.
		ContaPagarListFilter listFilter = new ContaPagarListFilter();
		
		// Extracts 7 records of ContaPagarEntity randomly from testData.
		final int resultSize = 7;
		List<ContaPagarEntity> filterTestData = getRandomItemsOf(testData, resultSize);
		
		// Extracts a list with only ContaPagarEntity.descricao field and configure this list as a filter.
		List<String> descricaoListFilter = filterTestData.stream().map(ContaPagarEntity::getDescricao).collect(Collectors.toList());
		listFilter.setDescricao(descricaoListFilter);
		
		// Generates a pageable configuration, without sorting.
		int pageIndex = 0; // First page starts at index zero.
		int size = 33; // Max of 33 records per page.
		Pageable pageable = PageRequest.of(pageIndex, size);
		// Call service list method.
		Page<ContaPagarEntity> page = contaPagarService.list(listFilter, pageable);
		
		// Converts found entities to DTOs and mount the result page.
		List<ContaPagar> content = page.getContent().stream().map(it -> contaPagarDTOConverter.convertEntityToDto(it)).collect(Collectors.toList());
		PageResult<ContaPagar> pageResult = new PageResult<>(content, page.getNumber(), page.getSize(), page.getTotalElements());
		
		// Asserts that result has size 7, in any order and has only rows with descricaoListFilter elements based on descricao field.
		assertThat(pageResult.getContent())
		.hasSize(7)
		.extracting(ContaPagar::getDescricao)
		.containsExactlyInAnyOrderElementsOf(descricaoListFilter);
		
		// Asserts some page result elements.
		assertThat(pageResult.getNumber()).isEqualTo(pageIndex);
		assertThat(pageResult.getNumberOfElements()).isEqualTo(7);
		assertThat(pageResult.getTotalElements()).isEqualTo(7);
		assertThat(pageResult.getTotalPages()).isEqualTo(1);
		
	}
	
	@Test
	public void testList_FilteringByDescricaoWithoutResults() {
		// Reset lastDate field to start LocalDate fields with today in this test. 
		resetNextDate();
					
		// Generate 33 records of data for ContaPagarEntity for this test.
		List<ContaPagarEntity> testData = new ArrayList<>();
		final int lastRecord = 33;
		final int firstRecord = 1;
		for (int i = firstRecord; i <= lastRecord; i++) {
			testData.add(newContaPagarEntity());
		}
		
		// Check if 33 records of ContaPagarEntity was generated.
		long count = contaPagarBaseRepository.count();
		assertThat(count).isEqualTo(lastRecord);
		
		// Creates a list filter for entity ContaPagar.
		ContaPagarListFilter listFilter = new ContaPagarListFilter();
		
		// Generates a list with only ContaPagarEntity.descricao field with 1 not found data in the database and configure this list as a filter.
		List<String> descricaoListFilter = Arrays.asList(generateRandomString(255));
		listFilter.setDescricao(descricaoListFilter);
		
		// Generates a pageable configuration, without sorting.
		int pageIndex = 0; // First page starts at index zero.
		int size = 33; // Max of 33 records per page.
		Pageable pageable = PageRequest.of(pageIndex, size);
		// Call service list method.
		Page<ContaPagarEntity> page = contaPagarService.list(listFilter, pageable);
		
		// Converts found entities to DTOs and mount the result page.
		List<ContaPagar> content = page.getContent().stream().map(it -> contaPagarDTOConverter.convertEntityToDto(it)).collect(Collectors.toList());
		PageResult<ContaPagar> pageResult = new PageResult<>(content, page.getNumber(), page.getSize(), page.getTotalElements());
		
		// Asserts that result has size 0 for unknown descricao field.
		assertThat(pageResult.getContent()).hasSize(0);
		
	}
	
	@Test
	public void testList_SortingByDataVencimento() {
		// Reset lastDate field to start LocalDate fields with today in this test. 
		resetNextDate();
		
		// Generate 10 records of data for ContaPagarEntity for this test.
		List<ContaPagarEntity> testData = new ArrayList<>();
		final int lastRecord = 10;
		final int firstRecord = 1;
		for (int i = firstRecord; i <= lastRecord; i++) {
			testData.add(newContaPagarEntity());
		}
		
		// Check if 10 records of ContaPagarEntity was generated.
		long count = contaPagarBaseRepository.count();
		assertThat(count).isEqualTo(lastRecord);
		
		// Creates a list filter for entity ContaPagar.
		ContaPagarListFilter listFilter = new ContaPagarListFilter();
		
		// Generates a pageable configuration, with sorting.
		Sort sort = Sort.by("dataVencimento").ascending(); // select ... order by dataVencimento ascending
		int pageIndex = 0; // First page starts at index zero.
		int size = 10; // Max of 10 records per page.
		Pageable pageable = PageRequest.of(pageIndex, size, sort);
		
		// Call service list method.
		Page<ContaPagarEntity> page = contaPagarService.list(listFilter, pageable);
		
		// Converts found entities to DTOs and mount the result page.
		List<ContaPagar> content = page.getContent().stream().map(it -> contaPagarDTOConverter.convertEntityToDto(it)).collect(Collectors.toList());
		PageResult<ContaPagar> pageResult = new PageResult<>(content, page.getNumber(), page.getSize(), page.getTotalElements());
		
		// Extracts a list with only ContaPagarEntity.dataVencimento fields.
		List<java.time.LocalDate> dataVencimentoTestDataList = testData.stream().map(ContaPagarEntity::getDataVencimento).collect(Collectors.toList());
		
		// Sort dataVencimento in ascending order.
		Collections.sort(dataVencimentoTestDataList);
	
		// Asserts that result has size 10 in a specific order.
		assertThat(pageResult.getContent())
		.hasSize(10)
		.extracting(ContaPagar::getDataVencimento)
		.containsExactlyElementsOf(dataVencimentoTestDataList);
		
		// Asserts some page result elements.
		assertThat(pageResult.getNumber()).isEqualTo(pageIndex);
		assertThat(pageResult.getNumberOfElements()).isEqualTo(10);
		assertThat(pageResult.getTotalElements()).isEqualTo(10);
		assertThat(pageResult.getTotalPages()).isEqualTo(1);
		
	}
	// END LIST TESTS
	
	// BEGIN Autocomplete TESTS
	@Test
	public void testAutoComplete() {
		// Reset lastDate field to start LocalDate fields with today in this test. 
		resetNextDate();
					
		// Generate 33 records of data for ContaPagarEntity for this test.
		List<ContaPagarEntity> testData = new ArrayList<>();
		final int lastRecord = 33;
		final int firstRecord = 1;
		for (int i = firstRecord; i <= lastRecord; i++) {
			testData.add(newContaPagarEntity());
		}
		
		// Check if 33 records of ContaPagarEntity was generated.
		long count = contaPagarBaseRepository.count();
		assertThat(count).isEqualTo(lastRecord);
		
		// Extracts 1 records of ContaPagarEntity randomly from testData.
		final int resultSize = 1;
		List<ContaPagarEntity> filterTestData = getRandomItemsOf(testData, resultSize);
		
		// Extracts a list with only ContaPagarEntity.descricao field and configure this list as a filter.
		List<String> descricaoListFilter = filterTestData.stream().map(ContaPagarEntity::getDescricao).collect(Collectors.toList());
		// Mount the autocomplete query expression and call it.
		String query = descricaoListFilter.get(0);
		Collection<ContaPagarAutoComplete> result = contaPagarService.autoComplete(query);
		
		// Assert ContaPagarAutoComplete results.
		assertThat(result).isNotNull().hasSize(1)
		.extracting(ContaPagarAutoComplete::getDescricao)
		.containsExactlyInAnyOrderElementsOf(descricaoListFilter);
	}
	
	// END Autocomplete TESTS
	
	// BEGIN ListFilter Autocomplete TESTS
	
	@Test
	public void testContaPagarDescricaoAutoComplete() {
		// Reset lastDate field to start LocalDate fields with today in this test. 
		resetNextDate();
					
		// Generate 33 records of data for ContaPagarEntity for this test.
		List<ContaPagarEntity> testData = new ArrayList<>();
		final int lastRecord = 33;
		final int firstRecord = 1;
		for (int i = firstRecord; i <= lastRecord; i++) {
			testData.add(newContaPagarEntity());
		}
		
		// Check if 33 records of ContaPagarEntity was generated.
		long count = contaPagarBaseRepository.count();
		assertThat(count).isEqualTo(lastRecord);
		
		// Extracts 1 records of ContaPagarEntity randomly from testData.
		final int resultSize = 1;
		List<ContaPagarEntity> filterTestData = getRandomItemsOf(testData, resultSize);
		
		// Extracts a list with only ContaPagarEntity.descricao field and configure this list as a filter.
		List<String> descricaoListFilter = filterTestData.stream().map(ContaPagarEntity::getDescricao).collect(Collectors.toList());
		// Mount the autocomplete query expression and call it.
		String query = descricaoListFilter.get(0);
		Collection<ContaPagarDescricaoAutoComplete> result = contaPagarService.contaPagarDescricaoAutoComplete(query);
		// Assert ContaPagarDescricaoAutoComplete results.
		assertThat(result).isNotNull().hasSize(1)
		.extracting(ContaPagarDescricaoAutoComplete::getDescricao)
		.containsExactlyInAnyOrderElementsOf(descricaoListFilter);
	}
	
	
	@Test
	public void testContaPagarHistConcBancariaAutoComplete() {
		// Reset lastDate field to start LocalDate fields with today in this test. 
		resetNextDate();
					
		// Generate 33 records of data for ContaPagarEntity for this test.
		List<ContaPagarEntity> testData = new ArrayList<>();
		final int lastRecord = 33;
		final int firstRecord = 1;
		for (int i = firstRecord; i <= lastRecord; i++) {
			testData.add(newContaPagarEntity());
		}
		
		// Check if 33 records of ContaPagarEntity was generated.
		long count = contaPagarBaseRepository.count();
		assertThat(count).isEqualTo(lastRecord);
		
		// Extracts 1 records of ContaPagarEntity randomly from testData.
		final int resultSize = 1;
		List<ContaPagarEntity> filterTestData = getRandomItemsOf(testData, resultSize);
		
		// Extracts a list with only ContaPagarEntity.histConcBancaria field and configure this list as a filter.
		List<String> histConcBancariaListFilter = filterTestData.stream().map(ContaPagarEntity::getHistConcBancaria).collect(Collectors.toList());
		// Mount the autocomplete query expression and call it.
		String query = histConcBancariaListFilter.get(0);
		Collection<ContaPagarHistConcBancariaAutoComplete> result = contaPagarService.contaPagarHistConcBancariaAutoComplete(query);
		// Assert ContaPagarHistConcBancariaAutoComplete results.
		assertThat(result).isNotNull().hasSize(1)
		.extracting(ContaPagarHistConcBancariaAutoComplete::getHistConcBancaria)
		.containsExactlyInAnyOrderElementsOf(histConcBancariaListFilter);
	}
	
	
	@Test
	public void testContaPagarAgrupadorAutoComplete() {
		// Reset lastDate field to start LocalDate fields with today in this test. 
		resetNextDate();
					
		// Generate 33 records of data for ContaPagarEntity for this test.
		List<ContaPagarEntity> testData = new ArrayList<>();
		final int lastRecord = 33;
		final int firstRecord = 1;
		for (int i = firstRecord; i <= lastRecord; i++) {
			testData.add(newContaPagarEntity());
		}
		
		// Check if 33 records of ContaPagarEntity was generated.
		long count = contaPagarBaseRepository.count();
		assertThat(count).isEqualTo(lastRecord);
		
		// Extracts 1 records of ContaPagarEntity randomly from testData.
		final int resultSize = 1;
		List<ContaPagarEntity> filterTestData = getRandomItemsOf(testData, resultSize);
		
		// Extracts a list with only ContaPagarEntity.agrupador field and configure this list as a filter.
		List<String> agrupadorListFilter = filterTestData.stream().map(ContaPagarEntity::getAgrupador).collect(Collectors.toList());
		// Mount the autocomplete query expression and call it.
		String query = agrupadorListFilter.get(0);
		Collection<ContaPagarAgrupadorAutoComplete> result = contaPagarService.contaPagarAgrupadorAutoComplete(query);
		// Assert ContaPagarAgrupadorAutoComplete results.
		assertThat(result).isNotNull().hasSize(1)
		.extracting(ContaPagarAgrupadorAutoComplete::getAgrupador)
		.containsExactlyInAnyOrderElementsOf(agrupadorListFilter);
	}
	
	// END ListFilter Autocomplete TESTS
	
	// BEGIN Relationships Autocomplete TESTS
	
	@Test
	public void testContaPagarPlanoContasAutoComplete() {
		// Reset lastDate field to start LocalDate fields with today in this test. 
		resetNextDate();
					
		// Generate 33 records of data for PlanoContaEntity for this test.
		List<PlanoContaEntity> testData = new ArrayList<>();
		final int lastRecord = 33;
		final int firstRecord = 1;
		for (int i = firstRecord; i <= lastRecord; i++) {
			testData.add(newPlanoContaEntity());
		}
		
		// Check if 33 records of PlanoContaEntity was generated.
		long count = planoContaRepository.count();
		assertThat(count).isEqualTo(lastRecord);
		
		// Extracts 1 records of PlanoContaEntity randomly from testData.
		final int resultSize = 1;
		List<PlanoContaEntity> filterTestData = getRandomItemsOf(testData, resultSize);
		
		// Extracts a list with only PlanoContaEntity.codigo field and configure this list as a filter.
		List<String> codigoListFilter = filterTestData.stream().map(PlanoContaEntity::getCodigo).collect(Collectors.toList());
		String query = codigoListFilter.get(0);
		
		Collection<PlanoContaAutoComplete> result = contaPagarService.planoContaPlanoContasAutoComplete(query);
		
		assertThat(result).isNotNull().hasSize(1)
		.extracting(PlanoContaAutoComplete::getCodigo)
		.containsExactlyInAnyOrderElementsOf(codigoListFilter);
	}
	
	
	@Test
	public void testContaPagarContaBancariaAutoComplete() {
		// Reset lastDate field to start LocalDate fields with today in this test. 
		resetNextDate();
					
		// Generate 33 records of data for ContaBancariaEntity for this test.
		List<ContaBancariaEntity> testData = new ArrayList<>();
		final int lastRecord = 33;
		final int firstRecord = 1;
		for (int i = firstRecord; i <= lastRecord; i++) {
			testData.add(newContaBancariaEntity());
		}
		
		// Check if 33 records of ContaBancariaEntity was generated.
		long count = contaBancariaRepository.count();
		assertThat(count).isEqualTo(lastRecord);
		
		// Extracts 1 records of ContaBancariaEntity randomly from testData.
		final int resultSize = 1;
		List<ContaBancariaEntity> filterTestData = getRandomItemsOf(testData, resultSize);
		
		// Extracts a list with only ContaBancariaEntity.nomeTitular field and configure this list as a filter.
		List<String> nomeTitularListFilter = filterTestData.stream().map(ContaBancariaEntity::getNomeTitular).collect(Collectors.toList());
		String query = nomeTitularListFilter.get(0);
		
		Collection<ContaBancariaAutoComplete> result = contaPagarService.contaBancariaContaBancariaAutoComplete(query);
		
		assertThat(result).isNotNull().hasSize(1)
		.extracting(ContaBancariaAutoComplete::getNomeTitular)
		.containsExactlyInAnyOrderElementsOf(nomeTitularListFilter);
	}
	
	
	@Test
	public void testContaPagarCartaoCreditoAutoComplete() {
		// Reset lastDate field to start LocalDate fields with today in this test. 
		resetNextDate();
					
		// Generate 33 records of data for CartaoCreditoEntity for this test.
		List<CartaoCreditoEntity> testData = new ArrayList<>();
		final int lastRecord = 33;
		final int firstRecord = 1;
		for (int i = firstRecord; i <= lastRecord; i++) {
			testData.add(newCartaoCreditoEntity());
		}
		
		// Check if 33 records of CartaoCreditoEntity was generated.
		long count = cartaoCreditoRepository.count();
		assertThat(count).isEqualTo(lastRecord);
		
		// Extracts 1 records of CartaoCreditoEntity randomly from testData.
		final int resultSize = 1;
		List<CartaoCreditoEntity> filterTestData = getRandomItemsOf(testData, resultSize);
		
		// Extracts a list with only CartaoCreditoEntity.nomeTitular field and configure this list as a filter.
		List<String> nomeTitularListFilter = filterTestData.stream().map(CartaoCreditoEntity::getNomeTitular).collect(Collectors.toList());
		String query = nomeTitularListFilter.get(0);
		
		Collection<CartaoCreditoAutoComplete> result = contaPagarService.cartaoCreditoCartaoCreditoAutoComplete(query);
		
		assertThat(result).isNotNull().hasSize(1)
		.extracting(CartaoCreditoAutoComplete::getNomeTitular)
		.containsExactlyInAnyOrderElementsOf(nomeTitularListFilter);
	}
	
	
	@Test
	public void testContaPagarFornecedorAutoComplete() {
		// Reset lastDate field to start LocalDate fields with today in this test. 
		resetNextDate();
					
		// Generate 33 records of data for FornecedorEntity for this test.
		List<FornecedorEntity> testData = new ArrayList<>();
		final int lastRecord = 33;
		final int firstRecord = 1;
		for (int i = firstRecord; i <= lastRecord; i++) {
			testData.add(newFornecedorEntity());
		}
		
		// Check if 33 records of FornecedorEntity was generated.
		long count = fornecedorRepository.count();
		assertThat(count).isEqualTo(lastRecord);
		
		// Extracts 1 records of FornecedorEntity randomly from testData.
		final int resultSize = 1;
		List<FornecedorEntity> filterTestData = getRandomItemsOf(testData, resultSize);
		
		// Extracts a list with only FornecedorEntity.nome field and configure this list as a filter.
		List<String> nomeListFilter = filterTestData.stream().map(FornecedorEntity::getNome).collect(Collectors.toList());
		String query = nomeListFilter.get(0);
		
		Collection<FornecedorAutoComplete> result = contaPagarService.fornecedorFornecedorAutoComplete(query);
		
		assertThat(result).isNotNull().hasSize(1)
		.extracting(FornecedorAutoComplete::getNome)
		.containsExactlyInAnyOrderElementsOf(nomeListFilter);
	}
	
	// END Relationships Autocomplete TESTS
	
	// BEGIN tests for Sum Fields
	
	@Test
	public void testGetContaPagarSumFields() {
		// Reset lastDate field to start LocalDate fields with today in this test. 
		resetNextDate();
		
		// Generate 2 records of data for ContaPagarEntity for this test.
		List<ContaPagarEntity> testData = new ArrayList<>();
		final int lastRecord = 2;
		final int firstRecord = 1;
		for (int i = firstRecord; i <= lastRecord; i++) {
			testData.add(newContaPagarEntity());
		}
		
		// Check if 2 records of ContaPagarEntity was generated.
		long count = contaPagarBaseRepository.count();
		assertThat(count).isEqualTo(lastRecord);
		
		// Creates a list filter for entity ContaPagar.
		ContaPagarListFilter listFilter = new ContaPagarListFilter();
		
		// Extracts 2 records of ContaPagarEntity randomly from testData.
		final int resultSize = 2;
		List<ContaPagarEntity> filterTestData = getRandomItemsOf(testData, resultSize);
		
		ContaPagarSumFields expected = new ContaPagarSumFields();
		
		BigDecimal sumValor = filterTestData.stream().map(it -> it.getValor()).reduce(BigDecimal.ZERO, BigDecimal::add);
		expected.setSumValor(sumValor);
		
		BigDecimal sumValorDesconto = filterTestData.stream().map(it -> it.getValorDesconto()).reduce(BigDecimal.ZERO, BigDecimal::add);
		expected.setSumValorDesconto(sumValorDesconto);
		
		BigDecimal sumValorMulta = filterTestData.stream().map(it -> it.getValorMulta()).reduce(BigDecimal.ZERO, BigDecimal::add);
		expected.setSumValorMulta(sumValorMulta);
		
		BigDecimal sumValorJuros = filterTestData.stream().map(it -> it.getValorJuros()).reduce(BigDecimal.ZERO, BigDecimal::add);
		expected.setSumValorJuros(sumValorJuros);
		
		BigDecimal sumValorAcrescimos = filterTestData.stream().map(it -> it.getValorAcrescimos()).reduce(BigDecimal.ZERO, BigDecimal::add);
		expected.setSumValorAcrescimos(sumValorAcrescimos);
		
		BigDecimal sumValorPago = filterTestData.stream().map(it -> it.getValorPago()).reduce(BigDecimal.ZERO, BigDecimal::add);
		expected.setSumValorPago(sumValorPago);
		ContaPagarSumFields actual = contaPagarService.getContaPagarSumFields(listFilter);
		
		assertThat(actual).isEqualToComparingFieldByField(expected);
	}
	// END tests for Sum Fields
	
	// BEGIN tests for Sum Fields
	
	@Test
	public void testActionFazerCopiasContaPagar_1Copy() {
		ContaPagarEntity baseEntity = newContaPagarEntity();
		
		ContaPagarMakeCopies contaPagarMakeCopies = new ContaPagarMakeCopies();
		contaPagarMakeCopies.setId(baseEntity.getId());
		contaPagarMakeCopies.setAgrupador(baseEntity.getAgrupador());
		contaPagarMakeCopies.setNumberOfCopies(1L);
		contaPagarMakeCopies.setReferenceFieldInterval(30L);
		contaPagarService.actionFazerCopiasContaPagar(contaPagarMakeCopies);
		
		// Mount expected
		LocalDate lastDate = baseEntity.getDataVencimento();
		List<ContaPagarEntity> copies = new ArrayList<>(2);
		long interval = contaPagarMakeCopies.getReferenceFieldInterval();
		int fixedDay = lastDate.getDayOfMonth();
		int fixedDayCopy = fixedDay;
		for (int i = 0; i < contaPagarMakeCopies.getNumberOfCopies(); i++) {
			ContaPagarEntity copiedEntity = baseEntity.clone();
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
		
		copies.add(baseEntity);
		
		List<ContaPagarEntity> actual = contaPagarBaseRepository.findAll();
		
		actual.sort(Comparator.comparing(ContaPagarEntity::getDataVencimento));
		copies.sort(Comparator.comparing(ContaPagarEntity::getDataVencimento));
		
		assertThat(actual).hasSize(2);
		assertThat(copies).hasSize(2);
		
		for (int i = 0; i < actual.size(); i++) {
			assertThat(actual.get(i)).isEqualToIgnoringGivenFields(copies.get(i), IGNORED_FIELDS);
		}
		
	}
	
	@Test
	public void testActionFazerCopiasContaPagar_11Copies() {
		ContaPagarEntity baseEntity = newContaPagarEntity();
					
		ContaPagarMakeCopies contaPagarMakeCopies = new ContaPagarMakeCopies();
		contaPagarMakeCopies.setId(baseEntity.getId());
		contaPagarMakeCopies.setAgrupador(baseEntity.getAgrupador());
		contaPagarMakeCopies.setNumberOfCopies(11L);
		contaPagarMakeCopies.setReferenceFieldInterval(30L);
		
		contaPagarService.actionFazerCopiasContaPagar(contaPagarMakeCopies);
		
		// Mount expected
		LocalDate lastDate = baseEntity.getDataVencimento();
		List<ContaPagarEntity> copies = new ArrayList<>(12);
		long interval = contaPagarMakeCopies.getReferenceFieldInterval();
		int fixedDay = lastDate.getDayOfMonth();
		int fixedDayCopy = fixedDay;
		for (int i = 0; i < contaPagarMakeCopies.getNumberOfCopies(); i++) {
			ContaPagarEntity copiedEntity = baseEntity.clone();
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
		
		copies.add(baseEntity);
		
		List<ContaPagarEntity> actual = contaPagarBaseRepository.findAll();
		
		actual.sort(Comparator.comparing(ContaPagarEntity::getDataVencimento));
		copies.sort(Comparator.comparing(ContaPagarEntity::getDataVencimento));
		
		assertThat(actual).hasSize(12);
		assertThat(copies).hasSize(12);
		
		for (int i = 0; i < actual.size(); i++) {
			assertThat(actual.get(i)).isEqualToIgnoringGivenFields(copies.get(i), IGNORED_FIELDS);
		}
	}
	// END tests for Sum Fields
	
	// BEGIN TESTS DEPENDENCIES
	
	
	protected ContaPagarEntity newContaPagarEntity() {
		ContaPagarEntity contaPagarEntity = new ContaPagarEntity();
		
		contaPagarEntity.setPlanoContas(newPlanoContaEntity());
		contaPagarEntity.setDescricao(generateRandomString(255));
		contaPagarEntity.setDataVencimento(getNextDate());
		contaPagarEntity.setValor(new java.math.BigDecimal("32577.16604"));
		contaPagarEntity.setFormaPagamento(FormaPagamento.DINHEIRO);
		contaPagarEntity.setContaBancaria(newContaBancariaEntity());
		contaPagarEntity.setCartaoCredito(newCartaoCreditoEntity());
		contaPagarEntity.setOutrosDescricao(generateRandomString(255));
		contaPagarEntity.setDataPagamento(getNextDate());
		contaPagarEntity.setValorDesconto(new java.math.BigDecimal("18714.27502"));
		contaPagarEntity.setValorMulta(new java.math.BigDecimal("18255.29497"));
		contaPagarEntity.setValorJuros(new java.math.BigDecimal("7193.18847"));
		contaPagarEntity.setValorAcrescimos(new java.math.BigDecimal("20607.479"));
		contaPagarEntity.setValorPago(new java.math.BigDecimal("442.16310"));
		contaPagarEntity.setFornecedor(newFornecedorEntity());
		contaPagarEntity.setNumDocumento(generateRandomString(255));
		contaPagarEntity.setIdConcBancaria(generateRandomString(255));
		contaPagarEntity.setHistConcBancaria(generateRandomString(255));
		contaPagarEntity.setNumDocConcBancaria(generateRandomString(255));
		contaPagarEntity.setObservacoes(generateRandomString(1000));
		contaPagarEntity.setAgrupador(generateRandomString(255));
		
		contaPagarEntity = em.persistAndFlush(contaPagarEntity);
		return contaPagarEntity;
	}
	
	
	protected ContaPagarLookupResult newContaPagarLookupResult(ContaPagarEntity contaPagarEntity) {
		ContaPagarLookupResult contaPagar = new ContaPagarLookupResult();
		
		contaPagar.setId(contaPagarEntity.getId());
		contaPagar.setDescricao(contaPagarEntity.getDescricao());
		
		return contaPagar;
	}
	
	
	protected PlanoContaEntity newPlanoContaEntity() {
		PlanoContaEntity planoContaEntity = new PlanoContaEntity();
		
		planoContaEntity.setId(java.util.UUID.randomUUID());
		planoContaEntity.setCodigo(generateRandomString(255));
		planoContaEntity.setDescricao(generateRandomString(255));
		planoContaEntity.setTipoFinanceiro(TipoPlanoContaFinanceiro.DESPESA);
		planoContaEntity.setTipoReceitaDespesa(TipoReceitaDespesa.VARIAVEL);
		planoContaEntity.setPlanoContaPai(null);
		planoContaEntity.setAtivo(true);
		planoContaEntity.setDeleted(false);
		
		planoContaEntity = em.persistAndFlush(planoContaEntity);
		return planoContaEntity;
	}
	
	
	protected PlanoContaLookupResult newPlanoContaLookupResult(PlanoContaEntity planoContaEntity) {
		PlanoContaLookupResult planoConta = new PlanoContaLookupResult();
		
		planoConta.setId(planoContaEntity.getId());
		planoConta.setCodigo(planoContaEntity.getCodigo());
		planoConta.setDescricao(planoContaEntity.getDescricao());
		
		return planoConta;
	}
	
	
	protected ContaBancariaEntity newContaBancariaEntity() {
		ContaBancariaEntity contaBancariaEntity = new ContaBancariaEntity();
		
		contaBancariaEntity.setId(java.util.UUID.randomUUID());
		contaBancariaEntity.setNomeTitular(generateRandomString(255));
		contaBancariaEntity.setAgencia(newAgenciaBancariaEntity());
		contaBancariaEntity.setTipoContaBancaria(TipoContaBancaria.CONTA_CORRENTE);
		contaBancariaEntity.setNumeroConta(generateRandomString(30));
		contaBancariaEntity.setDigito(generateRandomString(10));
		contaBancariaEntity.setDataValidade(getNextDate());
		contaBancariaEntity.setAtivo(true);
		contaBancariaEntity.setDeleted(false);
		
		contaBancariaEntity = em.persistAndFlush(contaBancariaEntity);
		return contaBancariaEntity;
	}
	
	
	protected ContaBancariaLookupResult newContaBancariaLookupResult(ContaBancariaEntity contaBancariaEntity) {
		ContaBancariaLookupResult contaBancaria = new ContaBancariaLookupResult();
		
		contaBancaria.setId(contaBancariaEntity.getId());
		contaBancaria.setNomeTitular(contaBancariaEntity.getNomeTitular());
		contaBancaria.setNumeroConta(contaBancariaEntity.getNumeroConta());
		
		return contaBancaria;
	}
	
	
	protected AgenciaBancariaEntity newAgenciaBancariaEntity() {
		AgenciaBancariaEntity agenciaBancariaEntity = new AgenciaBancariaEntity();
		
		agenciaBancariaEntity.setId(java.util.UUID.randomUUID());
		agenciaBancariaEntity.setBanco(newBancoEntity());
		agenciaBancariaEntity.setNumeroAgencia(generateRandomString(50));
		agenciaBancariaEntity.setDigitoAgencia(generateRandomString(10));
		agenciaBancariaEntity.setEndereco(generateRandomString(255));
		agenciaBancariaEntity.setDeleted(false);
		
		agenciaBancariaEntity = em.persistAndFlush(agenciaBancariaEntity);
		return agenciaBancariaEntity;
	}
	
	
	protected AgenciaBancariaLookupResult newAgenciaBancariaLookupResult(AgenciaBancariaEntity agenciaBancariaEntity) {
		AgenciaBancariaLookupResult agenciaBancaria = new AgenciaBancariaLookupResult();
		
		agenciaBancaria.setId(agenciaBancariaEntity.getId());
		agenciaBancaria.setNumeroAgencia(agenciaBancariaEntity.getNumeroAgencia());
		agenciaBancaria.setDigitoAgencia(agenciaBancariaEntity.getDigitoAgencia());
		agenciaBancaria.setEndereco(agenciaBancariaEntity.getEndereco());
		
		return agenciaBancaria;
	}
	
	
	protected BancoEntity newBancoEntity() {
		BancoEntity bancoEntity = new BancoEntity();
		
		bancoEntity.setId(java.util.UUID.randomUUID());
		bancoEntity.setNumero(generateRandomString(20));
		bancoEntity.setNome(generateRandomString(255));
		bancoEntity.setDeleted(false);
		
		bancoEntity = em.persistAndFlush(bancoEntity);
		return bancoEntity;
	}
	
	
	protected BancoLookupResult newBancoLookupResult(BancoEntity bancoEntity) {
		BancoLookupResult banco = new BancoLookupResult();
		
		banco.setId(bancoEntity.getId());
		banco.setNumero(bancoEntity.getNumero());
		banco.setNome(bancoEntity.getNome());
		
		return banco;
	}
	
	
	protected CartaoCreditoEntity newCartaoCreditoEntity() {
		CartaoCreditoEntity cartaoCreditoEntity = new CartaoCreditoEntity();
		
		cartaoCreditoEntity.setId(java.util.UUID.randomUUID());
		cartaoCreditoEntity.setBanco(newBancoEntity());
		cartaoCreditoEntity.setNomeTitular(generateRandomString(255));
		cartaoCreditoEntity.setNumeroCartao(generateRandomString(50));
		cartaoCreditoEntity.setValidade(getNextDate());
		cartaoCreditoEntity.setValorLimite(new java.math.BigDecimal("5334.24473"));
		cartaoCreditoEntity.setBandeiraCartao(newBandeiraCartaoEntity());
		cartaoCreditoEntity.setAtivo(true);
		cartaoCreditoEntity.setDeleted(false);
		
		cartaoCreditoEntity = em.persistAndFlush(cartaoCreditoEntity);
		return cartaoCreditoEntity;
	}
	
	
	protected CartaoCreditoLookupResult newCartaoCreditoLookupResult(CartaoCreditoEntity cartaoCreditoEntity) {
		CartaoCreditoLookupResult cartaoCredito = new CartaoCreditoLookupResult();
		
		cartaoCredito.setId(cartaoCreditoEntity.getId());
		cartaoCredito.setNomeTitular(cartaoCreditoEntity.getNomeTitular());
		cartaoCredito.setNumeroCartao(cartaoCreditoEntity.getNumeroCartao());
		
		return cartaoCredito;
	}
	
	
	protected BandeiraCartaoEntity newBandeiraCartaoEntity() {
		BandeiraCartaoEntity bandeiraCartaoEntity = new BandeiraCartaoEntity();
		
		bandeiraCartaoEntity.setId(java.util.UUID.randomUUID());
		bandeiraCartaoEntity.setNomeBandeira(generateRandomString(255));
		bandeiraCartaoEntity.setDeleted(false);
		
		bandeiraCartaoEntity = em.persistAndFlush(bandeiraCartaoEntity);
		return bandeiraCartaoEntity;
	}
	
	
	protected BandeiraCartaoLookupResult newBandeiraCartaoLookupResult(BandeiraCartaoEntity bandeiraCartaoEntity) {
		BandeiraCartaoLookupResult bandeiraCartao = new BandeiraCartaoLookupResult();
		
		bandeiraCartao.setId(bandeiraCartaoEntity.getId());
		bandeiraCartao.setNomeBandeira(bandeiraCartaoEntity.getNomeBandeira());
		
		return bandeiraCartao;
	}
	
	
	protected FornecedorEntity newFornecedorEntity() {
		FornecedorEntity fornecedorEntity = new FornecedorEntity();
		
		fornecedorEntity.setId(java.util.UUID.randomUUID());
		fornecedorEntity.setTipoPessoa(TipoPessoa.PESSOA_JURIDICA);
		fornecedorEntity.setNome(generateRandomString(255));
		fornecedorEntity.setCnpjCPF(generateRandomString(255));
		fornecedorEntity.setAtivo(true);
		fornecedorEntity.setDeleted(false);
		
		fornecedorEntity = em.persistAndFlush(fornecedorEntity);
		return fornecedorEntity;
	}
	
	
	protected FornecedorLookupResult newFornecedorLookupResult(FornecedorEntity fornecedorEntity) {
		FornecedorLookupResult fornecedor = new FornecedorLookupResult();
		
		fornecedor.setId(fornecedorEntity.getId());
		fornecedor.setNome(fornecedorEntity.getNome());
		
		return fornecedor;
	}
	// END TESTS DEPENDENCIES

}