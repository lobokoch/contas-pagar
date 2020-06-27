/**********************************************************************************************
Code generated with MKL Plug-in version: 27.0.12
Code generated at time stamp: 2019-11-06T06:23:00.711
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.conciliacaobancaria;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.kerubin.api.financeiro.contaspagar.FormaPagamento;
import br.com.kerubin.api.financeiro.contaspagar.TipoContaBancaria;
import br.com.kerubin.api.financeiro.contaspagar.TipoPessoa;
import br.com.kerubin.api.financeiro.contaspagar.TipoPlanoContaFinanceiro;
import br.com.kerubin.api.financeiro.contaspagar.TipoReceitaDespesa;
import br.com.kerubin.api.financeiro.contaspagar.entity.agenciabancaria.AgenciaBancariaEntity;
import br.com.kerubin.api.financeiro.contaspagar.entity.agenciabancaria.AgenciaBancariaLookupResult;
import br.com.kerubin.api.financeiro.contaspagar.entity.banco.BancoEntity;
import br.com.kerubin.api.financeiro.contaspagar.entity.banco.BancoLookupResult;
import br.com.kerubin.api.financeiro.contaspagar.entity.bandeiracartao.BandeiraCartaoEntity;
import br.com.kerubin.api.financeiro.contaspagar.entity.bandeiracartao.BandeiraCartaoLookupResult;
import br.com.kerubin.api.financeiro.contaspagar.entity.cartaocredito.CartaoCreditoEntity;
import br.com.kerubin.api.financeiro.contaspagar.entity.cartaocredito.CartaoCreditoLookupResult;
import br.com.kerubin.api.financeiro.contaspagar.entity.cartaocredito.CartaoCreditoRepository;
import br.com.kerubin.api.financeiro.contaspagar.entity.contabancaria.ContaBancariaEntity;
import br.com.kerubin.api.financeiro.contaspagar.entity.contabancaria.ContaBancariaLookupResult;
import br.com.kerubin.api.financeiro.contaspagar.entity.contabancaria.ContaBancariaRepository;
import br.com.kerubin.api.financeiro.contaspagar.entity.contapagar.ContaPagarBaseRepository;
import br.com.kerubin.api.financeiro.contaspagar.entity.contapagar.ContaPagarDTOConverter;
import br.com.kerubin.api.financeiro.contaspagar.entity.contapagar.ContaPagarEntity;
import br.com.kerubin.api.financeiro.contaspagar.entity.contapagar.ContaPagarListFilterPredicate;
import br.com.kerubin.api.financeiro.contaspagar.entity.contapagar.ContaPagarListFilterPredicateImpl;
import br.com.kerubin.api.financeiro.contaspagar.entity.contapagar.ContaPagarLookupResult;
import br.com.kerubin.api.financeiro.contaspagar.entity.contapagar.ContaPagarService;
import br.com.kerubin.api.financeiro.contaspagar.entity.contapagar.ContaPagarServiceImpl;
import br.com.kerubin.api.financeiro.contaspagar.entity.contapagarmultiple.ContaPagarMultipleListFilterPredicate;
import br.com.kerubin.api.financeiro.contaspagar.entity.contapagarmultiple.ContaPagarMultipleListFilterPredicateImpl;
import br.com.kerubin.api.financeiro.contaspagar.entity.contapagarmultiple.ContaPagarMultipleService;
import br.com.kerubin.api.financeiro.contaspagar.entity.contapagarmultiple.ContaPagarMultipleServiceImpl;
import br.com.kerubin.api.financeiro.contaspagar.entity.fornecedor.FornecedorEntity;
import br.com.kerubin.api.financeiro.contaspagar.entity.fornecedor.FornecedorLookupResult;
import br.com.kerubin.api.financeiro.contaspagar.entity.fornecedor.FornecedorRepository;
import br.com.kerubin.api.financeiro.contaspagar.entity.planoconta.PlanoContaEntity;
import br.com.kerubin.api.financeiro.contaspagar.entity.planoconta.PlanoContaListFilterPredicate;
import br.com.kerubin.api.financeiro.contaspagar.entity.planoconta.PlanoContaListFilterPredicateImpl;
import br.com.kerubin.api.financeiro.contaspagar.entity.planoconta.PlanoContaLookupResult;
import br.com.kerubin.api.financeiro.contaspagar.entity.planoconta.PlanoContaRepository;
import br.com.kerubin.api.financeiro.contaspagar.entity.planoconta.PlanoContaService;
import br.com.kerubin.api.financeiro.contaspagar.entity.planoconta.PlanoContaServiceImpl;
import br.com.kerubin.api.messaging.core.DomainEntityEventsPublisher;
import br.com.kerubin.api.servicecore.util.CoreUtils;


@RunWith(SpringRunner.class)
public class VerificarTransacoesConciliacaoBancariaServiceTest extends FinanceiroContasPagarBaseEntityTest {
	
	private static final String BANCO_ID = "237";
	private static final String AGENDIA_ID = "12345";
	private static final String CONTA_BANCARIA_ID = "98765";
	private static final BigDecimal VALOR = new BigDecimal("1269.4");
	
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
		
		@Bean
		public ConciliacaoBancariaService conciliacaoBancariaService() {
			return new ConciliacaoBancariaServiceImpl();
		}
		
		@Bean
		public PlanoContaService planoContaService() {
			return new PlanoContaServiceImpl(); 
		}
		
		@Bean
		public PlanoContaListFilterPredicate planoContaListFilterPredicate() {
			return new PlanoContaListFilterPredicateImpl();
		}
		
		@Bean
		public ContaPagarMultipleListFilterPredicate contaPagarMultipleListFilterPredicate() {
			return new ContaPagarMultipleListFilterPredicateImpl();
		}
		
		@Bean
		public ContaPagarMultipleService contaPagarMultipleService() {
			return new ContaPagarMultipleServiceImpl(); 
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
	
	@Inject
	private ConciliacaoBancariaService conciliacaoBancariaService;
	
	@Test
	public void testScores() {
		
		List<ContaPagarEntity> contas = new ArrayList<>(); 
		
		ContaPagarEntity c = new ContaPagarEntity();
		c.setDescricao("Conta de energia elétrica");
		c.setDataVencimento(LocalDate.of(2020, 01, 23));
		c.setDataPagamento(LocalDate.of(2020, 01, 23));
		c.setValor(BigDecimal.valueOf(181.50));
		c.setValorPago(BigDecimal.valueOf(181.50));
		
		FornecedorEntity f = new FornecedorEntity();
		f.setNome("Celesc Santa Catarina");
		c.setFornecedor(f);
		
		ConciliacaoTransacaoDTO transacao = new ConciliacaoTransacaoDTO();
		transacao.setTrnHistorico("Celesc Sant. Catarin. Energ. Elétr.");
		transacao.setTrnData(LocalDate.of(2020, 01, 23));
		transacao.setTrnValor(BigDecimal.valueOf(181.50));
		
		contas.add(c);
		
		List<String> tokens = CoreUtils.getTokens(transacao.getTrnHistorico());
		Map<ContaPagarEntity, Integer> scores = conciliacaoBancariaService.computeScore(contas, tokens, transacao);
		assertThat(scores).containsValue(9462);
		
		contas.clear();
		
		c = new ContaPagarEntity();
		c.setDescricao("Conta de gáz");
		c.setDataVencimento(LocalDate.of(2020, 01, 23));
		c.setDataPagamento(LocalDate.of(2020, 01, 23));
		c.setValor(BigDecimal.valueOf(181.50));
		c.setValorPago(BigDecimal.valueOf(181.50));
		
		f = new FornecedorEntity();
		f.setNome("Petrobras");
		c.setFornecedor(f);
		
		transacao = new ConciliacaoTransacaoDTO();
		transacao.setTrnHistorico("Celesc Sant. Catarin. Energ. Elétr.");
		transacao.setTrnData(LocalDate.of(2020, 01, 23));
		transacao.setTrnValor(BigDecimal.valueOf(181.99));
		
		contas.add(c);
		
		tokens = CoreUtils.getTokens(transacao.getTrnHistorico());
		scores = conciliacaoBancariaService.computeScore(contas, tokens, transacao);
		assertThat(scores).containsValue(0);
		
		contas.clear();
		
		c = new ContaPagarEntity();
		c.setDescricao("Conta de energia elétrica");
		c.setDataVencimento(LocalDate.of(2020, 01, 23));
		c.setDataPagamento(LocalDate.of(2020, 01, 23));
		c.setValor(BigDecimal.valueOf(181.50));
		c.setValorPago(BigDecimal.valueOf(181.50));
		
		f = new FornecedorEntity();
		f.setNome("Celesc Santa Catarina");
		c.setFornecedor(f);
		
		transacao = new ConciliacaoTransacaoDTO();
		transacao.setTrnHistorico("Celesc Sant. Catarin. Energ. Elétr.");
		transacao.setTrnData(LocalDate.of(2020, 01, 23));
		transacao.setTrnValor(BigDecimal.valueOf(181.99));
		
		contas.add(c);
		
		tokens = CoreUtils.getTokens(transacao.getTrnHistorico());
		scores = conciliacaoBancariaService.computeScore(contas, tokens, transacao);
		assertThat(scores).containsValue(5);
		
		contas.clear();
		
		c = new ContaPagarEntity();
		c.setDescricao("Conta de energia elétrica");
		c.setDataVencimento(LocalDate.of(2020, 01, 23));
		//c.setDataPagamento(LocalDate.of(2020, 01, 23));
		c.setValor(BigDecimal.valueOf(181.50));
		//c.setValorPago(BigDecimal.valueOf(181.50));
		
		f = new FornecedorEntity();
		f.setNome("Celesc Santa Catarina");
		c.setFornecedor(f);
		
		transacao = new ConciliacaoTransacaoDTO();
		transacao.setTrnHistorico("Celesc Sant. Catarin. Energ. Elétr.");
		transacao.setTrnData(LocalDate.of(2020, 01, 23));
		transacao.setTrnValor(BigDecimal.valueOf(181.50));
		
		contas.add(c);
		
		tokens = CoreUtils.getTokens(transacao.getTrnHistorico());
		scores = conciliacaoBancariaService.computeScore(contas, tokens, transacao);
		assertThat(scores).containsValue(8465);
		
		contas.clear();
		
		c = new ContaPagarEntity();
		c.setDescricao("Conta de energia elétrica");
		c.setDataVencimento(LocalDate.of(2020, 01, 25));
		//c.setDataPagamento(LocalDate.of(2020, 01, 23));
		c.setValor(BigDecimal.valueOf(181.50));
		//c.setValorPago(BigDecimal.valueOf(181.50));
		
		f = new FornecedorEntity();
		f.setNome("Celesc Santa Catarina");
		c.setFornecedor(f);
		
		transacao = new ConciliacaoTransacaoDTO();
		transacao.setTrnHistorico("Celesc Sant. Catarin. Energ. Elétr.");
		transacao.setTrnData(LocalDate.of(2020, 01, 23));
		transacao.setTrnValor(BigDecimal.valueOf(181.50));
		
		contas.add(c);
		
		tokens = CoreUtils.getTokens(transacao.getTrnHistorico());
		scores = conciliacaoBancariaService.computeScore(contas, tokens, transacao);
		assertThat(scores).containsValue(7924);
		
		
	}
	
	@Test
	public void testVerificarTransacoes_Primeira() {
		List<ContaPagarEntity> contas = criarContas();
		ConciliacaoBancariaDTO conciliacaoBancariaDTO = newConciliacaoBancariaDTO();
		
		List<ConciliacaoTransacaoDTO> transacoes = new ArrayList<>();
		ConciliacaoTransacaoDTO t1 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(VALOR)
				.trnTipo(TipoTransacao.DEBITO)
				.trnData(LocalDate.of(2019, 6, 10))
				.tituloConciliadoId(null)
				.trnId("123")
				.trnDocumento("123")
				.trnHistorico("Teste 123")
				.build();
		
		transacoes.add(t1);
		
		conciliacaoBancariaDTO.setTransacoes(transacoes);
		
		conciliacaoBancariaDTO = conciliacaoBancariaService.verificarTransacoes(conciliacaoBancariaDTO);
		
		transacoes = conciliacaoBancariaDTO.getTransacoes();
		
		ContaPagarEntity conta1 = contas.get(0);
		
		assertThat(transacoes).hasSize(1)
		.extracting(ConciliacaoTransacaoDTO::getTituloConciliadoId, ConciliacaoTransacaoDTO::getTituloConciliadoDesc, ConciliacaoTransacaoDTO::getSituacaoConciliacaoTrn)
		.contains(tuple(conta1.getId(), conta1.getDescricao(), SituacaoConciliacaoTrn.CONCILIAR_CONTAS_PAGAR));
	}
	
	@Test
	public void testVerificarTransacoes2_PrimeiraPagaRetornaSegunda() {
		List<ContaPagarEntity> contas = criarContas();
		ConciliacaoBancariaDTO conciliacaoBancariaDTO = newConciliacaoBancariaDTO();
		
		ContaPagarEntity conat1 = contas.get(0);
		conat1 = pagarConta(conat1, LocalDate.of(2019, 6, 10));
		
		List<ConciliacaoTransacaoDTO> transacoes = new ArrayList<>();
		ConciliacaoTransacaoDTO t1 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(VALOR)
				.trnTipo(TipoTransacao.DEBITO)
				.trnData(LocalDate.of(2019, 07, 9))
				.tituloConciliadoId(null)
				.trnId("123")
				.trnDocumento("123")
				.trnHistorico("Teste 123")
				.build();
		
		transacoes.add(t1);
		
		conciliacaoBancariaDTO.setTransacoes(transacoes);
		
		conciliacaoBancariaDTO = conciliacaoBancariaService.verificarTransacoes(conciliacaoBancariaDTO);
		
		transacoes = conciliacaoBancariaDTO.getTransacoes();
		
		ContaPagarEntity conta2 = contas.get(1);
		
		assertThat(transacoes).hasSize(1)
		.extracting(ConciliacaoTransacaoDTO::getTituloConciliadoId, ConciliacaoTransacaoDTO::getTituloConciliadoDesc, ConciliacaoTransacaoDTO::getSituacaoConciliacaoTrn)
		.contains(tuple(conta2.getId(), conta2.getDescricao(), SituacaoConciliacaoTrn.CONCILIAR_CONTAS_PAGAR));
	}
	
	@Test
	public void testVerificarTransacoes_RetornaConta3() {
		List<ContaPagarEntity> contas = criarContas();
		ConciliacaoBancariaDTO conciliacaoBancariaDTO = newConciliacaoBancariaDTO();
		
		ContaPagarEntity conat1 = contas.get(0);
		conat1 = pagarConta(conat1, LocalDate.of(2019, 6, 10));
		
		ContaPagarEntity conat2 = contas.get(1);
		conat2 = pagarConta(conat2, LocalDate.of(2019, 7, 9));
		
		List<ConciliacaoTransacaoDTO> transacoes = new ArrayList<>();
		ConciliacaoTransacaoDTO t1 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(VALOR)
				.trnTipo(TipoTransacao.DEBITO)
				.trnData(LocalDate.of(2019, 8, 7))
				.tituloConciliadoId(null)
				.trnId("123")
				.trnDocumento("123")
				.trnHistorico("Teste 123")
				.build();
		
		transacoes.add(t1);
		
		conciliacaoBancariaDTO.setTransacoes(transacoes);
		
		conciliacaoBancariaDTO = conciliacaoBancariaService.verificarTransacoes(conciliacaoBancariaDTO);
		
		transacoes = conciliacaoBancariaDTO.getTransacoes();
		
		ContaPagarEntity conta3 = contas.get(2);
		
		assertThat(transacoes).hasSize(1)
		.extracting(ConciliacaoTransacaoDTO::getTituloConciliadoId, ConciliacaoTransacaoDTO::getTituloConciliadoDesc, ConciliacaoTransacaoDTO::getSituacaoConciliacaoTrn)
		.contains(tuple(conta3.getId(), conta3.getDescricao(), SituacaoConciliacaoTrn.CONCILIAR_CONTAS_PAGAR));
	}
	
	@Test
	public void testVerificarTransacoes_RetornaConta4() {
		List<ContaPagarEntity> contas = criarContas();
		ConciliacaoBancariaDTO conciliacaoBancariaDTO = newConciliacaoBancariaDTO();
		
		ContaPagarEntity conat1 = contas.get(0);
		conat1 = pagarConta(conat1, LocalDate.of(2019, 6, 10));
		
		ContaPagarEntity conat2 = contas.get(1);
		conat2 = pagarConta(conat2, LocalDate.of(2019, 7, 9));
		
		ContaPagarEntity conat3 = contas.get(2);
		conat3 = pagarConta(conat3, LocalDate.of(2019, 8, 7));
		
		List<ConciliacaoTransacaoDTO> transacoes = new ArrayList<>();
		ConciliacaoTransacaoDTO t1 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(VALOR)
				.trnTipo(TipoTransacao.DEBITO)
				.trnData(LocalDate.of(2019, 9, 9))
				.tituloConciliadoId(null)
				.trnId("123")
				.trnDocumento("123")
				.trnHistorico("Teste 123")
				.build();
		
		transacoes.add(t1);
		
		conciliacaoBancariaDTO.setTransacoes(transacoes);
		
		conciliacaoBancariaDTO = conciliacaoBancariaService.verificarTransacoes(conciliacaoBancariaDTO);
		
		transacoes = conciliacaoBancariaDTO.getTransacoes();
		
		ContaPagarEntity conta4 = contas.get(3);
		
		assertThat(transacoes).hasSize(1)
		.extracting(ConciliacaoTransacaoDTO::getTituloConciliadoId, ConciliacaoTransacaoDTO::getTituloConciliadoDesc, ConciliacaoTransacaoDTO::getSituacaoConciliacaoTrn)
		.contains(tuple(conta4.getId(), conta4.getDescricao(), SituacaoConciliacaoTrn.CONCILIAR_CONTAS_PAGAR));
	}
	
	@Test
	public void testVerificarTransacoes_RetornaConta5() {
		List<ContaPagarEntity> contas = criarContas();
		ConciliacaoBancariaDTO conciliacaoBancariaDTO = newConciliacaoBancariaDTO();
		
		ContaPagarEntity conat1 = contas.get(0);
		conat1 = pagarConta(conat1, LocalDate.of(2019, 6, 10));
		
		ContaPagarEntity conat2 = contas.get(1);
		conat2 = pagarConta(conat2, LocalDate.of(2019, 7, 9));
		
		ContaPagarEntity conat3 = contas.get(2);
		conat3 = pagarConta(conat3, LocalDate.of(2019, 8, 7));
		
		ContaPagarEntity conat4 = contas.get(3);
		conat4 = pagarConta(conat4, LocalDate.of(2019, 9, 9));
		
		List<ConciliacaoTransacaoDTO> transacoes = new ArrayList<>();
		ConciliacaoTransacaoDTO t1 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(VALOR)
				.trnTipo(TipoTransacao.DEBITO)
				.trnData(LocalDate.of(2019, 10, 7))
				.tituloConciliadoId(null)
				.trnId("123")
				.trnDocumento("123")
				.trnHistorico("Teste 123")
				.build();
		
		transacoes.add(t1);
		
		conciliacaoBancariaDTO.setTransacoes(transacoes);
		
		conciliacaoBancariaDTO = conciliacaoBancariaService.verificarTransacoes(conciliacaoBancariaDTO);
		
		transacoes = conciliacaoBancariaDTO.getTransacoes();
		
		ContaPagarEntity conta5 = contas.get(4);
		
		assertThat(transacoes).hasSize(1)
		.extracting(ConciliacaoTransacaoDTO::getTituloConciliadoId, ConciliacaoTransacaoDTO::getTituloConciliadoDesc, ConciliacaoTransacaoDTO::getSituacaoConciliacaoTrn)
		.contains(tuple(conta5.getId(), conta5.getDescricao(), SituacaoConciliacaoTrn.CONCILIAR_CONTAS_PAGAR));
	}
	
	@Test
	public void testVerificarTransacoes_Retorna5Contas() {
		List<ContaPagarEntity> contas = criarContas();
		ConciliacaoBancariaDTO conciliacaoBancariaDTO = newConciliacaoBancariaDTO();
		
		List<ConciliacaoTransacaoDTO> transacoes = new ArrayList<>();
		ConciliacaoTransacaoDTO t1 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(VALOR)
				.trnTipo(TipoTransacao.DEBITO)
				.trnData(LocalDate.of(2019, 10, 7))
				.trnId("123")
				.trnDocumento("123")
				.trnHistorico("bla")
				.build();
		
		ConciliacaoTransacaoDTO t2 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(VALOR)
				.trnTipo(TipoTransacao.DEBITO)
				.trnData(LocalDate.of(2019, 7, 9))
				.trnId("123")
				.trnDocumento("123")
				.trnHistorico("ble")
				.build();
		
		ConciliacaoTransacaoDTO t3 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(VALOR)
				.trnTipo(TipoTransacao.DEBITO)
				.trnData(LocalDate.of(2019, 8, 7))
				.trnId("123")
				.trnDocumento("123")
				.trnHistorico("bli")
				.build();
		
		ConciliacaoTransacaoDTO t4 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(VALOR)
				.trnTipo(TipoTransacao.DEBITO)
				.trnData(LocalDate.of(2019, 9, 9))
				.trnId("123")
				.trnDocumento("123")
				.trnHistorico("blo")
				.build();
		
		ConciliacaoTransacaoDTO t5 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(VALOR)
				.trnTipo(TipoTransacao.DEBITO)
				.trnData(LocalDate.of(2019, 10, 7))
				.trnId("123")
				.trnDocumento("123")
				.trnHistorico("blu")
				.build();
		
		transacoes.add(t1);
		transacoes.add(t2);
		transacoes.add(t3);
		transacoes.add(t4);
		transacoes.add(t5);
		
		conciliacaoBancariaDTO.setTransacoes(transacoes);
		
		conciliacaoBancariaDTO = conciliacaoBancariaService.verificarTransacoes(conciliacaoBancariaDTO);
		
		transacoes = conciliacaoBancariaDTO.getTransacoes();
		
		ContaPagarEntity conta1 = contas.get(0);
		ContaPagarEntity conta2 = contas.get(1);
		ContaPagarEntity conta3 = contas.get(2);
		ContaPagarEntity conta4 = contas.get(3);
		//ContaPagarEntity conta5 = contas.get(4);
		
		assertThat(transacoes).hasSize(5)
		.extracting(ConciliacaoTransacaoDTO::getTituloConciliadoId, ConciliacaoTransacaoDTO::getTituloConciliadoDesc, ConciliacaoTransacaoDTO::getSituacaoConciliacaoTrn)
		.contains(
				tuple(conta1.getId(), conta1.getDescricao(), SituacaoConciliacaoTrn.CONCILIAR_CONTAS_PAGAR),
				tuple(conta2.getId(), conta2.getDescricao(), SituacaoConciliacaoTrn.CONCILIAR_CONTAS_PAGAR),
				tuple(conta3.getId(), conta3.getDescricao(), SituacaoConciliacaoTrn.CONCILIAR_CONTAS_PAGAR),
				tuple(conta4.getId(), conta4.getDescricao(), SituacaoConciliacaoTrn.CONCILIAR_CONTAS_PAGAR),
				tuple(conta4.getId(), conta4.getDescricao(), SituacaoConciliacaoTrn.CONCILIAR_CONTAS_PAGAR)
				);
	}
	
	@Test
	public void testVerificarTransacoes_Retorna4ContasPagas() {
		List<ContaPagarEntity> contas = criarContas();
		ConciliacaoBancariaDTO conciliacaoBancariaDTO = newConciliacaoBancariaDTO();
		
		ContaPagarEntity conat1 = contas.get(0);
		conat1 = pagarConta(conat1, LocalDate.of(2019, 6, 10));
		
		ContaPagarEntity conat2 = contas.get(1);
		conat2.setIdConcBancaria("0123456789");
		conat2.setNumDocConcBancaria("0123456789");
		conat2 = pagarConta(conat2, LocalDate.of(2019, 7, 9));
		
		ContaPagarEntity conat3 = contas.get(2);
		conat3 = pagarConta(conat3, LocalDate.of(2019, 8, 7));
		
		ContaPagarEntity conat4 = contas.get(3);
		conat4 = pagarConta(conat4, LocalDate.of(2019, 9, 9));
		
		List<ConciliacaoTransacaoDTO> transacoes = new ArrayList<>();
		ConciliacaoTransacaoDTO t1 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(VALOR)
				.trnTipo(TipoTransacao.DEBITO)
				.trnData(LocalDate.of(2019, 6, 10))
				.trnId("123")
				.trnDocumento("123")
				.trnHistorico("Teste 123")
				.build();
		
		ConciliacaoTransacaoDTO t2 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(VALOR)
				.trnTipo(TipoTransacao.DEBITO)
				.trnData(LocalDate.of(2019, 7, 9))
				.trnId("123")
				.trnDocumento("123")
				.trnHistorico("Teste 123")
				.build();
		
		ConciliacaoTransacaoDTO t3 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(VALOR)
				.trnTipo(TipoTransacao.DEBITO)
				.trnData(LocalDate.of(2019, 8, 7))
				.trnId("123")
				.trnDocumento("123")
				.trnHistorico("Teste 123")
				.build();
		
		ConciliacaoTransacaoDTO t4 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(VALOR)
				.trnTipo(TipoTransacao.DEBITO)
				.trnData(LocalDate.of(2019, 9, 9))
				.trnId("123")
				.trnDocumento("123")
				.trnHistorico("Teste 123")
				.build();
		
		ConciliacaoTransacaoDTO t5 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(VALOR)
				.trnTipo(TipoTransacao.DEBITO)
				.trnData(LocalDate.of(2019, 10, 7))
				.trnId("123")
				.trnDocumento("123")
				.trnHistorico("Teste 123")
				.build();
		
		transacoes.add(t1);
		transacoes.add(t2);
		transacoes.add(t3);
		transacoes.add(t4);
		transacoes.add(t5);
		
		conciliacaoBancariaDTO.setTransacoes(transacoes);
		
		conciliacaoBancariaDTO = conciliacaoBancariaService.verificarTransacoes(conciliacaoBancariaDTO);
		
		transacoes = conciliacaoBancariaDTO.getTransacoes();
		
		ContaPagarEntity conta1 = contas.get(0);
		ContaPagarEntity conta2 = contas.get(1);
		ContaPagarEntity conta3 = contas.get(2);
		//ContaPagarEntity conta4 = contas.get(3);
		ContaPagarEntity conta5 = contas.get(4);
		
		assertThat(transacoes).hasSize(5)
		.extracting(ConciliacaoTransacaoDTO::getTituloConciliadoId, ConciliacaoTransacaoDTO::getTituloConciliadoDesc, ConciliacaoTransacaoDTO::getSituacaoConciliacaoTrn)
		.contains(
				tuple(conta1.getId(), conta1.getDescricao(), SituacaoConciliacaoTrn.CONTAS_PAGAR_BAIXADO_SEM_CONCILIACAO),
				tuple(conta1.getId(), conta1.getDescricao(), SituacaoConciliacaoTrn.CONTAS_PAGAR_BAIXADO_SEM_CONCILIACAO),
				tuple(conta2.getId(), conta2.getDescricao(), SituacaoConciliacaoTrn.CONCILIADO_CONTAS_PAGAR),
				tuple(conta3.getId(), conta3.getDescricao(), SituacaoConciliacaoTrn.CONTAS_PAGAR_BAIXADO_SEM_CONCILIACAO),
				tuple(conta5.getId(), conta5.getDescricao(), SituacaoConciliacaoTrn.CONCILIAR_CONTAS_PAGAR)
				);
	}
	
	@Test
	public void testParcelasPagasAdiantadas() {
		
		int quantidade = 5;
		LocalDate dataInicial = LocalDate.of(2019,10, 10);
		List<ContaPagarEntity> contas = criarContaPagar(quantidade, dataInicial, VALOR);
		assertThat(contas).hasSize(quantidade);
		assertThat(contas.get(0).getDataVencimento()).isEqualTo(LocalDate.of(2019, 10, 10));
		assertThat(contas.get(contas.size() - 1)
				.getDataVencimento()).isEqualTo(LocalDate.of(2020, 02, 10));
		
		ConciliacaoBancariaDTO conciliacaoBancariaDTO = newConciliacaoBancariaDTO();
		
		List<ConciliacaoTransacaoDTO> transacoes = new ArrayList<>();
		List<LocalDate> datas = Arrays.asList(LocalDate.of(2019, 10, 10), 
				LocalDate.of(2019, 10, 10), 
				LocalDate.of(2019, 10, 10),
				LocalDate.of(2019, 10, 11),
				LocalDate.of(2019, 10, 13)
				);
		
		for (int i = 0; i < datas.size(); i++) {
			ConciliacaoTransacaoDTO t = ConciliacaoTransacaoDTO.builder()
					.id(UUID.randomUUID())
					.trnValor(VALOR)
					.trnTipo(TipoTransacao.DEBITO)
					.trnData(datas.get(i))
					.trnId("123")
					.trnDocumento("123")
					.trnHistorico("Teste 123")
					.build();
			
			transacoes.add(t);
		}
		
		
		conciliacaoBancariaDTO.setTransacoes(transacoes);
		
		conciliacaoBancariaDTO = conciliacaoBancariaService.verificarTransacoes(conciliacaoBancariaDTO);
		transacoes = conciliacaoBancariaDTO.getTransacoes();
		
		int i = 0;
		ConciliacaoTransacaoDTO t0 = transacoes.get(i);
		ConciliacaoTransacaoTituloDTO titulo = t0.getConciliacaoTransacaoTitulosDTO()
				.stream()
				.filter(it -> it.getTituloConciliadoId().equals(contas.get(0).getId())) // Um dos títulos tem que ser a contas a pagar.
				.findFirst().orElse(null);
		
		assertThat(titulo.getTituloConciliadoDataVen()).isEqualTo(contas.get(i).getDataVencimento());
		
		i++;
		ConciliacaoTransacaoDTO t1 = transacoes.get(i);
		titulo = t1.getConciliacaoTransacaoTitulosDTO()
			.stream()
			.filter(it -> it.getTituloConciliadoId().equals(contas.get(1).getId())) // Um dos títulos tem que ser a contas a pagar.
			.findFirst().orElse(null);
		
		assertThat(titulo.getTituloConciliadoDataVen()).isEqualTo(contas.get(i).getDataVencimento());
		
		i++;
		ConciliacaoTransacaoDTO t2 = transacoes.get(i);
		titulo = t2.getConciliacaoTransacaoTitulosDTO()
				.stream()
				.filter(it -> it.getTituloConciliadoId().equals(contas.get(2).getId())) // Um dos títulos tem que ser a contas a pagar.
				.findFirst().orElse(null);
		
		assertThat(titulo.getTituloConciliadoDataVen()).isEqualTo(contas.get(i).getDataVencimento());
		
		i++;
		ConciliacaoTransacaoDTO t3 = transacoes.get(i);
		titulo = t3.getConciliacaoTransacaoTitulosDTO()
				.stream()
				.filter(it -> it.getTituloConciliadoId().equals(contas.get(3).getId())) // Um dos títulos tem que ser a contas a pagar.
				.findFirst().orElse(null);
		
		assertThat(titulo.getTituloConciliadoDataVen()).isEqualTo(contas.get(i).getDataVencimento());
		
		i++;
		ConciliacaoTransacaoDTO t4 = transacoes.get(i);
		titulo = t4.getConciliacaoTransacaoTitulosDTO()
				.stream()
				.filter(it -> it.getTituloConciliadoId().equals(contas.get(4).getId())) // Um dos títulos tem que ser a contas a pagar.
				.findFirst().orElse(null);
		
		assertThat(titulo.getTituloConciliadoDataVen()).isEqualTo(contas.get(i).getDataVencimento());
		
	}
	
	@Test
	public void testAcharParcelasPelaDescricao() {
		
		int quantidade = 5;
		LocalDate dataInicial = LocalDate.of(2019,10, 10);
		List<ContaPagarEntity> contas = criarContaPagar(quantidade, dataInicial, VALOR);
		assertThat(contas).hasSize(quantidade);
		assertThat(contas.get(0).getDataVencimento()).isEqualTo(LocalDate.of(2019, 10, 10));
		assertThat(contas.get(contas.size() - 1)
				.getDataVencimento()).isEqualTo(LocalDate.of(2020, 02, 10));
		
		
		contas.get(0).setValor(new BigDecimal("100"));
		contas.get(0).setDescricao("Luz");
		
		contas.get(1).setValor(new BigDecimal("300"));
		contas.get(1).setDescricao("Corte de cabelo Márcio no Shoping Park Europeu");
		
		contas.get(2).setValor(new BigDecimal("200"));
		contas.get(2).setDescricao("Compra no supermercado Giassi");
		
		contas.get(3).setValor(new BigDecimal("400"));
		contas.get(3).setDescricao("Assinatura da Sky");
		
		contas.get(4).setValor(new BigDecimal("500"));
		contas.get(4).setDescricao("Colégio Mateus - Bom Jesus");
		em.flush();
		
		
		ConciliacaoBancariaDTO conciliacaoBancariaDTO = newConciliacaoBancariaDTO();
		
		List<ConciliacaoTransacaoDTO> transacoes = new ArrayList<>();
		
		for (int i = 0; i < contas.size(); i++) {
			ConciliacaoTransacaoDTO t = ConciliacaoTransacaoDTO.builder()
					.id(UUID.randomUUID())
					.trnValor(VALOR)
					.trnTipo(TipoTransacao.DEBITO)
					.trnData(contas.get(i).getDataVencimento())
					.trnId("123")
					.trnDocumento("123")
					.trnHistorico("Teste 123")
					.build();
			
			transacoes.add(t);
		}
		
		transacoes.get(0).setTrnHistorico("Conta de Luz Bradesco C-celesc Distr./sc");
		transacoes.get(1).setTrnHistorico("Visa Electron Barbearia Park Europ");
		transacoes.get(2).setTrnHistorico("Visa Electron Giassi Supermerca");
		transacoes.get(3).setTrnHistorico("tv p/assinatura Sky-tv Assinatura*-118774910");
		transacoes.get(4).setTrnHistorico("Pagto Cobranca Bom Jesus");
		
		conciliacaoBancariaDTO.setTransacoes(transacoes);
		
		conciliacaoBancariaDTO = conciliacaoBancariaService.verificarTransacoes(conciliacaoBancariaDTO);
		transacoes = conciliacaoBancariaDTO.getTransacoes();
		
		assertThat(transacoes.get(0).getConciliacaoTransacaoTitulosDTO()).hasSize(1)
		.extracting(ConciliacaoTransacaoTituloDTO::getTituloConciliadoId)
		.contains(contas.get(0).getId());
		
		assertThat(transacoes.get(1).getConciliacaoTransacaoTitulosDTO()).hasSize(1)
		.extracting(ConciliacaoTransacaoTituloDTO::getTituloConciliadoId)
		.contains(contas.get(1).getId());
		
		assertThat(transacoes.get(2).getConciliacaoTransacaoTitulosDTO()).hasSize(1)
		.extracting(ConciliacaoTransacaoTituloDTO::getTituloConciliadoId)
		.contains(contas.get(2).getId());
		
		assertThat(transacoes.get(3).getConciliacaoTransacaoTitulosDTO()).hasSize(1)
		.extracting(ConciliacaoTransacaoTituloDTO::getTituloConciliadoId)
		.contains(contas.get(3).getId());
		
		assertThat(transacoes.get(4).getConciliacaoTransacaoTitulosDTO()).hasSize(1)
		.extracting(ConciliacaoTransacaoTituloDTO::getTituloConciliadoId)
		.contains(contas.get(4).getId());		
		
	}
	
	
	private ConciliacaoBancariaDTO newConciliacaoBancariaDTO() {
		ConciliacaoBancariaDTO conciliacaoBancariaDTO = new ConciliacaoBancariaDTO();
		conciliacaoBancariaDTO.setBancoId(BANCO_ID);
		conciliacaoBancariaDTO.setAgenciaId(AGENDIA_ID);
		conciliacaoBancariaDTO.setContaId(CONTA_BANCARIA_ID);
		return conciliacaoBancariaDTO;
	}
	
	private ContaPagarEntity pagarConta(ContaPagarEntity conta, LocalDate dataPagamento) {
		conta.setDataPagamento(dataPagamento);
		conta.setValorPago(conta.getValor());
		
		conta = em.persistAndFlush(conta);
		return conta;
	}
	
	
	// BEGIN TESTS DEPENDENCIES
	
	private List<ContaPagarEntity> criarContas() {
		int quantidade = 10;
		LocalDate dataInicial = LocalDate.of(2019, 7, 4);
		List<ContaPagarEntity> contas = criarContaPagar(quantidade, dataInicial, VALOR);
		assertThat(contas).hasSize(quantidade);
		assertThat(contas.get(0).getDataVencimento()).isEqualTo(LocalDate.of(2019, 7, 4));
		assertThat(contas.get(contas.size() - 1).getDataVencimento()).isEqualTo(LocalDate.of(2020, 4, 4));
		
		return contas;
	}
	
	protected List<ContaPagarEntity> criarContaPagar(int quantidade, LocalDate dataInicial, BigDecimal valor) {
		LocalDate dataVencimento = dataInicial;
		List<ContaPagarEntity> contas = new ArrayList<>(quantidade);
		for (int i = 1; i <= quantidade; i++) {
			ContaPagarEntity contaPagarEntity = new ContaPagarEntity();
			
			contaPagarEntity.setPlanoContas(newPlanoContaEntity());
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String dataVencStr = dataVencimento.format(formatter);
			
			contaPagarEntity.setDescricao("Conta número: " + i + ", vencimento: " + dataVencStr);
			contaPagarEntity.setDataVencimento(dataVencimento);
			
			contaPagarEntity.setValor(valor);
			contaPagarEntity.setFormaPagamento(FormaPagamento.CONTA_BANCARIA);
			
			contaPagarEntity.setContaBancaria(newContaBancariaConciliacao());
			
			//contaPagarEntity.setCartaoCredito(null);
			//contaPagarEntity.setOutrosDescricao(null);
			contaPagarEntity.setDataPagamento(null);
			contaPagarEntity.setValorPago(null);
			
			//contaPagarEntity.setFornecedor(newFornecedorEntity());
			//contaPagarEntity.setNumDocumento(generateRandomString(255));
			contaPagarEntity.setIdConcBancaria(null);
			contaPagarEntity.setNumDocConcBancaria(null);
			contaPagarEntity.setHistConcBancaria(null);
			contaPagarEntity.setObservacoes(null);
			contaPagarEntity.setAgrupador("teste");
			
			contaPagarEntity = em.persistAndFlush(contaPagarEntity);
			contas.add(contaPagarEntity);
			dataVencimento = dataVencimento.plusMonths(1);
		}
		
		return contas;
	}
	
	protected ContaPagarEntity newContaPagar1() {
		ContaPagarEntity contaPagarEntity = new ContaPagarEntity();
		
		contaPagarEntity.setPlanoContas(newPlanoContaEntity());
		contaPagarEntity.setDescricao("Conta Pagar 1");
		contaPagarEntity.setDataVencimento(LocalDate.now());
		contaPagarEntity.setValor(new java.math.BigDecimal("100.55"));
		contaPagarEntity.setFormaPagamento(FormaPagamento.CONTA_BANCARIA);
		
		contaPagarEntity.setContaBancaria(newContaBancariaConciliacao());
		
		contaPagarEntity.setCartaoCredito(null);
		contaPagarEntity.setOutrosDescricao(generateRandomString(255));
		contaPagarEntity.setDataPagamento(null);
		
		contaPagarEntity.setFornecedor(newFornecedorEntity());
		contaPagarEntity.setNumDocumento(generateRandomString(255));
		contaPagarEntity.setNumDocConcBancaria(null);
		contaPagarEntity.setHistConcBancaria(null);
		contaPagarEntity.setObservacoes(null);
		contaPagarEntity.setAgrupador("teste");
		
		contaPagarEntity = em.persistAndFlush(contaPagarEntity);
		
		return contaPagarEntity;
	}
	
	protected ContaPagarEntity newContaPagar2() {
		ContaPagarEntity contaPagarEntity = new ContaPagarEntity();
		
		contaPagarEntity.setPlanoContas(newPlanoContaEntity());
		contaPagarEntity.setDescricao("Conta Pagar 2");
		contaPagarEntity.setDataVencimento(LocalDate.now());
		contaPagarEntity.setValor(new java.math.BigDecimal("200.65"));
		contaPagarEntity.setFormaPagamento(FormaPagamento.CONTA_BANCARIA);
		
		contaPagarEntity.setContaBancaria(newContaBancariaConciliacao());
		
		contaPagarEntity.setCartaoCredito(null);
		contaPagarEntity.setOutrosDescricao(generateRandomString(255));
		contaPagarEntity.setDataPagamento(null);
		
		contaPagarEntity.setFornecedor(newFornecedorEntity());
		contaPagarEntity.setNumDocumento(generateRandomString(255));
		contaPagarEntity.setNumDocConcBancaria(null);
		contaPagarEntity.setHistConcBancaria(null);
		contaPagarEntity.setObservacoes(null);
		contaPagarEntity.setAgrupador("teste");
		
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
	
	ContaBancariaEntity contaBancariaEntity;
	protected ContaBancariaEntity newContaBancariaConciliacao() {
		if (contaBancariaEntity == null) {
			contaBancariaEntity = new ContaBancariaEntity();
			
			contaBancariaEntity.setId(java.util.UUID.randomUUID());
			contaBancariaEntity.setNomeTitular("Kerubin");
			contaBancariaEntity.setAgencia(newAgenciaBancariaConciliacao());
			contaBancariaEntity.setTipoContaBancaria(TipoContaBancaria.CONTA_CORRENTE);
			contaBancariaEntity.setNumeroConta(CONTA_BANCARIA_ID);
			contaBancariaEntity.setDigito("01");
			contaBancariaEntity.setDataValidade(getNextDate());
			contaBancariaEntity.setAtivo(true);
			contaBancariaEntity.setDeleted(false);
			
			contaBancariaEntity = em.persistAndFlush(contaBancariaEntity);
		}
		
		return contaBancariaEntity;
	}
	
	protected ContaBancariaLookupResult newContaBancariaLookupResult(ContaBancariaEntity contaBancariaEntity) {
		ContaBancariaLookupResult contaBancaria = new ContaBancariaLookupResult();
		
		contaBancaria.setId(contaBancariaEntity.getId());
		contaBancaria.setNomeTitular(contaBancariaEntity.getNomeTitular());
		contaBancaria.setNumeroConta(contaBancariaEntity.getNumeroConta());
		
		return contaBancaria;
	}
	
	AgenciaBancariaEntity agenciaBancariaEntity;
	protected AgenciaBancariaEntity newAgenciaBancariaConciliacao() {
		if (agenciaBancariaEntity == null) {
			agenciaBancariaEntity = new AgenciaBancariaEntity();
			
			agenciaBancariaEntity.setId(java.util.UUID.randomUUID());
			agenciaBancariaEntity.setBanco(newBancoBradesco());
			agenciaBancariaEntity.setNumeroAgencia(AGENDIA_ID);
			agenciaBancariaEntity.setDigitoAgencia("12");
			agenciaBancariaEntity.setEndereco("Blumenau");
			agenciaBancariaEntity.setDeleted(false);
			
			agenciaBancariaEntity = em.persistAndFlush(agenciaBancariaEntity);
			
		}
		
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
	
	BancoEntity bancoBradesco;
	protected BancoEntity newBancoBradesco() {
		if (bancoBradesco == null) {
			bancoBradesco = new BancoEntity();
			bancoBradesco.setId(java.util.UUID.randomUUID());
			bancoBradesco.setNumero(BANCO_ID);
			bancoBradesco.setNome("Banco Bradesco");
			bancoBradesco.setDeleted(false);
			
			bancoBradesco = em.persistAndFlush(bancoBradesco);
		}
		
		return bancoBradesco;
	}
	
	protected BancoLookupResult newBancoLookupResult(BancoEntity bancoEntity) {
		BancoLookupResult banco = new BancoLookupResult();
		
		banco.setId(bancoEntity.getId());
		banco.setNumero(bancoEntity.getNumero());
		banco.setNome(bancoEntity.getNome());
		
		return banco;
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
