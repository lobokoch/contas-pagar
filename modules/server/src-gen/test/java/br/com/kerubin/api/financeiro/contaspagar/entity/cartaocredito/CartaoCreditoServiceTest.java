/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-22T06:59:30.300
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.cartaocredito;

import br.com.kerubin.api.financeiro.contaspagar.entity.banco.BancoEntity;
import br.com.kerubin.api.financeiro.contaspagar.entity.bandeiracartao.BandeiraCartaoEntity;
import br.com.kerubin.api.financeiro.contaspagar.entity.banco.BancoLookupResult;
import br.com.kerubin.api.financeiro.contaspagar.entity.bandeiracartao.BandeiraCartaoLookupResult;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import javax.inject.Inject;
import br.com.kerubin.api.financeiro.contaspagar.entity.banco.BancoRepository;
import br.com.kerubin.api.financeiro.contaspagar.entity.bandeiracartao.BandeiraCartaoRepository;
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
import br.com.kerubin.api.financeiro.contaspagar.entity.bandeiracartao.BandeiraCartaoAutoComplete;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;
import br.com.kerubin.api.financeiro.contaspagar.FinanceiroContasPagarBaseEntityTest;


@RunWith(SpringRunner.class)
public class CartaoCreditoServiceTest extends FinanceiroContasPagarBaseEntityTest {
	
	private static final String[] IGNORED_FIELDS = { "id" };
	
	@TestConfiguration
	static class CartaoCreditoServiceTestConfig {
		
		@Bean
		public CartaoCreditoListFilterPredicate cartaoCreditoListFilterPredicate() {
			return new CartaoCreditoListFilterPredicateImpl();
		}
		
		@Bean
		public CartaoCreditoService cartaoCreditoService() {
			return new CartaoCreditoServiceImpl();
		}
		
		@Bean
		public CartaoCreditoDTOConverter cartaoCreditoDTOConverter() {
			return new CartaoCreditoDTOConverter();
		}
		
	}
	
	
	@Inject
	protected CartaoCreditoService cartaoCreditoService;
	
	@Inject
	protected CartaoCreditoDTOConverter cartaoCreditoDTOConverter;
	
	@Inject
	protected CartaoCreditoRepository cartaoCreditoRepository;
	
	@Inject
	protected BancoRepository bancoRepository;
	
	@Inject
	protected BandeiraCartaoRepository bandeiraCartaoRepository;
	
	@MockBean
	protected DomainEntityEventsPublisher publisher;
	
	// BEGIN CREATE TESTS
	
	@Test
	public void testCreateWithAllFields() throws Exception {
		CartaoCredito cartaoCredito = new CartaoCredito();
		
		cartaoCredito.setId(java.util.UUID.randomUUID());
		
		BancoEntity bancoEntityParam = newBancoEntity();
		BancoLookupResult banco = newBancoLookupResult(bancoEntityParam);
		cartaoCredito.setBanco(banco);
		
		cartaoCredito.setNomeTitular(generateRandomString(255));
		cartaoCredito.setNumeroCartao(generateRandomString(50));
		cartaoCredito.setValidade(getNextDate());
		cartaoCredito.setValorLimite(new java.math.BigDecimal("7603.6446"));
		
		BandeiraCartaoEntity bandeiraCartaoEntityParam = newBandeiraCartaoEntity();
		BandeiraCartaoLookupResult bandeiraCartao = newBandeiraCartaoLookupResult(bandeiraCartaoEntityParam);
		cartaoCredito.setBandeiraCartao(bandeiraCartao);
		
		cartaoCredito.setAtivo(true);
		cartaoCredito.setDeleted(false);
		CartaoCreditoEntity cartaoCreditoEntity = cartaoCreditoService.create(cartaoCreditoDTOConverter.convertDtoToEntity(cartaoCredito));
		em.flush();
		verify(publisher, times(0)).publish(any());
		CartaoCredito actual = cartaoCreditoDTOConverter.convertEntityToDto(cartaoCreditoEntity);
		
		
		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isNotNull();
		assertThat(actual).isEqualToIgnoringGivenFields(cartaoCredito, IGNORED_FIELDS);
		
		
		assertThat(actual.getBanco().getId()).isNotNull();
		assertThat(actual.getBanco()).isEqualToIgnoringGivenFields(cartaoCredito.getBanco(), IGNORED_FIELDS);
		
		
		assertThat(actual.getBandeiraCartao().getId()).isNotNull();
		assertThat(actual.getBandeiraCartao()).isEqualToIgnoringGivenFields(cartaoCredito.getBandeiraCartao(), IGNORED_FIELDS);
		
		
	}
	
	@Test
	public void testCreateWithOnlyRecairedFields() throws Exception {
		CartaoCredito cartaoCredito = new CartaoCredito();
		
		cartaoCredito.setId(java.util.UUID.randomUUID());
		
		BancoEntity bancoEntityParam = newBancoEntity();
		BancoLookupResult banco = newBancoLookupResult(bancoEntityParam);
		cartaoCredito.setBanco(banco);
		
		cartaoCredito.setNomeTitular(generateRandomString(255));
		cartaoCredito.setNumeroCartao(generateRandomString(50));
		
		BandeiraCartaoEntity bandeiraCartaoEntityParam = newBandeiraCartaoEntity();
		BandeiraCartaoLookupResult bandeiraCartao = newBandeiraCartaoLookupResult(bandeiraCartaoEntityParam);
		cartaoCredito.setBandeiraCartao(bandeiraCartao);
		
		cartaoCredito.setAtivo(true);
		CartaoCreditoEntity cartaoCreditoEntity = cartaoCreditoService.create(cartaoCreditoDTOConverter.convertDtoToEntity(cartaoCredito));
		em.flush();
		verify(publisher, times(0)).publish(any());
		CartaoCredito actual = cartaoCreditoDTOConverter.convertEntityToDto(cartaoCreditoEntity);
		
		
		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isNotNull();
		assertThat(actual).isEqualToIgnoringGivenFields(cartaoCredito, IGNORED_FIELDS);
		
		
		assertThat(actual.getBanco().getId()).isNotNull();
		assertThat(actual.getBanco()).isEqualToIgnoringGivenFields(cartaoCredito.getBanco(), IGNORED_FIELDS);
		
		
		assertThat(actual.getBandeiraCartao().getId()).isNotNull();
		assertThat(actual.getBandeiraCartao()).isEqualToIgnoringGivenFields(cartaoCredito.getBandeiraCartao(), IGNORED_FIELDS);
		
		
	}
	// END CREATE TESTS
	
	// BEGIN READ TESTS
	
	@Test
	public void testRead1() {
		CartaoCreditoEntity expectedCartaoCreditoEntity = newCartaoCreditoEntity();
		java.util.UUID id = expectedCartaoCreditoEntity.getId();
		CartaoCredito expected = cartaoCreditoDTOConverter.convertEntityToDto(expectedCartaoCreditoEntity);
		CartaoCreditoEntity readCartaoCreditoEntity = cartaoCreditoService.read(id);
		CartaoCredito actual = cartaoCreditoDTOConverter.convertEntityToDto(readCartaoCreditoEntity);
		
		assertThat(actual).isEqualToComparingFieldByField(expected);
		
	}
	// END READ TESTS
	
	// BEGIN UPDATE TESTS
	
	@Test
	public void testUpdateWithAllFields() throws Exception {
		CartaoCreditoEntity oldCartaoCreditoEntity = newCartaoCreditoEntity();
		java.util.UUID id = oldCartaoCreditoEntity.getId();
				
		CartaoCredito cartaoCredito = new CartaoCredito();
		cartaoCredito.setId(id);
		
		
		BancoEntity bancoEntityParam = newBancoEntity();
		BancoLookupResult banco = newBancoLookupResult(bancoEntityParam);
		cartaoCredito.setBanco(banco);
		
		cartaoCredito.setNomeTitular(generateRandomString(255));
		cartaoCredito.setNumeroCartao(generateRandomString(50));
		cartaoCredito.setValidade(getNextDate());
		cartaoCredito.setValorLimite(new java.math.BigDecimal("15631.17629"));
		
		BandeiraCartaoEntity bandeiraCartaoEntityParam = newBandeiraCartaoEntity();
		BandeiraCartaoLookupResult bandeiraCartao = newBandeiraCartaoLookupResult(bandeiraCartaoEntityParam);
		cartaoCredito.setBandeiraCartao(bandeiraCartao);
		
		cartaoCredito.setAtivo(true);
		cartaoCredito.setDeleted(false);
		CartaoCreditoEntity cartaoCreditoEntity = cartaoCreditoService.update(id, cartaoCreditoDTOConverter.convertDtoToEntity(cartaoCredito));
		em.flush();
		verify(publisher, times(0)).publish(any());
		
		CartaoCredito actual = cartaoCreditoDTOConverter.convertEntityToDto(cartaoCreditoEntity);
		
		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isNotNull();
		assertThat(actual).isEqualToIgnoringGivenFields(cartaoCredito, IGNORED_FIELDS);
		
		
		assertThat(actual.getBanco().getId()).isNotNull();
		assertThat(actual.getBanco()).isEqualToIgnoringGivenFields(cartaoCredito.getBanco(), IGNORED_FIELDS);
		
		
		assertThat(actual.getBandeiraCartao().getId()).isNotNull();
		assertThat(actual.getBandeiraCartao()).isEqualToIgnoringGivenFields(cartaoCredito.getBandeiraCartao(), IGNORED_FIELDS);
		
		
	}
	
	@Test
	public void testUpdateWithOnlyRecairedFields() throws Exception {
		CartaoCreditoEntity oldCartaoCreditoEntity = newCartaoCreditoEntity();
		java.util.UUID id = oldCartaoCreditoEntity.getId();
				
		CartaoCredito cartaoCredito = new CartaoCredito();
		cartaoCredito.setId(id);
		
		
		BancoEntity bancoEntityParam = newBancoEntity();
		BancoLookupResult banco = newBancoLookupResult(bancoEntityParam);
		cartaoCredito.setBanco(banco);
		
		cartaoCredito.setNomeTitular(generateRandomString(255));
		cartaoCredito.setNumeroCartao(generateRandomString(50));
		
		BandeiraCartaoEntity bandeiraCartaoEntityParam = newBandeiraCartaoEntity();
		BandeiraCartaoLookupResult bandeiraCartao = newBandeiraCartaoLookupResult(bandeiraCartaoEntityParam);
		cartaoCredito.setBandeiraCartao(bandeiraCartao);
		
		cartaoCredito.setAtivo(true);
		CartaoCreditoEntity cartaoCreditoEntity = cartaoCreditoService.update(id, cartaoCreditoDTOConverter.convertDtoToEntity(cartaoCredito));
		em.flush();
		verify(publisher, times(0)).publish(any());
		
		CartaoCredito actual = cartaoCreditoDTOConverter.convertEntityToDto(cartaoCreditoEntity);
		
		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isNotNull();
		assertThat(actual).isEqualToIgnoringGivenFields(cartaoCredito, IGNORED_FIELDS);
		
		
		assertThat(actual.getBanco().getId()).isNotNull();
		assertThat(actual.getBanco()).isEqualToIgnoringGivenFields(cartaoCredito.getBanco(), IGNORED_FIELDS);
		
		
		assertThat(actual.getBandeiraCartao().getId()).isNotNull();
		assertThat(actual.getBandeiraCartao()).isEqualToIgnoringGivenFields(cartaoCredito.getBandeiraCartao(), IGNORED_FIELDS);
		
		
	}
	// END UPDATE TESTS
	
	// BEGIN DELETE TESTS
	
	@Test
	public void testDelete1() {
		CartaoCreditoEntity expected = newCartaoCreditoEntity();
		java.util.UUID id = expected.getId();
		
		
		expected = em.find(CartaoCreditoEntity.class, id);
		assertThat(expected).isNotNull();
		cartaoCreditoService.delete(id);
		verify(publisher, times(0)).publish(any());
		
		expected = em.find(CartaoCreditoEntity.class, id);
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
		// Mount the autocomplete query expression and call it.
		String query = nomeTitularListFilter.get(0);
		Collection<CartaoCreditoAutoComplete> result = cartaoCreditoService.autoComplete(query);
		
		// Assert CartaoCreditoAutoComplete results.
		assertThat(result).isNotNull().hasSize(1)
		.extracting(CartaoCreditoAutoComplete::getNomeTitular)
		.containsExactlyInAnyOrderElementsOf(nomeTitularListFilter);
	}
	
	// END Autocomplete TESTS
	
	
	// BEGIN Relationships Autocomplete TESTS
	
	@Test
	public void testCartaoCreditoBancoAutoComplete() {
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
		
		Collection<BancoAutoComplete> result = cartaoCreditoService.bancoBancoAutoComplete(query);
		
		assertThat(result).isNotNull().hasSize(1)
		.extracting(BancoAutoComplete::getNumero)
		.containsExactlyInAnyOrderElementsOf(numeroListFilter);
	}
	
	
	@Test
	public void testCartaoCreditoBandeiraCartaoAutoComplete() {
		// Reset lastDate field to start LocalDate fields with today in this test. 
		resetNextDate();
					
		// Generate 33 records of data for BandeiraCartaoEntity for this test.
		List<BandeiraCartaoEntity> testData = new ArrayList<>();
		final int lastRecord = 33;
		final int firstRecord = 1;
		for (int i = firstRecord; i <= lastRecord; i++) {
			testData.add(newBandeiraCartaoEntity());
		}
		
		// Check if 33 records of BandeiraCartaoEntity was generated.
		long count = bandeiraCartaoRepository.count();
		assertThat(count).isEqualTo(lastRecord);
		
		// Extracts 1 records of BandeiraCartaoEntity randomly from testData.
		final int resultSize = 1;
		List<BandeiraCartaoEntity> filterTestData = getRandomItemsOf(testData, resultSize);
		
		// Extracts a list with only BandeiraCartaoEntity.nomeBandeira field and configure this list as a filter.
		List<String> nomeBandeiraListFilter = filterTestData.stream().map(BandeiraCartaoEntity::getNomeBandeira).collect(Collectors.toList());
		String query = nomeBandeiraListFilter.get(0);
		
		Collection<BandeiraCartaoAutoComplete> result = cartaoCreditoService.bandeiraCartaoBandeiraCartaoAutoComplete(query);
		
		assertThat(result).isNotNull().hasSize(1)
		.extracting(BandeiraCartaoAutoComplete::getNomeBandeira)
		.containsExactlyInAnyOrderElementsOf(nomeBandeiraListFilter);
	}
	
	// END Relationships Autocomplete TESTS
	
	// BEGIN tests for Sum Fields
	// END tests for Sum Fields
	
	// BEGIN tests for Sum Fields
	// END tests for Sum Fields
	
	// BEGIN TESTS DEPENDENCIES
	
	
	protected CartaoCreditoEntity newCartaoCreditoEntity() {
		CartaoCreditoEntity cartaoCreditoEntity = new CartaoCreditoEntity();
		
		cartaoCreditoEntity.setId(java.util.UUID.randomUUID());
		cartaoCreditoEntity.setBanco(newBancoEntity());
		cartaoCreditoEntity.setNomeTitular(generateRandomString(255));
		cartaoCreditoEntity.setNumeroCartao(generateRandomString(50));
		cartaoCreditoEntity.setValidade(getNextDate());
		cartaoCreditoEntity.setValorLimite(new java.math.BigDecimal("19734.22093"));
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
	// END TESTS DEPENDENCIES

}