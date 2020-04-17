/**********************************************************************************************
Code generated by MKL Plug-in
Copyright: Kerubin - kerubin.platform@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.contapagarmultiple;

import br.com.kerubin.api.financeiro.contaspagar.entity.fornecedor.FornecedorEntity;
import br.com.kerubin.api.financeiro.contaspagar.entity.planoconta.PlanoContaEntity;
import br.com.kerubin.api.financeiro.contaspagar.entity.contabancaria.ContaBancariaEntity;
import br.com.kerubin.api.financeiro.contaspagar.entity.cartaocredito.CartaoCreditoEntity;
import br.com.kerubin.api.financeiro.contaspagar.entity.contapagar.ContaPagarEntity;
import br.com.kerubin.api.financeiro.contaspagar.entity.fornecedor.FornecedorLookupResult;
import br.com.kerubin.api.financeiro.contaspagar.entity.planoconta.PlanoContaLookupResult;
import br.com.kerubin.api.financeiro.contaspagar.entity.contabancaria.ContaBancariaLookupResult;
import br.com.kerubin.api.financeiro.contaspagar.entity.cartaocredito.CartaoCreditoLookupResult;
import br.com.kerubin.api.financeiro.contaspagar.entity.contapagar.ContaPagarLookupResult;
import br.com.kerubin.api.financeiro.contaspagar.FormaPagamento;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import javax.inject.Inject;
import br.com.kerubin.api.financeiro.contaspagar.entity.fornecedor.FornecedorRepository;
import br.com.kerubin.api.financeiro.contaspagar.entity.planoconta.PlanoContaRepository;
import br.com.kerubin.api.financeiro.contaspagar.entity.contabancaria.ContaBancariaRepository;
import br.com.kerubin.api.financeiro.contaspagar.entity.cartaocredito.CartaoCreditoRepository;
import br.com.kerubin.api.financeiro.contaspagar.entity.contapagar.ContaPagarBaseRepository;
import org.springframework.boot.test.mock.mockito.MockBean;
import br.com.kerubin.api.messaging.core.DomainEntityEventsPublisher;
import br.com.kerubin.api.financeiro.contaspagar.TipoPessoa;
import br.com.kerubin.api.financeiro.contaspagar.TipoPlanoContaFinanceiro;
import br.com.kerubin.api.financeiro.contaspagar.TipoReceitaDespesa;
import br.com.kerubin.api.financeiro.contaspagar.entity.agenciabancaria.AgenciaBancariaEntity;
import br.com.kerubin.api.financeiro.contaspagar.entity.agenciabancaria.AgenciaBancariaLookupResult;
import br.com.kerubin.api.financeiro.contaspagar.TipoContaBancaria;
import br.com.kerubin.api.financeiro.contaspagar.entity.banco.BancoEntity;
import br.com.kerubin.api.financeiro.contaspagar.entity.bandeiracartao.BandeiraCartaoEntity;
import br.com.kerubin.api.financeiro.contaspagar.entity.banco.BancoLookupResult;
import br.com.kerubin.api.financeiro.contaspagar.entity.bandeiracartao.BandeiraCartaoLookupResult;
import br.com.kerubin.api.financeiro.contaspagar.TipoPagamentoConta;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.ArgumentMatchers.any;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.Collection;
import br.com.kerubin.api.financeiro.contaspagar.entity.fornecedor.FornecedorAutoComplete;
import br.com.kerubin.api.financeiro.contaspagar.entity.planoconta.PlanoContaAutoComplete;
import br.com.kerubin.api.financeiro.contaspagar.entity.contabancaria.ContaBancariaAutoComplete;
import br.com.kerubin.api.financeiro.contaspagar.entity.cartaocredito.CartaoCreditoAutoComplete;
import br.com.kerubin.api.financeiro.contaspagar.entity.contapagar.ContaPagarAutoComplete;
import java.math.BigDecimal;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;
import br.com.kerubin.api.financeiro.contaspagar.FinanceiroContasPagarBaseEntityTest;


@RunWith(SpringRunner.class)
public class ContaPagarMultipleServiceTest extends FinanceiroContasPagarBaseEntityTest {
	
	private static final String[] IGNORED_FIELDS = { "id", "createdBy", "createdDate", "lastModifiedBy", "lastModifiedDate" };
	
	@TestConfiguration
	static class ContaPagarMultipleServiceTestConfig {
		
		@Bean
		public ContaPagarMultipleListFilterPredicate contaPagarMultipleListFilterPredicate() {
			return new ContaPagarMultipleListFilterPredicateImpl();
		}
		
		@Bean
		public ContaPagarMultipleService contaPagarMultipleService() {
			return new ContaPagarMultipleServiceImpl();
		}
		
		@Bean
		public ContaPagarMultipleDTOConverter contaPagarMultipleDTOConverter() {
			return new ContaPagarMultipleDTOConverter();
		}
		
	}
	
	
	@Inject
	protected ContaPagarMultipleService contaPagarMultipleService;
	
	@Inject
	protected ContaPagarMultipleDTOConverter contaPagarMultipleDTOConverter;
	
	@Inject
	protected ContaPagarMultipleRepository contaPagarMultipleRepository;
	
	@Inject
	protected FornecedorRepository fornecedorRepository;
	
	@Inject
	protected PlanoContaRepository planoContaRepository;
	
	@Inject
	protected ContaBancariaRepository contaBancariaRepository;
	
	@Inject
	protected CartaoCreditoRepository cartaoCreditoRepository;
	
	@Inject
	protected ContaPagarBaseRepository contaPagarBaseRepository;
	
	@MockBean
	protected DomainEntityEventsPublisher publisher;
	
	// BEGIN CREATE TESTS
	
	@Test
	public void testCreateWithAllFields() throws Exception {
		ContaPagarMultiple contaPagarMultiple = new ContaPagarMultiple();
		
		contaPagarMultiple.setId(java.util.UUID.randomUUID());
		contaPagarMultiple.setDataPagamento(java.time.LocalDate.now().minusDays(1));
		contaPagarMultiple.setValorPago(new java.math.BigDecimal("5607.28041"));
		contaPagarMultiple.setDescricao(generateRandomString(255));
		
		FornecedorEntity fornecedorEntityParam = newFornecedorEntity();
		FornecedorLookupResult fornecedor = newFornecedorLookupResult(fornecedorEntityParam);
		contaPagarMultiple.setFornecedor(fornecedor);
		
		contaPagarMultiple.setMaisOpcoes(false);
		
		PlanoContaEntity planoContaEntityParam = newPlanoContaEntity();
		PlanoContaLookupResult planoContas = newPlanoContaLookupResult(planoContaEntityParam);
		contaPagarMultiple.setPlanoContas(planoContas);
		
		contaPagarMultiple.setFormaPagamento(FormaPagamento.DINHEIRO);
		
		ContaBancariaEntity contaBancariaEntityParam = newContaBancariaEntity();
		ContaBancariaLookupResult contaBancaria = newContaBancariaLookupResult(contaBancariaEntityParam);
		contaPagarMultiple.setContaBancaria(contaBancaria);
		
		
		CartaoCreditoEntity cartaoCreditoEntityParam = newCartaoCreditoEntity();
		CartaoCreditoLookupResult cartaoCredito = newCartaoCreditoLookupResult(cartaoCreditoEntityParam);
		contaPagarMultiple.setCartaoCredito(cartaoCredito);
		
		contaPagarMultiple.setOutrosDescricao(generateRandomString(255));
		
		ContaPagarEntity contaPagarEntityParam = newContaPagarEntity();
		ContaPagarLookupResult contaPagar = newContaPagarLookupResult(contaPagarEntityParam);
		contaPagarMultiple.setContaPagar(contaPagar);
		
		ContaPagarMultipleEntity contaPagarMultipleEntity = contaPagarMultipleService.create(contaPagarMultipleDTOConverter.convertDtoToEntity(contaPagarMultiple));
		em.flush();
		verify(publisher, times(0)).publish(any());
		ContaPagarMultiple actual = contaPagarMultipleDTOConverter.convertEntityToDto(contaPagarMultipleEntity);
		
		
		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isNotNull();
		assertThat(actual).isEqualToIgnoringGivenFields(contaPagarMultiple, IGNORED_FIELDS);
		
		
		assertThat(actual.getFornecedor().getId()).isNotNull();
		assertThat(actual.getFornecedor()).isEqualToIgnoringGivenFields(contaPagarMultiple.getFornecedor(), IGNORED_FIELDS);
		
		
		assertThat(actual.getPlanoContas().getId()).isNotNull();
		assertThat(actual.getPlanoContas()).isEqualToIgnoringGivenFields(contaPagarMultiple.getPlanoContas(), IGNORED_FIELDS);
		
		
		assertThat(actual.getContaBancaria().getId()).isNotNull();
		assertThat(actual.getContaBancaria()).isEqualToIgnoringGivenFields(contaPagarMultiple.getContaBancaria(), IGNORED_FIELDS);
		
		
		assertThat(actual.getCartaoCredito().getId()).isNotNull();
		assertThat(actual.getCartaoCredito()).isEqualToIgnoringGivenFields(contaPagarMultiple.getCartaoCredito(), IGNORED_FIELDS);
		
		
		assertThat(actual.getContaPagar().getId()).isNotNull();
		assertThat(actual.getContaPagar()).isEqualToIgnoringGivenFields(contaPagarMultiple.getContaPagar(), IGNORED_FIELDS);
		
		
	}
	
	@Test
	public void testCreateWithOnlyRecairedFields() throws Exception {
		ContaPagarMultiple contaPagarMultiple = new ContaPagarMultiple();
		
		contaPagarMultiple.setId(java.util.UUID.randomUUID());
		contaPagarMultiple.setDataPagamento(java.time.LocalDate.now().minusDays(1));
		contaPagarMultiple.setValorPago(new java.math.BigDecimal("5816.14471"));
		contaPagarMultiple.setDescricao(generateRandomString(255));
		
		PlanoContaEntity planoContaEntityParam = newPlanoContaEntity();
		PlanoContaLookupResult planoContas = newPlanoContaLookupResult(planoContaEntityParam);
		contaPagarMultiple.setPlanoContas(planoContas);
		
		contaPagarMultiple.setFormaPagamento(FormaPagamento.DINHEIRO);
		
		ContaPagarEntity contaPagarEntityParam = newContaPagarEntity();
		ContaPagarLookupResult contaPagar = newContaPagarLookupResult(contaPagarEntityParam);
		contaPagarMultiple.setContaPagar(contaPagar);
		
		ContaPagarMultipleEntity contaPagarMultipleEntity = contaPagarMultipleService.create(contaPagarMultipleDTOConverter.convertDtoToEntity(contaPagarMultiple));
		em.flush();
		verify(publisher, times(0)).publish(any());
		ContaPagarMultiple actual = contaPagarMultipleDTOConverter.convertEntityToDto(contaPagarMultipleEntity);
		
		
		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isNotNull();
		assertThat(actual).isEqualToIgnoringGivenFields(contaPagarMultiple, IGNORED_FIELDS);
		
		
		assertThat(actual.getPlanoContas().getId()).isNotNull();
		assertThat(actual.getPlanoContas()).isEqualToIgnoringGivenFields(contaPagarMultiple.getPlanoContas(), IGNORED_FIELDS);
		
		
		assertThat(actual.getContaPagar().getId()).isNotNull();
		assertThat(actual.getContaPagar()).isEqualToIgnoringGivenFields(contaPagarMultiple.getContaPagar(), IGNORED_FIELDS);
		
		assertThat(actual.getFornecedor()).isNull();
		assertThat(actual.getContaBancaria()).isNull();
		assertThat(actual.getCartaoCredito()).isNull();
		
	}
	// END CREATE TESTS
	
	// BEGIN READ TESTS
	
	@Test
	public void testRead1() {
		ContaPagarMultipleEntity expectedContaPagarMultipleEntity = newContaPagarMultipleEntity();
		java.util.UUID id = expectedContaPagarMultipleEntity.getId();
		ContaPagarMultiple expected = contaPagarMultipleDTOConverter.convertEntityToDto(expectedContaPagarMultipleEntity);
		ContaPagarMultipleEntity readContaPagarMultipleEntity = contaPagarMultipleService.read(id);
		ContaPagarMultiple actual = contaPagarMultipleDTOConverter.convertEntityToDto(readContaPagarMultipleEntity);
		
		assertThat(actual).isEqualToComparingFieldByField(expected);
		
	}
	// END READ TESTS
	
	// BEGIN UPDATE TESTS
	
	@Test
	public void testUpdateWithAllFields() throws Exception {
		ContaPagarMultipleEntity oldContaPagarMultipleEntity = newContaPagarMultipleEntity();
		java.util.UUID id = oldContaPagarMultipleEntity.getId();
				
		ContaPagarMultiple contaPagarMultiple = new ContaPagarMultiple();
		contaPagarMultiple.setId(id);
		
		contaPagarMultiple.setDataPagamento(java.time.LocalDate.now().minusDays(1));
		contaPagarMultiple.setValorPago(new java.math.BigDecimal("19926.15742"));
		contaPagarMultiple.setDescricao(generateRandomString(255));
		
		FornecedorEntity fornecedorEntityParam = newFornecedorEntity();
		FornecedorLookupResult fornecedor = newFornecedorLookupResult(fornecedorEntityParam);
		contaPagarMultiple.setFornecedor(fornecedor);
		
		contaPagarMultiple.setMaisOpcoes(false);
		
		PlanoContaEntity planoContaEntityParam = newPlanoContaEntity();
		PlanoContaLookupResult planoContas = newPlanoContaLookupResult(planoContaEntityParam);
		contaPagarMultiple.setPlanoContas(planoContas);
		
		contaPagarMultiple.setFormaPagamento(FormaPagamento.DINHEIRO);
		
		ContaBancariaEntity contaBancariaEntityParam = newContaBancariaEntity();
		ContaBancariaLookupResult contaBancaria = newContaBancariaLookupResult(contaBancariaEntityParam);
		contaPagarMultiple.setContaBancaria(contaBancaria);
		
		
		CartaoCreditoEntity cartaoCreditoEntityParam = newCartaoCreditoEntity();
		CartaoCreditoLookupResult cartaoCredito = newCartaoCreditoLookupResult(cartaoCreditoEntityParam);
		contaPagarMultiple.setCartaoCredito(cartaoCredito);
		
		contaPagarMultiple.setOutrosDescricao(generateRandomString(255));
		
		ContaPagarEntity contaPagarEntityParam = newContaPagarEntity();
		ContaPagarLookupResult contaPagar = newContaPagarLookupResult(contaPagarEntityParam);
		contaPagarMultiple.setContaPagar(contaPagar);
		
		ContaPagarMultipleEntity contaPagarMultipleEntity = contaPagarMultipleService.update(id, contaPagarMultipleDTOConverter.convertDtoToEntity(contaPagarMultiple));
		em.flush();
		verify(publisher, times(0)).publish(any());
		
		ContaPagarMultiple actual = contaPagarMultipleDTOConverter.convertEntityToDto(contaPagarMultipleEntity);
		
		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isNotNull();
		assertThat(actual).isEqualToIgnoringGivenFields(contaPagarMultiple, IGNORED_FIELDS);
		
		
		assertThat(actual.getFornecedor().getId()).isNotNull();
		assertThat(actual.getFornecedor()).isEqualToIgnoringGivenFields(contaPagarMultiple.getFornecedor(), IGNORED_FIELDS);
		
		
		assertThat(actual.getPlanoContas().getId()).isNotNull();
		assertThat(actual.getPlanoContas()).isEqualToIgnoringGivenFields(contaPagarMultiple.getPlanoContas(), IGNORED_FIELDS);
		
		
		assertThat(actual.getContaBancaria().getId()).isNotNull();
		assertThat(actual.getContaBancaria()).isEqualToIgnoringGivenFields(contaPagarMultiple.getContaBancaria(), IGNORED_FIELDS);
		
		
		assertThat(actual.getCartaoCredito().getId()).isNotNull();
		assertThat(actual.getCartaoCredito()).isEqualToIgnoringGivenFields(contaPagarMultiple.getCartaoCredito(), IGNORED_FIELDS);
		
		
		assertThat(actual.getContaPagar().getId()).isNotNull();
		assertThat(actual.getContaPagar()).isEqualToIgnoringGivenFields(contaPagarMultiple.getContaPagar(), IGNORED_FIELDS);
		
		
	}
	
	@Test
	public void testUpdateWithOnlyRecairedFields() throws Exception {
		ContaPagarMultipleEntity oldContaPagarMultipleEntity = newContaPagarMultipleEntity();
		java.util.UUID id = oldContaPagarMultipleEntity.getId();
				
		ContaPagarMultiple contaPagarMultiple = new ContaPagarMultiple();
		contaPagarMultiple.setId(id);
		
		contaPagarMultiple.setDataPagamento(java.time.LocalDate.now().minusDays(1));
		contaPagarMultiple.setValorPago(new java.math.BigDecimal("10892.12211"));
		contaPagarMultiple.setDescricao(generateRandomString(255));
		
		PlanoContaEntity planoContaEntityParam = newPlanoContaEntity();
		PlanoContaLookupResult planoContas = newPlanoContaLookupResult(planoContaEntityParam);
		contaPagarMultiple.setPlanoContas(planoContas);
		
		contaPagarMultiple.setFormaPagamento(FormaPagamento.DINHEIRO);
		
		ContaPagarEntity contaPagarEntityParam = newContaPagarEntity();
		ContaPagarLookupResult contaPagar = newContaPagarLookupResult(contaPagarEntityParam);
		contaPagarMultiple.setContaPagar(contaPagar);
		
		ContaPagarMultipleEntity contaPagarMultipleEntity = contaPagarMultipleService.update(id, contaPagarMultipleDTOConverter.convertDtoToEntity(contaPagarMultiple));
		em.flush();
		verify(publisher, times(0)).publish(any());
		
		ContaPagarMultiple actual = contaPagarMultipleDTOConverter.convertEntityToDto(contaPagarMultipleEntity);
		
		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isNotNull();
		assertThat(actual).isEqualToIgnoringGivenFields(contaPagarMultiple, IGNORED_FIELDS);
		
		
		assertThat(actual.getPlanoContas().getId()).isNotNull();
		assertThat(actual.getPlanoContas()).isEqualToIgnoringGivenFields(contaPagarMultiple.getPlanoContas(), IGNORED_FIELDS);
		
		
		assertThat(actual.getContaPagar().getId()).isNotNull();
		assertThat(actual.getContaPagar()).isEqualToIgnoringGivenFields(contaPagarMultiple.getContaPagar(), IGNORED_FIELDS);
		
		assertThat(actual.getFornecedor()).isNull();
		assertThat(actual.getContaBancaria()).isNull();
		assertThat(actual.getCartaoCredito()).isNull();
		
	}
	// END UPDATE TESTS
	
	// BEGIN DELETE TESTS
	
	@Test
	public void testDelete1() {
		ContaPagarMultipleEntity expected = newContaPagarMultipleEntity();
		java.util.UUID id = expected.getId();
		
		
		expected = em.find(ContaPagarMultipleEntity.class, id);
		assertThat(expected).isNotNull();
		contaPagarMultipleService.delete(id);
		verify(publisher, times(0)).publish(any());
		
		expected = em.find(ContaPagarMultipleEntity.class, id);
		assertThat(expected).isNull();
	}
	// END DELETE TESTS
	
	// BEGIN LIST TESTS
	// END LIST TESTS
	
	// BEGIN Autocomplete TESTS
	@Test
	public void testAutoComplete() {
		// Reset lastDate field to start LocalDate fields with today in this test. 
		resetNextDate();
					
		// Generate 33 records of data for ContaPagarMultipleEntity for this test.
		List<ContaPagarMultipleEntity> testData = new ArrayList<>();
		final int lastRecord = 33;
		final int firstRecord = 1;
		for (int i = firstRecord; i <= lastRecord; i++) {
			testData.add(newContaPagarMultipleEntity());
		}
		
		// Check if 33 records of ContaPagarMultipleEntity was generated.
		long count = contaPagarMultipleRepository.count();
		assertThat(count).isEqualTo(lastRecord);
		
		// Extracts 1 records of ContaPagarMultipleEntity randomly from testData.
		final int resultSize = 1;
		List<ContaPagarMultipleEntity> filterTestData = getRandomItemsOf(testData, resultSize);
		
		// Extracts a list with only ContaPagarMultipleEntity.descricao field and configure this list as a filter.
		List<String> descricaoListFilter = filterTestData.stream().map(ContaPagarMultipleEntity::getDescricao).collect(Collectors.toList());
		// Mount the autocomplete query expression and call it.
		String query = descricaoListFilter.get(0);
		Collection<ContaPagarMultipleAutoComplete> result = contaPagarMultipleService.autoComplete(query);
		
		// Assert ContaPagarMultipleAutoComplete results.
		assertThat(result).isNotNull().hasSize(1)
		.extracting(ContaPagarMultipleAutoComplete::getDescricao)
		.containsExactlyInAnyOrderElementsOf(descricaoListFilter);
	}
	
	// END Autocomplete TESTS
	
	
	// BEGIN Relationships Autocomplete TESTS
	
	@Test
	public void testContaPagarMultipleFornecedorAutoComplete() {
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
		
		Collection<FornecedorAutoComplete> result = contaPagarMultipleService.fornecedorFornecedorAutoComplete(query);
		
		assertThat(result).isNotNull().hasSize(1)
		.extracting(FornecedorAutoComplete::getNome)
		.containsExactlyInAnyOrderElementsOf(nomeListFilter);
	}
	
	
	@Test
	public void testContaPagarMultiplePlanoContasAutoComplete() {
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
		
		Collection<PlanoContaAutoComplete> result = contaPagarMultipleService.planoContaPlanoContasAutoComplete(query);
		
		assertThat(result).isNotNull().hasSize(1)
		.extracting(PlanoContaAutoComplete::getCodigo)
		.containsExactlyInAnyOrderElementsOf(codigoListFilter);
	}
	
	
	@Test
	public void testContaPagarMultipleContaBancariaAutoComplete() {
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
		
		Collection<ContaBancariaAutoComplete> result = contaPagarMultipleService.contaBancariaContaBancariaAutoComplete(query);
		
		assertThat(result).isNotNull().hasSize(1)
		.extracting(ContaBancariaAutoComplete::getNomeTitular)
		.containsExactlyInAnyOrderElementsOf(nomeTitularListFilter);
	}
	
	
	@Test
	public void testContaPagarMultipleCartaoCreditoAutoComplete() {
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
		
		Collection<CartaoCreditoAutoComplete> result = contaPagarMultipleService.cartaoCreditoCartaoCreditoAutoComplete(query);
		
		assertThat(result).isNotNull().hasSize(1)
		.extracting(CartaoCreditoAutoComplete::getNomeTitular)
		.containsExactlyInAnyOrderElementsOf(nomeTitularListFilter);
	}
	
	
	@Test
	public void testContaPagarMultipleContaPagarAutoComplete() {
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
		String query = descricaoListFilter.get(0);
		
		Collection<ContaPagarAutoComplete> result = contaPagarMultipleService.contaPagarContaPagarAutoComplete(query);
		
		assertThat(result).isNotNull().hasSize(1)
		.extracting(ContaPagarAutoComplete::getDescricao)
		.containsExactlyInAnyOrderElementsOf(descricaoListFilter);
	}
	
	// END Relationships Autocomplete TESTS
	
	// BEGIN tests for Sum Fields
	
	@Test
	public void testGetContaPagarMultipleSumFields() {
		// Reset lastDate field to start LocalDate fields with today in this test. 
		resetNextDate();
		
		// Generate 2 records of data for ContaPagarMultipleEntity for this test.
		List<ContaPagarMultipleEntity> testData = new ArrayList<>();
		final int lastRecord = 2;
		final int firstRecord = 1;
		for (int i = firstRecord; i <= lastRecord; i++) {
			testData.add(newContaPagarMultipleEntity());
		}
		
		// Check if 2 records of ContaPagarMultipleEntity was generated.
		long count = contaPagarMultipleRepository.count();
		assertThat(count).isEqualTo(lastRecord);
		
		// Creates a list filter for entity ContaPagarMultiple.
		ContaPagarMultipleListFilter listFilter = new ContaPagarMultipleListFilter();
		
		// Extracts 2 records of ContaPagarMultipleEntity randomly from testData.
		final int resultSize = 2;
		List<ContaPagarMultipleEntity> filterTestData = getRandomItemsOf(testData, resultSize);
		
		ContaPagarMultipleSumFields expected = new ContaPagarMultipleSumFields();
		
		BigDecimal sumValorPago = filterTestData.stream().map(it -> it.getValorPago()).reduce(BigDecimal.ZERO, BigDecimal::add);
		expected.setSumValorPago(sumValorPago);
		ContaPagarMultipleSumFields actual = contaPagarMultipleService.getContaPagarMultipleSumFields(listFilter);
		
		assertThat(actual).isEqualToComparingFieldByField(expected);
	}
	// END tests for Sum Fields
	
	// BEGIN tests for Sum Fields
	// END tests for Sum Fields
	
	// BEGIN TESTS DEPENDENCIES
	
	
	protected ContaPagarMultipleEntity newContaPagarMultipleEntity() {
		ContaPagarMultipleEntity contaPagarMultipleEntity = new ContaPagarMultipleEntity();
		
		contaPagarMultipleEntity.setDataPagamento(java.time.LocalDate.now().minusDays(1));
		contaPagarMultipleEntity.setValorPago(new java.math.BigDecimal("2151.15689"));
		contaPagarMultipleEntity.setDescricao(generateRandomString(255));
		contaPagarMultipleEntity.setFornecedor(newFornecedorEntity());
		contaPagarMultipleEntity.setMaisOpcoes(false);
		contaPagarMultipleEntity.setPlanoContas(newPlanoContaEntity());
		contaPagarMultipleEntity.setFormaPagamento(FormaPagamento.DINHEIRO);
		contaPagarMultipleEntity.setContaBancaria(newContaBancariaEntity());
		contaPagarMultipleEntity.setCartaoCredito(newCartaoCreditoEntity());
		contaPagarMultipleEntity.setOutrosDescricao(generateRandomString(255));
		contaPagarMultipleEntity.setContaPagar(newContaPagarEntity());
		
		contaPagarMultipleEntity = em.persistAndFlush(contaPagarMultipleEntity);
		return contaPagarMultipleEntity;
	}
	
	
	protected ContaPagarMultipleLookupResult newContaPagarMultipleLookupResult(ContaPagarMultipleEntity contaPagarMultipleEntity) {
		ContaPagarMultipleLookupResult contaPagarMultiple = new ContaPagarMultipleLookupResult();
		
		contaPagarMultiple.setId(contaPagarMultipleEntity.getId());
		contaPagarMultiple.setDescricao(contaPagarMultipleEntity.getDescricao());
		
		return contaPagarMultiple;
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
	
	
	protected PlanoContaEntity newPlanoContaEntity() {
		PlanoContaEntity planoContaEntity = new PlanoContaEntity();
		
		planoContaEntity.setId(java.util.UUID.randomUUID());
		planoContaEntity.setCodigo(generateRandomString(255));
		planoContaEntity.setDescricao(generateRandomString(255));
		planoContaEntity.setTipoFinanceiro(TipoPlanoContaFinanceiro.RECEITA);
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
		cartaoCreditoEntity.setValorLimite(new java.math.BigDecimal("10167.24834"));
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
	
	
	protected ContaPagarEntity newContaPagarEntity() {
		ContaPagarEntity contaPagarEntity = new ContaPagarEntity();
		
		contaPagarEntity.setDescricao(generateRandomString(255));
		contaPagarEntity.setPlanoContas(newPlanoContaEntity());
		contaPagarEntity.setDataVencimento(getNextDate());
		contaPagarEntity.setValor(new java.math.BigDecimal("7306.23992"));
		contaPagarEntity.setFormaPagamento(FormaPagamento.DINHEIRO);
		contaPagarEntity.setContaBancaria(newContaBancariaEntity());
		contaPagarEntity.setCartaoCredito(newCartaoCreditoEntity());
		contaPagarEntity.setOutrosDescricao(generateRandomString(255));
		contaPagarEntity.setFornecedor(newFornecedorEntity());
		contaPagarEntity.setContaPaga(false);
		contaPagarEntity.setDataPagamento(getNextDate());
		contaPagarEntity.setValorDesconto(new java.math.BigDecimal("5660.12070"));
		contaPagarEntity.setValorMulta(new java.math.BigDecimal("27255.7292"));
		contaPagarEntity.setValorJuros(new java.math.BigDecimal("25000.6585"));
		contaPagarEntity.setValorAcrescimos(new java.math.BigDecimal("9067.24061"));
		contaPagarEntity.setValorPago(new java.math.BigDecimal("27960.3339"));
		contaPagarEntity.setMaisOpcoes(false);
		contaPagarEntity.setIdConcBancaria(generateRandomString(255));
		contaPagarEntity.setHistConcBancaria(generateRandomString(255));
		contaPagarEntity.setNumDocConcBancaria(generateRandomString(255));
		contaPagarEntity.setNumDocumento(generateRandomString(255));
		contaPagarEntity.setObservacoes(generateRandomString(1000));
		contaPagarEntity.setAgrupador(generateRandomString(255));
		contaPagarEntity.setTipoPagamento(TipoPagamentoConta.SINGLE);
		contaPagarEntity.setContaPagarPai(java.util.UUID.randomUUID());
		
		contaPagarEntity = em.persistAndFlush(contaPagarEntity);
		return contaPagarEntity;
	}
	
	
	protected ContaPagarLookupResult newContaPagarLookupResult(ContaPagarEntity contaPagarEntity) {
		ContaPagarLookupResult contaPagar = new ContaPagarLookupResult();
		
		contaPagar.setId(contaPagarEntity.getId());
		contaPagar.setDescricao(contaPagarEntity.getDescricao());
		
		return contaPagar;
	}
	// END TESTS DEPENDENCIES

}
