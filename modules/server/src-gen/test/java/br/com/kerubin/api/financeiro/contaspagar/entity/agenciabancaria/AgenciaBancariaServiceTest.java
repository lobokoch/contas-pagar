/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-13T08:12:17.669
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.agenciabancaria;

import br.com.kerubin.api.financeiro.contaspagar.entity.banco.BancoEntity;
import br.com.kerubin.api.financeiro.contaspagar.entity.banco.BancoLookupResult;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import javax.inject.Inject;
import br.com.kerubin.api.financeiro.contaspagar.entity.banco.BancoRepository;
import org.springframework.boot.test.mock.mockito.MockBean;
import br.com.kerubin.api.messaging.core.DomainEntityEventsPublisher;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.ArgumentMatchers.any;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.Collection;
import br.com.kerubin.api.financeiro.contaspagar.entity.banco.BancoAutoComplete;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;
import br.com.kerubin.api.financeiro.contaspagar.FinanceiroContasPagarBaseEntityTest;


@RunWith(SpringRunner.class)
public class AgenciaBancariaServiceTest extends FinanceiroContasPagarBaseEntityTest {
	
	private static final String[] IGNORED_FIELDS = { "id" };
	
	@TestConfiguration
	static class AgenciaBancariaServiceTestConfig {
		
		@Bean
		public AgenciaBancariaListFilterPredicate agenciaBancariaListFilterPredicate() {
			return new AgenciaBancariaListFilterPredicateImpl();
		}
		
		@Bean
		public AgenciaBancariaService agenciaBancariaService() {
			return new AgenciaBancariaServiceImpl();
		}
		
		@Bean
		public AgenciaBancariaDTOConverter agenciaBancariaDTOConverter() {
			return new AgenciaBancariaDTOConverter();
		}
		
	}
	
	
	@Inject
	protected AgenciaBancariaService agenciaBancariaService;
	
	@Inject
	protected AgenciaBancariaDTOConverter agenciaBancariaDTOConverter;
	
	@Inject
	protected AgenciaBancariaRepository agenciaBancariaRepository;
	
	@Inject
	protected BancoRepository bancoRepository;
	
	@MockBean
	protected DomainEntityEventsPublisher publisher;
	
	// BEGIN CREATE TESTS
	
	@Test
	public void testCreateWithAllFields() throws Exception {
		AgenciaBancaria agenciaBancaria = new AgenciaBancaria();
		
		agenciaBancaria.setId(java.util.UUID.randomUUID());
		
		BancoEntity bancoEntityParam = newBancoEntity();
		BancoLookupResult banco = newBancoLookupResult(bancoEntityParam);
		agenciaBancaria.setBanco(banco);
		
		agenciaBancaria.setNumeroAgencia(generateRandomString(50));
		agenciaBancaria.setDigitoAgencia(generateRandomString(10));
		agenciaBancaria.setEndereco(generateRandomString(255));
		agenciaBancaria.setDeleted(false);
		AgenciaBancariaEntity agenciaBancariaEntity = agenciaBancariaService.create(agenciaBancariaDTOConverter.convertDtoToEntity(agenciaBancaria));
		em.flush();
		verify(publisher, times(0)).publish(any());
		AgenciaBancaria actual = agenciaBancariaDTOConverter.convertEntityToDto(agenciaBancariaEntity);
		
		
		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isNotNull();
		assertThat(actual).isEqualToIgnoringGivenFields(agenciaBancaria, IGNORED_FIELDS);
		
		
		assertThat(actual.getBanco().getId()).isNotNull();
		assertThat(actual.getBanco()).isEqualToIgnoringGivenFields(agenciaBancaria.getBanco(), IGNORED_FIELDS);
		
		
	}
	
	@Test
	public void testCreateWithOnlyRecairedFields() throws Exception {
		AgenciaBancaria agenciaBancaria = new AgenciaBancaria();
		
		agenciaBancaria.setId(java.util.UUID.randomUUID());
		
		BancoEntity bancoEntityParam = newBancoEntity();
		BancoLookupResult banco = newBancoLookupResult(bancoEntityParam);
		agenciaBancaria.setBanco(banco);
		
		agenciaBancaria.setNumeroAgencia(generateRandomString(50));
		agenciaBancaria.setDigitoAgencia(generateRandomString(10));
		AgenciaBancariaEntity agenciaBancariaEntity = agenciaBancariaService.create(agenciaBancariaDTOConverter.convertDtoToEntity(agenciaBancaria));
		em.flush();
		verify(publisher, times(0)).publish(any());
		AgenciaBancaria actual = agenciaBancariaDTOConverter.convertEntityToDto(agenciaBancariaEntity);
		
		
		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isNotNull();
		assertThat(actual).isEqualToIgnoringGivenFields(agenciaBancaria, IGNORED_FIELDS);
		
		
		assertThat(actual.getBanco().getId()).isNotNull();
		assertThat(actual.getBanco()).isEqualToIgnoringGivenFields(agenciaBancaria.getBanco(), IGNORED_FIELDS);
		
		
	}
	// END CREATE TESTS
	
	// BEGIN READ TESTS
	
	@Test
	public void testRead1() {
		AgenciaBancariaEntity expectedAgenciaBancariaEntity = newAgenciaBancariaEntity();
		java.util.UUID id = expectedAgenciaBancariaEntity.getId();
		AgenciaBancaria expected = agenciaBancariaDTOConverter.convertEntityToDto(expectedAgenciaBancariaEntity);
		AgenciaBancariaEntity readAgenciaBancariaEntity = agenciaBancariaService.read(id);
		AgenciaBancaria actual = agenciaBancariaDTOConverter.convertEntityToDto(readAgenciaBancariaEntity);
		
		assertThat(actual).isEqualToComparingFieldByField(expected);
		
	}
	// END READ TESTS
	
	// BEGIN UPDATE TESTS
	
	@Test
	public void testUpdateWithAllFields() throws Exception {
		AgenciaBancariaEntity oldAgenciaBancariaEntity = newAgenciaBancariaEntity();
		java.util.UUID id = oldAgenciaBancariaEntity.getId();
				
		AgenciaBancaria agenciaBancaria = new AgenciaBancaria();
		agenciaBancaria.setId(id);
		
		
		BancoEntity bancoEntityParam = newBancoEntity();
		BancoLookupResult banco = newBancoLookupResult(bancoEntityParam);
		agenciaBancaria.setBanco(banco);
		
		agenciaBancaria.setNumeroAgencia(generateRandomString(50));
		agenciaBancaria.setDigitoAgencia(generateRandomString(10));
		agenciaBancaria.setEndereco(generateRandomString(255));
		agenciaBancaria.setDeleted(false);
		AgenciaBancariaEntity agenciaBancariaEntity = agenciaBancariaService.update(id, agenciaBancariaDTOConverter.convertDtoToEntity(agenciaBancaria));
		em.flush();
		verify(publisher, times(0)).publish(any());
		
		AgenciaBancaria actual = agenciaBancariaDTOConverter.convertEntityToDto(agenciaBancariaEntity);
		
		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isNotNull();
		assertThat(actual).isEqualToIgnoringGivenFields(agenciaBancaria, IGNORED_FIELDS);
		
		
		assertThat(actual.getBanco().getId()).isNotNull();
		assertThat(actual.getBanco()).isEqualToIgnoringGivenFields(agenciaBancaria.getBanco(), IGNORED_FIELDS);
		
		
	}
	
	@Test
	public void testUpdateWithOnlyRecairedFields() throws Exception {
		AgenciaBancariaEntity oldAgenciaBancariaEntity = newAgenciaBancariaEntity();
		java.util.UUID id = oldAgenciaBancariaEntity.getId();
				
		AgenciaBancaria agenciaBancaria = new AgenciaBancaria();
		agenciaBancaria.setId(id);
		
		
		BancoEntity bancoEntityParam = newBancoEntity();
		BancoLookupResult banco = newBancoLookupResult(bancoEntityParam);
		agenciaBancaria.setBanco(banco);
		
		agenciaBancaria.setNumeroAgencia(generateRandomString(50));
		agenciaBancaria.setDigitoAgencia(generateRandomString(10));
		AgenciaBancariaEntity agenciaBancariaEntity = agenciaBancariaService.update(id, agenciaBancariaDTOConverter.convertDtoToEntity(agenciaBancaria));
		em.flush();
		verify(publisher, times(0)).publish(any());
		
		AgenciaBancaria actual = agenciaBancariaDTOConverter.convertEntityToDto(agenciaBancariaEntity);
		
		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isNotNull();
		assertThat(actual).isEqualToIgnoringGivenFields(agenciaBancaria, IGNORED_FIELDS);
		
		
		assertThat(actual.getBanco().getId()).isNotNull();
		assertThat(actual.getBanco()).isEqualToIgnoringGivenFields(agenciaBancaria.getBanco(), IGNORED_FIELDS);
		
		
	}
	// END UPDATE TESTS
	
	// BEGIN DELETE TESTS
	
	@Test
	public void testDelete1() {
		AgenciaBancariaEntity expected = newAgenciaBancariaEntity();
		java.util.UUID id = expected.getId();
		
		
		expected = em.find(AgenciaBancariaEntity.class, id);
		assertThat(expected).isNotNull();
		agenciaBancariaService.delete(id);
		verify(publisher, times(0)).publish(any());
		
		expected = em.find(AgenciaBancariaEntity.class, id);
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
					
		// Generate 33 records of data for AgenciaBancariaEntity for this test.
		List<AgenciaBancariaEntity> testData = new ArrayList<>();
		final int lastRecord = 33;
		final int firstRecord = 1;
		for (int i = firstRecord; i <= lastRecord; i++) {
			testData.add(newAgenciaBancariaEntity());
		}
		
		// Check if 33 records of AgenciaBancariaEntity was generated.
		long count = agenciaBancariaRepository.count();
		assertThat(count).isEqualTo(lastRecord);
		
		// Extracts 1 records of AgenciaBancariaEntity randomly from testData.
		final int resultSize = 1;
		List<AgenciaBancariaEntity> filterTestData = getRandomItemsOf(testData, resultSize);
		
		// Extracts a list with only AgenciaBancariaEntity.numeroAgencia field and configure this list as a filter.
		List<String> numeroAgenciaListFilter = filterTestData.stream().map(AgenciaBancariaEntity::getNumeroAgencia).collect(Collectors.toList());
		// Mount the autocomplete query expression and call it.
		String query = numeroAgenciaListFilter.get(0);
		Collection<AgenciaBancariaAutoComplete> result = agenciaBancariaService.autoComplete(query);
		
		// Assert AgenciaBancariaAutoComplete results.
		assertThat(result).isNotNull().hasSize(1)
		.extracting(AgenciaBancariaAutoComplete::getNumeroAgencia)
		.containsExactlyInAnyOrderElementsOf(numeroAgenciaListFilter);
	}
	
	// END Autocomplete TESTS
	
	
	// BEGIN Relationships Autocomplete TESTS
	
	@Test
	public void testAgenciaBancariaBancoAutoComplete() {
		// Reset lastDate field to start LocalDate fields with today in this test. 
		resetNextDate();
					
		// Generate 33 records of data for BancoEntity for this test.
		List<BancoEntity> testData = new ArrayList<>();
		final int lastRecord = 33;
		final int firstRecord = 1;
		for (int i = firstRecord; i <= lastRecord; i++) {
			testData.add(newBancoEntity());
		}
		
		// Check if 33 records of BancoEntity was generated.
		long count = bancoRepository.count();
		assertThat(count).isEqualTo(lastRecord);
		
		// Extracts 1 records of BancoEntity randomly from testData.
		final int resultSize = 1;
		List<BancoEntity> filterTestData = getRandomItemsOf(testData, resultSize);
		
		// Extracts a list with only BancoEntity.numero field and configure this list as a filter.
		List<String> numeroListFilter = filterTestData.stream().map(BancoEntity::getNumero).collect(Collectors.toList());
		String query = numeroListFilter.get(0);
		
		Collection<BancoAutoComplete> result = agenciaBancariaService.bancoBancoAutoComplete(query);
		
		assertThat(result).isNotNull().hasSize(1)
		.extracting(BancoAutoComplete::getNumero)
		.containsExactlyInAnyOrderElementsOf(numeroListFilter);
	}
	
	// END Relationships Autocomplete TESTS
	
	// BEGIN tests for Sum Fields
	// END tests for Sum Fields
	
	// BEGIN tests for Sum Fields
	// END tests for Sum Fields
	
	// BEGIN TESTS DEPENDENCIES
	
	
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
	// END TESTS DEPENDENCIES

}
