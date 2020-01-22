/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-22T06:59:30.300
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.fornecedor;

import br.com.kerubin.api.financeiro.contaspagar.TipoPessoa;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import javax.inject.Inject;
import org.springframework.boot.test.mock.mockito.MockBean;
import br.com.kerubin.api.messaging.core.DomainEntityEventsPublisher;
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
import java.util.Collection;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;
import br.com.kerubin.api.financeiro.contaspagar.FinanceiroContasPagarBaseEntityTest;


@RunWith(SpringRunner.class)
public class FornecedorServiceTest extends FinanceiroContasPagarBaseEntityTest {
	
	private static final String[] IGNORED_FIELDS = { "id" };
	
	@TestConfiguration
	static class FornecedorServiceTestConfig {
		
		@Bean
		public FornecedorListFilterPredicate fornecedorListFilterPredicate() {
			return new FornecedorListFilterPredicateImpl();
		}
		
		@Bean
		public FornecedorService fornecedorService() {
			return new FornecedorServiceImpl();
		}
		
		@Bean
		public FornecedorDTOConverter fornecedorDTOConverter() {
			return new FornecedorDTOConverter();
		}
		
	}
	
	
	@Inject
	protected FornecedorService fornecedorService;
	
	@Inject
	protected FornecedorDTOConverter fornecedorDTOConverter;
	
	@Inject
	protected FornecedorRepository fornecedorRepository;
	
	@MockBean
	protected DomainEntityEventsPublisher publisher;
	
	// BEGIN CREATE TESTS
	
	@Test
	public void testCreateWithAllFields() throws Exception {
		Fornecedor fornecedor = new Fornecedor();
		
		fornecedor.setId(java.util.UUID.randomUUID());
		fornecedor.setTipoPessoa(TipoPessoa.PESSOA_JURIDICA);
		fornecedor.setNome(generateRandomString(255));
		fornecedor.setCnpjCPF(generateRandomString(255));
		fornecedor.setAtivo(true);
		fornecedor.setDeleted(false);
		FornecedorEntity fornecedorEntity = fornecedorService.create(fornecedorDTOConverter.convertDtoToEntity(fornecedor));
		em.flush();
		verify(publisher, times(0)).publish(any());
		Fornecedor actual = fornecedorDTOConverter.convertEntityToDto(fornecedorEntity);
		
		
		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isNotNull();
		assertThat(actual).isEqualToIgnoringGivenFields(fornecedor, IGNORED_FIELDS);
		
		
	}
	
	@Test
	public void testCreateWithOnlyRecairedFields() throws Exception {
		Fornecedor fornecedor = new Fornecedor();
		
		fornecedor.setId(java.util.UUID.randomUUID());
		fornecedor.setTipoPessoa(TipoPessoa.PESSOA_JURIDICA);
		fornecedor.setNome(generateRandomString(255));
		FornecedorEntity fornecedorEntity = fornecedorService.create(fornecedorDTOConverter.convertDtoToEntity(fornecedor));
		em.flush();
		verify(publisher, times(0)).publish(any());
		Fornecedor actual = fornecedorDTOConverter.convertEntityToDto(fornecedorEntity);
		
		
		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isNotNull();
		assertThat(actual).isEqualToIgnoringGivenFields(fornecedor, IGNORED_FIELDS);
		
		
	}
	// END CREATE TESTS
	
	// BEGIN READ TESTS
	
	@Test
	public void testRead1() {
		FornecedorEntity expectedFornecedorEntity = newFornecedorEntity();
		java.util.UUID id = expectedFornecedorEntity.getId();
		Fornecedor expected = fornecedorDTOConverter.convertEntityToDto(expectedFornecedorEntity);
		FornecedorEntity readFornecedorEntity = fornecedorService.read(id);
		Fornecedor actual = fornecedorDTOConverter.convertEntityToDto(readFornecedorEntity);
		
		assertThat(actual).isEqualToComparingFieldByField(expected);
		
	}
	// END READ TESTS
	
	// BEGIN UPDATE TESTS
	
	@Test
	public void testUpdateWithAllFields() throws Exception {
		FornecedorEntity oldFornecedorEntity = newFornecedorEntity();
		java.util.UUID id = oldFornecedorEntity.getId();
				
		Fornecedor fornecedor = new Fornecedor();
		fornecedor.setId(id);
		
		fornecedor.setTipoPessoa(TipoPessoa.PESSOA_JURIDICA);
		fornecedor.setNome(generateRandomString(255));
		fornecedor.setCnpjCPF(generateRandomString(255));
		fornecedor.setAtivo(true);
		fornecedor.setDeleted(false);
		FornecedorEntity fornecedorEntity = fornecedorService.update(id, fornecedorDTOConverter.convertDtoToEntity(fornecedor));
		em.flush();
		verify(publisher, times(0)).publish(any());
		
		Fornecedor actual = fornecedorDTOConverter.convertEntityToDto(fornecedorEntity);
		
		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isNotNull();
		assertThat(actual).isEqualToIgnoringGivenFields(fornecedor, IGNORED_FIELDS);
		
		
	}
	
	@Test
	public void testUpdateWithOnlyRecairedFields() throws Exception {
		FornecedorEntity oldFornecedorEntity = newFornecedorEntity();
		java.util.UUID id = oldFornecedorEntity.getId();
				
		Fornecedor fornecedor = new Fornecedor();
		fornecedor.setId(id);
		
		fornecedor.setTipoPessoa(TipoPessoa.PESSOA_JURIDICA);
		fornecedor.setNome(generateRandomString(255));
		FornecedorEntity fornecedorEntity = fornecedorService.update(id, fornecedorDTOConverter.convertDtoToEntity(fornecedor));
		em.flush();
		verify(publisher, times(0)).publish(any());
		
		Fornecedor actual = fornecedorDTOConverter.convertEntityToDto(fornecedorEntity);
		
		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isNotNull();
		assertThat(actual).isEqualToIgnoringGivenFields(fornecedor, IGNORED_FIELDS);
		
		
	}
	// END UPDATE TESTS
	
	// BEGIN DELETE TESTS
	
	@Test
	public void testDelete1() {
		FornecedorEntity expected = newFornecedorEntity();
		java.util.UUID id = expected.getId();
		
		
		expected = em.find(FornecedorEntity.class, id);
		assertThat(expected).isNotNull();
		fornecedorService.delete(id);
		verify(publisher, times(0)).publish(any());
		
		expected = em.find(FornecedorEntity.class, id);
		assertThat(expected).isNull();
	}
	// END DELETE TESTS
	
	// BEGIN LIST TESTS
	
	@Test
	public void testList_FilteringByNome() {
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
		
		// Creates a list filter for entity Fornecedor.
		FornecedorListFilter listFilter = new FornecedorListFilter();
		
		// Extracts 7 records of FornecedorEntity randomly from testData.
		final int resultSize = 7;
		List<FornecedorEntity> filterTestData = getRandomItemsOf(testData, resultSize);
		
		// Extracts a list with only FornecedorEntity.nome field and configure this list as a filter.
		List<String> nomeListFilter = filterTestData.stream().map(FornecedorEntity::getNome).collect(Collectors.toList());
		listFilter.setNome(nomeListFilter);
		
		// Generates a pageable configuration, without sorting.
		int pageIndex = 0; // First page starts at index zero.
		int size = 33; // Max of 33 records per page.
		Pageable pageable = PageRequest.of(pageIndex, size);
		// Call service list method.
		Page<FornecedorEntity> page = fornecedorService.list(listFilter, pageable);
		
		// Converts found entities to DTOs and mount the result page.
		List<Fornecedor> content = page.getContent().stream().map(it -> fornecedorDTOConverter.convertEntityToDto(it)).collect(Collectors.toList());
		PageResult<Fornecedor> pageResult = new PageResult<>(content, page.getNumber(), page.getSize(), page.getTotalElements());
		
		// Asserts that result has size 7, in any order and has only rows with nomeListFilter elements based on nome field.
		assertThat(pageResult.getContent())
		.hasSize(7)
		.extracting(Fornecedor::getNome)
		.containsExactlyInAnyOrderElementsOf(nomeListFilter);
		
		// Asserts some page result elements.
		assertThat(pageResult.getNumber()).isEqualTo(pageIndex);
		assertThat(pageResult.getNumberOfElements()).isEqualTo(7);
		assertThat(pageResult.getTotalElements()).isEqualTo(7);
		assertThat(pageResult.getTotalPages()).isEqualTo(1);
		
	}
	
	@Test
	public void testList_FilteringByNomeWithoutResults() {
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
		
		// Creates a list filter for entity Fornecedor.
		FornecedorListFilter listFilter = new FornecedorListFilter();
		
		// Generates a list with only FornecedorEntity.nome field with 1 not found data in the database and configure this list as a filter.
		List<String> nomeListFilter = Arrays.asList(generateRandomString(255));
		listFilter.setNome(nomeListFilter);
		
		// Generates a pageable configuration, without sorting.
		int pageIndex = 0; // First page starts at index zero.
		int size = 33; // Max of 33 records per page.
		Pageable pageable = PageRequest.of(pageIndex, size);
		// Call service list method.
		Page<FornecedorEntity> page = fornecedorService.list(listFilter, pageable);
		
		// Converts found entities to DTOs and mount the result page.
		List<Fornecedor> content = page.getContent().stream().map(it -> fornecedorDTOConverter.convertEntityToDto(it)).collect(Collectors.toList());
		PageResult<Fornecedor> pageResult = new PageResult<>(content, page.getNumber(), page.getSize(), page.getTotalElements());
		
		// Asserts that result has size 0 for unknown nome field.
		assertThat(pageResult.getContent()).hasSize(0);
		
	}
	// END LIST TESTS
	
	// BEGIN Autocomplete TESTS
	@Test
	public void testAutoComplete() {
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
		// Mount the autocomplete query expression and call it.
		String query = nomeListFilter.get(0);
		Collection<FornecedorAutoComplete> result = fornecedorService.autoComplete(query);
		
		// Assert FornecedorAutoComplete results.
		assertThat(result).isNotNull().hasSize(1)
		.extracting(FornecedorAutoComplete::getNome)
		.containsExactlyInAnyOrderElementsOf(nomeListFilter);
	}
	
	// END Autocomplete TESTS
	
	// BEGIN ListFilter Autocomplete TESTS
	
	@Test
	public void testFornecedorNomeAutoComplete() {
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
		// Mount the autocomplete query expression and call it.
		String query = nomeListFilter.get(0);
		Collection<FornecedorNomeAutoComplete> result = fornecedorService.fornecedorNomeAutoComplete(query);
		// Assert FornecedorNomeAutoComplete results.
		assertThat(result).isNotNull().hasSize(1)
		.extracting(FornecedorNomeAutoComplete::getNome)
		.containsExactlyInAnyOrderElementsOf(nomeListFilter);
	}
	
	// END ListFilter Autocomplete TESTS
	
	
	// BEGIN tests for Sum Fields
	// END tests for Sum Fields
	
	// BEGIN tests for Sum Fields
	// END tests for Sum Fields
	
	// BEGIN TESTS DEPENDENCIES
	
	
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
