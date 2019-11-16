/**********************************************************************************************
Code generated with MKL Plug-in version: 27.0.12
Code generated at time stamp: 2019-11-06T06:23:00.711
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.conciliacaobancaria;

import static br.com.kerubin.api.servicecore.util.CoreUtils.format;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
import br.com.kerubin.api.financeiro.contaspagar.entity.fornecedor.FornecedorEntity;
import br.com.kerubin.api.financeiro.contaspagar.entity.fornecedor.FornecedorLookupResult;
import br.com.kerubin.api.financeiro.contaspagar.entity.fornecedor.FornecedorRepository;
import br.com.kerubin.api.financeiro.contaspagar.entity.planoconta.PlanoContaEntity;
import br.com.kerubin.api.financeiro.contaspagar.entity.planoconta.PlanoContaLookupResult;
import br.com.kerubin.api.financeiro.contaspagar.entity.planoconta.PlanoContaRepository;
import br.com.kerubin.api.messaging.core.DomainEntityEventsPublisher;


@RunWith(SpringRunner.class)
public class AplicarConciliacaoBancariaServiceTest extends FinanceiroContasPagarBaseEntityTest {
	
	private static final String BANCO_ID = "237";
	private static final String AGENDIA_ID = "12345";
	private static final String CONTA_BANCARIA_ID = "98765";
	
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
	public void testAplicarConciliacaoBancariaComSucesso() {
		
		ContaPagarEntity contaPagar1 = newContaPagar1();
		ContaPagarEntity contaPagar2 = newContaPagar2();
		
		ConciliacaoBancariaDTO conciliacaoBancariaDTO = new ConciliacaoBancariaDTO();
		conciliacaoBancariaDTO.setBancoId(BANCO_ID);
		conciliacaoBancariaDTO.setAgenciaId(AGENDIA_ID);
		conciliacaoBancariaDTO.setContaId(CONTA_BANCARIA_ID);
		
		List<ConciliacaoTransacaoDTO> transacoes = new ArrayList<>();
		ConciliacaoTransacaoDTO t1 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(BigDecimal.valueOf(100.55))
				.trnTipo(TipoTransacao.DEBITO)
				.trnData(LocalDate.now().minusDays(1))
				.tituloConciliadoId(contaPagar1.getId())
				.trnDocumento("00123")
				.trnHistorico("Teste de conciliação 123")
				.build();
		
		transacoes.add(t1);
		
		ConciliacaoTransacaoDTO t2 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(BigDecimal.valueOf(200.65))
				.trnTipo(TipoTransacao.DEBITO)
				.trnData(LocalDate.now().minusDays(2))
				.tituloConciliadoId(contaPagar2.getId())
				.trnDocumento("00987")
				.trnHistorico("Teste de conciliação 987")
				.build();
		
		transacoes.add(t2);
		
		
		conciliacaoBancariaDTO.setTransacoes(transacoes);
		
		conciliacaoBancariaDTO = conciliacaoBancariaService.aplicarConciliacaoBancaria(conciliacaoBancariaDTO);
		
		transacoes = conciliacaoBancariaDTO.getTransacoes();
		
		assertThat(transacoes).hasSize(2)
		.extracting(ConciliacaoTransacaoDTO::getTituloConciliadoId,
				ConciliacaoTransacaoDTO::getTituloConciliadoDesc, 
				ConciliacaoTransacaoDTO::getConciliadoComErro,
				ConciliacaoTransacaoDTO::getConciliadoMsg,
				ConciliacaoTransacaoDTO::getDataConciliacao,
				ConciliacaoTransacaoDTO::getSituacaoConciliacaoTrn
				)
		.contains(
				tuple(contaPagar1.getId(), contaPagar1.getDescricao(), /*getConciliadoComErro=*/false, /*getConciliadoMsg=*/"Sucesso", LocalDate.now(), SituacaoConciliacaoTrn.CONCILIADO_CONTAS_PAGAR),
				tuple(contaPagar2.getId(), contaPagar2.getDescricao(), /*getConciliadoComErro=*/false, /*getConciliadoMsg=*/"Sucesso", LocalDate.now(), SituacaoConciliacaoTrn.CONCILIADO_CONTAS_PAGAR)
				);
		
		
		List<ContaPagarEntity> contas = Arrays.asList(contaPagar1, contaPagar2);
		assertThat(contas).hasSize(2)
		.extracting(ContaPagarEntity::getDataPagamento,
				ContaPagarEntity::getValorPago,
				ContaPagarEntity::getFormaPagamento,
				ContaPagarEntity::getContaBancaria,
				ContaPagarEntity::getIdConcBancaria,
				ContaPagarEntity::getNumDocConcBancaria,
				ContaPagarEntity::getHistConcBancaria
				)
		.contains(
				tuple(t1.getTrnData(), t1.getTrnValor(), FormaPagamento.CONTA_BANCARIA, newContaBancariaConciliacao(), t1.getTrnId(), t1.getTrnDocumento(), t1.getTrnHistorico()),
				tuple(t2.getTrnData(), t2.getTrnValor(), FormaPagamento.CONTA_BANCARIA, newContaBancariaConciliacao(), t2.getTrnId(), t2.getTrnDocumento(), t2.getTrnHistorico())
				);
		
	}
	
	@Test
	public void testAplicarConciliacaoBancariaComErroSemTituloConciliadoId() {
		
		ContaPagarEntity contaPagar1 = newContaPagar1();
		ContaPagarEntity contaPagar2 = newContaPagar2();
		
		ConciliacaoBancariaDTO conciliacaoBancariaDTO = new ConciliacaoBancariaDTO();
		conciliacaoBancariaDTO.setBancoId(BANCO_ID);
		conciliacaoBancariaDTO.setAgenciaId(AGENDIA_ID);
		conciliacaoBancariaDTO.setContaId(CONTA_BANCARIA_ID);
		
		UUID unknownId = UUID.randomUUID();
		List<ConciliacaoTransacaoDTO> transacoes = new ArrayList<>();
		ConciliacaoTransacaoDTO t1 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(BigDecimal.valueOf(100.55))
				.trnTipo(TipoTransacao.DEBITO)
				.trnData(LocalDate.now().minusDays(1))
				.tituloConciliadoId(unknownId)
				.situacaoConciliacaoTrn(SituacaoConciliacaoTrn.CONCILIAR_CONTAS_PAGAR)
				.trnId("00123")
				.trnDocumento("00123")
				.trnHistorico("Teste de conciliação 123")
				.build();
		
		transacoes.add(t1);
		
		ConciliacaoTransacaoDTO t2 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(BigDecimal.valueOf(200.65))
				.trnTipo(TipoTransacao.DEBITO)
				.trnData(LocalDate.now().minusDays(2))
				.tituloConciliadoId(contaPagar2.getId())
				.situacaoConciliacaoTrn(SituacaoConciliacaoTrn.CONCILIAR_CONTAS_PAGAR)
				.trnId("00987")
				.trnDocumento("00987")
				.trnHistorico("Teste de conciliação 987")
				.build();
		
		transacoes.add(t2);
		
		
		conciliacaoBancariaDTO.setTransacoes(transacoes);
		
		conciliacaoBancariaDTO = conciliacaoBancariaService.aplicarConciliacaoBancaria(conciliacaoBancariaDTO);
		
		transacoes = conciliacaoBancariaDTO.getTransacoes();
		
		String erroMsg = "Título não localizado com o id: " + unknownId.toString();
		assertThat(transacoes).hasSize(2)
		.extracting(ConciliacaoTransacaoDTO::getTituloConciliadoId,
				ConciliacaoTransacaoDTO::getTituloConciliadoDesc, 
				ConciliacaoTransacaoDTO::getConciliadoComErro,
				ConciliacaoTransacaoDTO::getConciliadoMsg,
				ConciliacaoTransacaoDTO::getDataConciliacao,
				ConciliacaoTransacaoDTO::getSituacaoConciliacaoTrn
				)
		.contains(
				tuple(unknownId,           null,                       /*getConciliadoComErro=*/true,  /*getConciliadoMsg=*/erroMsg,   null,            SituacaoConciliacaoTrn.CONCILIAR_CONTAS_PAGAR),
				tuple(contaPagar2.getId(), contaPagar2.getDescricao(), /*getConciliadoComErro=*/false, /*getConciliadoMsg=*/"Sucesso", LocalDate.now(), SituacaoConciliacaoTrn.CONCILIADO_CONTAS_PAGAR)
				);
		
		
		List<ContaPagarEntity> contas = Arrays.asList(contaPagar1, contaPagar2);
		assertThat(contas).hasSize(2)
		.extracting(ContaPagarEntity::getDataPagamento,
				ContaPagarEntity::getValorPago,
				ContaPagarEntity::getFormaPagamento,
				ContaPagarEntity::getContaBancaria,
				ContaPagarEntity::getIdConcBancaria,
				ContaPagarEntity::getNumDocConcBancaria,
				ContaPagarEntity::getHistConcBancaria
				)
		.contains(
				tuple(null,            null,             FormaPagamento.CONTA_BANCARIA, newContaBancariaConciliacao(), null,          null,                 null),
				tuple(t2.getTrnData(), t2.getTrnValor(), FormaPagamento.CONTA_BANCARIA, newContaBancariaConciliacao(), t2.getTrnId(), t2.getTrnDocumento(), t2.getTrnHistorico())
				);
		
	}
	
	@Test
	public void testAplicarConciliacaoBancariaComErroSemValor() {
		ContaPagarEntity contaPagar1 = newContaPagar1();
		ContaPagarEntity contaPagar2 = newContaPagar2();
		
		ConciliacaoBancariaDTO conciliacaoBancariaDTO = new ConciliacaoBancariaDTO();
		conciliacaoBancariaDTO.setBancoId(BANCO_ID);
		conciliacaoBancariaDTO.setAgenciaId(AGENDIA_ID);
		conciliacaoBancariaDTO.setContaId(CONTA_BANCARIA_ID);
		
		String erroMsg = "Valor da transação está vazio para baixar conta via conciliação";
		List<ConciliacaoTransacaoDTO> transacoes = new ArrayList<>();
		ConciliacaoTransacaoDTO t1 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(null)
				.trnTipo(TipoTransacao.DEBITO)
				.trnData(LocalDate.now().minusDays(1))
				.tituloConciliadoId(contaPagar1.getId())
				.situacaoConciliacaoTrn(SituacaoConciliacaoTrn.CONCILIAR_CONTAS_PAGAR)
				.trnId("00123")
				.trnDocumento("00123")
				.trnHistorico("Teste de conciliação 123")
				.build();
		
		transacoes.add(t1);
		
		ConciliacaoTransacaoDTO t2 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(BigDecimal.valueOf(200.65))
				.trnTipo(TipoTransacao.DEBITO)
				.trnData(LocalDate.now().minusDays(2))
				.tituloConciliadoId(contaPagar2.getId())
				.situacaoConciliacaoTrn(SituacaoConciliacaoTrn.CONCILIAR_CONTAS_PAGAR)
				.trnId("00987")
				.trnDocumento("00987")
				.trnHistorico("Teste de conciliação 987")
				.build();
		
		transacoes.add(t2);
		
		
		conciliacaoBancariaDTO.setTransacoes(transacoes);
		
		conciliacaoBancariaDTO = conciliacaoBancariaService.aplicarConciliacaoBancaria(conciliacaoBancariaDTO);
		
		transacoes = conciliacaoBancariaDTO.getTransacoes();
		
		assertThat(transacoes).hasSize(2)
		.extracting(ConciliacaoTransacaoDTO::getTituloConciliadoId,
				ConciliacaoTransacaoDTO::getTituloConciliadoDesc, 
				ConciliacaoTransacaoDTO::getConciliadoComErro,
				ConciliacaoTransacaoDTO::getConciliadoMsg,
				ConciliacaoTransacaoDTO::getDataConciliacao,
				ConciliacaoTransacaoDTO::getSituacaoConciliacaoTrn
				)
		.contains(
				tuple(contaPagar1.getId(), null,                       /*getConciliadoComErro=*/true,  /*getConciliadoMsg=*/erroMsg,   null,            SituacaoConciliacaoTrn.CONCILIAR_CONTAS_PAGAR),
				tuple(contaPagar2.getId(), contaPagar2.getDescricao(), /*getConciliadoComErro=*/false, /*getConciliadoMsg=*/"Sucesso", LocalDate.now(), SituacaoConciliacaoTrn.CONCILIADO_CONTAS_PAGAR)
				);
		
		
		List<ContaPagarEntity> contas = Arrays.asList(contaPagar1, contaPagar2);
		assertThat(contas).hasSize(2)
		.extracting(ContaPagarEntity::getDataPagamento,
				ContaPagarEntity::getValorPago,
				ContaPagarEntity::getFormaPagamento,
				ContaPagarEntity::getContaBancaria,
				ContaPagarEntity::getIdConcBancaria,
				ContaPagarEntity::getNumDocConcBancaria,
				ContaPagarEntity::getHistConcBancaria
				)
		.contains(
				tuple(null,            null,             FormaPagamento.CONTA_BANCARIA, newContaBancariaConciliacao(), null,          null,                 null),
				tuple(t2.getTrnData(), t2.getTrnValor(), FormaPagamento.CONTA_BANCARIA, newContaBancariaConciliacao(), t2.getTrnId(), t2.getTrnDocumento(), t2.getTrnHistorico())
				);
	}
	
	@Test
	public void testAplicarConciliacaoBancariaComErroSemData() {
		ContaPagarEntity contaPagar1 = newContaPagar1();
		ContaPagarEntity contaPagar2 = newContaPagar2();
		
		ConciliacaoBancariaDTO conciliacaoBancariaDTO = new ConciliacaoBancariaDTO();
		conciliacaoBancariaDTO.setBancoId(BANCO_ID);
		conciliacaoBancariaDTO.setAgenciaId(AGENDIA_ID);
		conciliacaoBancariaDTO.setContaId(CONTA_BANCARIA_ID);
		
		String erroMsg = "Data da transação está vazia para baixar conta via conciliação";
		List<ConciliacaoTransacaoDTO> transacoes = new ArrayList<>();
		ConciliacaoTransacaoDTO t1 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(contaPagar1.getValor())
				.trnTipo(TipoTransacao.DEBITO)
				.trnData(null)
				.tituloConciliadoId(contaPagar1.getId())
				.situacaoConciliacaoTrn(SituacaoConciliacaoTrn.CONCILIAR_CONTAS_PAGAR)
				.trnId("00123")
				.trnDocumento("00123")
				.trnHistorico("Teste de conciliação 123")
				.build();
		
		transacoes.add(t1);
		
		ConciliacaoTransacaoDTO t2 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(BigDecimal.valueOf(200.65))
				.trnTipo(TipoTransacao.DEBITO)
				.trnData(LocalDate.now().minusDays(2))
				.tituloConciliadoId(contaPagar2.getId())
				.situacaoConciliacaoTrn(SituacaoConciliacaoTrn.CONCILIAR_CONTAS_PAGAR)
				.trnId("00987")
				.trnDocumento("00987")
				.trnHistorico("Teste de conciliação 987")
				.build();
		
		transacoes.add(t2);
		
		
		conciliacaoBancariaDTO.setTransacoes(transacoes);
		
		conciliacaoBancariaDTO = conciliacaoBancariaService.aplicarConciliacaoBancaria(conciliacaoBancariaDTO);
		
		transacoes = conciliacaoBancariaDTO.getTransacoes();
		
		assertThat(transacoes).hasSize(2)
		.extracting(ConciliacaoTransacaoDTO::getTituloConciliadoId,
				ConciliacaoTransacaoDTO::getTituloConciliadoDesc, 
				ConciliacaoTransacaoDTO::getConciliadoComErro,
				ConciliacaoTransacaoDTO::getConciliadoMsg,
				ConciliacaoTransacaoDTO::getDataConciliacao,
				ConciliacaoTransacaoDTO::getSituacaoConciliacaoTrn
				)
		.contains(
				tuple(contaPagar1.getId(), null,                       /*getConciliadoComErro=*/true,  /*getConciliadoMsg=*/erroMsg,   null,            SituacaoConciliacaoTrn.CONCILIAR_CONTAS_PAGAR),
				tuple(contaPagar2.getId(), contaPagar2.getDescricao(), /*getConciliadoComErro=*/false, /*getConciliadoMsg=*/"Sucesso", LocalDate.now(), SituacaoConciliacaoTrn.CONCILIADO_CONTAS_PAGAR)
				);
		
		
		List<ContaPagarEntity> contas = Arrays.asList(contaPagar1, contaPagar2);
		assertThat(contas).hasSize(2)
		.extracting(ContaPagarEntity::getDataPagamento,
				ContaPagarEntity::getValorPago,
				ContaPagarEntity::getFormaPagamento,
				ContaPagarEntity::getContaBancaria,
				ContaPagarEntity::getIdConcBancaria,
				ContaPagarEntity::getNumDocConcBancaria,
				ContaPagarEntity::getHistConcBancaria
				)
		.contains(
				tuple(null,            null,             FormaPagamento.CONTA_BANCARIA, newContaBancariaConciliacao(), null,          null,                 null),
				tuple(t2.getTrnData(), t2.getTrnValor(), FormaPagamento.CONTA_BANCARIA, newContaBancariaConciliacao(), t2.getTrnId(), t2.getTrnDocumento(), t2.getTrnHistorico())
				);
	}
	
	@Test
	public void testAplicarConciliacaoBancariaComErroTipoNaoDebito() {
		ContaPagarEntity contaPagar1 = newContaPagar1();
		ContaPagarEntity contaPagar2 = newContaPagar2();
		
		ConciliacaoBancariaDTO conciliacaoBancariaDTO = new ConciliacaoBancariaDTO();
		conciliacaoBancariaDTO.setBancoId(BANCO_ID);
		conciliacaoBancariaDTO.setAgenciaId(AGENDIA_ID);
		conciliacaoBancariaDTO.setContaId(CONTA_BANCARIA_ID);
		
		String erroMsg = "Tipo da transação deveria ser CRÉDITO mas é: " + TipoTransacao.CREDITO;
		List<ConciliacaoTransacaoDTO> transacoes = new ArrayList<>();
		ConciliacaoTransacaoDTO t1 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(contaPagar1.getValor())
				.trnTipo(TipoTransacao.CREDITO)
				.trnData(LocalDate.now())
				.tituloConciliadoId(contaPagar1.getId())
				.situacaoConciliacaoTrn(SituacaoConciliacaoTrn.CONCILIAR_CONTAS_PAGAR)
				.trnId("00123")
				.trnDocumento("00123")
				.trnHistorico("Teste de conciliação 123")
				.build();
		
		transacoes.add(t1);
		
		ConciliacaoTransacaoDTO t2 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(BigDecimal.valueOf(200.65))
				.trnTipo(TipoTransacao.DEBITO)
				.trnData(LocalDate.now().minusDays(2))
				.tituloConciliadoId(contaPagar2.getId())
				.situacaoConciliacaoTrn(SituacaoConciliacaoTrn.CONCILIAR_CONTAS_PAGAR)
				.trnId("00987")
				.trnDocumento("00987")
				.trnHistorico("Teste de conciliação 987")
				.build();
		
		transacoes.add(t2);
		
		
		conciliacaoBancariaDTO.setTransacoes(transacoes);
		
		conciliacaoBancariaDTO = conciliacaoBancariaService.aplicarConciliacaoBancaria(conciliacaoBancariaDTO);
		
		transacoes = conciliacaoBancariaDTO.getTransacoes();
		
		assertThat(transacoes).hasSize(2)
		.extracting(ConciliacaoTransacaoDTO::getTituloConciliadoId,
				ConciliacaoTransacaoDTO::getTituloConciliadoDesc, 
				ConciliacaoTransacaoDTO::getConciliadoComErro,
				ConciliacaoTransacaoDTO::getConciliadoMsg,
				ConciliacaoTransacaoDTO::getDataConciliacao,
				ConciliacaoTransacaoDTO::getSituacaoConciliacaoTrn
				)
		.contains(
				tuple(contaPagar1.getId(), null,                       /*getConciliadoComErro=*/true,  /*getConciliadoMsg=*/erroMsg,   null,            SituacaoConciliacaoTrn.CONCILIAR_CONTAS_PAGAR),
				tuple(contaPagar2.getId(), contaPagar2.getDescricao(), /*getConciliadoComErro=*/false, /*getConciliadoMsg=*/"Sucesso", LocalDate.now(), SituacaoConciliacaoTrn.CONCILIADO_CONTAS_PAGAR)
				);
		
		
		List<ContaPagarEntity> contas = Arrays.asList(contaPagar1, contaPagar2);
		assertThat(contas).hasSize(2)
		.extracting(ContaPagarEntity::getDataPagamento,
				ContaPagarEntity::getValorPago,
				ContaPagarEntity::getFormaPagamento,
				ContaPagarEntity::getContaBancaria,
				ContaPagarEntity::getIdConcBancaria,
				ContaPagarEntity::getNumDocConcBancaria,
				ContaPagarEntity::getHistConcBancaria
				)
		.contains(
				tuple(null,            null,             FormaPagamento.CONTA_BANCARIA, newContaBancariaConciliacao(), null,          null,                 null),
				tuple(t2.getTrnData(), t2.getTrnValor(), FormaPagamento.CONTA_BANCARIA, newContaBancariaConciliacao(), t2.getTrnId(), t2.getTrnDocumento(), t2.getTrnHistorico())
				);
	}
	
	@Test
	public void testAplicarConciliacaoBancariaComErroContaJaBaixada() {
		ContaPagarEntity contaPagar1 = newContaPagar1();
		contaPagar1.setDataPagamento(LocalDate.now());
		contaPagar1.setNumDocConcBancaria("012345");
		contaPagar1.setIdConcBancaria("012345");
		
		ContaPagarEntity contaPagar2 = newContaPagar2();
		
		ConciliacaoBancariaDTO conciliacaoBancariaDTO = new ConciliacaoBancariaDTO();
		conciliacaoBancariaDTO.setBancoId(BANCO_ID);
		conciliacaoBancariaDTO.setAgenciaId(AGENDIA_ID);
		conciliacaoBancariaDTO.setContaId(CONTA_BANCARIA_ID);
		
		String erroMsg = format("Conta já baixada em: {0}, doc: {1}", contaPagar1.getDataPagamento(), contaPagar1.getNumDocConcBancaria());
		List<ConciliacaoTransacaoDTO> transacoes = new ArrayList<>();
		ConciliacaoTransacaoDTO t1 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(contaPagar1.getValor())
				.trnTipo(TipoTransacao.DEBITO)
				.trnData(LocalDate.now())
				.tituloConciliadoId(contaPagar1.getId())
				.situacaoConciliacaoTrn(SituacaoConciliacaoTrn.CONCILIAR_CONTAS_PAGAR)
				.trnId("00123")
				.trnDocumento("00123")
				.trnHistorico("Teste de conciliação 123")
				.build();
		
		transacoes.add(t1);
		
		ConciliacaoTransacaoDTO t2 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(BigDecimal.valueOf(200.65))
				.trnTipo(TipoTransacao.DEBITO)
				.trnData(LocalDate.now().minusDays(2))
				.tituloConciliadoId(contaPagar2.getId())
				.situacaoConciliacaoTrn(SituacaoConciliacaoTrn.CONCILIAR_CONTAS_PAGAR)
				.trnId("00987")
				.trnDocumento("00987")
				.trnHistorico("Teste de conciliação 987")
				.build();
		
		transacoes.add(t2);
		
		
		conciliacaoBancariaDTO.setTransacoes(transacoes);
		
		conciliacaoBancariaDTO = conciliacaoBancariaService.aplicarConciliacaoBancaria(conciliacaoBancariaDTO);
		
		transacoes = conciliacaoBancariaDTO.getTransacoes();
		
		assertThat(transacoes).hasSize(2)
		.extracting(ConciliacaoTransacaoDTO::getTituloConciliadoId,
				ConciliacaoTransacaoDTO::getTituloConciliadoDesc, 
				ConciliacaoTransacaoDTO::getConciliadoComErro,
				ConciliacaoTransacaoDTO::getConciliadoMsg,
				ConciliacaoTransacaoDTO::getDataConciliacao,
				ConciliacaoTransacaoDTO::getSituacaoConciliacaoTrn
				)
		.contains(
				tuple(contaPagar1.getId(), null,                       /*getConciliadoComErro=*/true,  /*getConciliadoMsg=*/erroMsg,   null,            SituacaoConciliacaoTrn.CONCILIAR_CONTAS_PAGAR),
				tuple(contaPagar2.getId(), contaPagar2.getDescricao(), /*getConciliadoComErro=*/false, /*getConciliadoMsg=*/"Sucesso", LocalDate.now(), SituacaoConciliacaoTrn.CONCILIADO_CONTAS_PAGAR)
				);
		
		
		List<ContaPagarEntity> contas = Arrays.asList(contaPagar1, contaPagar2);
		assertThat(contas).hasSize(2)
		.extracting(ContaPagarEntity::getDataPagamento,
				ContaPagarEntity::getValorPago,
				ContaPagarEntity::getFormaPagamento,
				ContaPagarEntity::getContaBancaria,
				ContaPagarEntity::getIdConcBancaria,
				ContaPagarEntity::getNumDocConcBancaria,
				ContaPagarEntity::getHistConcBancaria
				)
		.contains(
				tuple(LocalDate.now(), null,             FormaPagamento.CONTA_BANCARIA, newContaBancariaConciliacao(), "012345",      "012345",             null),
				tuple(t2.getTrnData(), t2.getTrnValor(), FormaPagamento.CONTA_BANCARIA, newContaBancariaConciliacao(), t2.getTrnId(), t2.getTrnDocumento(), t2.getTrnHistorico())
				);
	}
	
	@Test
	public void testAplicarConciliacaoBancariaComErroContaValorDivergente() {
		ContaPagarEntity contaPagar1 = newContaPagar1();
		contaPagar1.setValor(BigDecimal.valueOf(181.18));
		
		ContaPagarEntity contaPagar2 = newContaPagar2();
		
		ConciliacaoBancariaDTO conciliacaoBancariaDTO = new ConciliacaoBancariaDTO();
		conciliacaoBancariaDTO.setBancoId(BANCO_ID);
		conciliacaoBancariaDTO.setAgenciaId(AGENDIA_ID);
		conciliacaoBancariaDTO.setContaId(CONTA_BANCARIA_ID);
		
		List<ConciliacaoTransacaoDTO> transacoes = new ArrayList<>();
		ConciliacaoTransacaoDTO t1 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(BigDecimal.valueOf(181.00))
				.trnTipo(TipoTransacao.DEBITO)
				.trnData(LocalDate.now())
				.tituloConciliadoId(contaPagar1.getId())
				.situacaoConciliacaoTrn(SituacaoConciliacaoTrn.CONCILIAR_CONTAS_PAGAR)
				.trnId("00123")
				.trnDocumento("00123")
				.trnHistorico("Teste de conciliação 123")
				.build();
		
		String erroMsg = format("Valor da conta: {0}, diverge do valor da transação de conciliação: {1}", contaPagar1.getValor(), t1.getTrnValor());
		transacoes.add(t1);
		
		ConciliacaoTransacaoDTO t2 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(BigDecimal.valueOf(200.65))
				.trnTipo(TipoTransacao.DEBITO)
				.trnData(LocalDate.now().minusDays(2))
				.tituloConciliadoId(contaPagar2.getId())
				.situacaoConciliacaoTrn(SituacaoConciliacaoTrn.CONCILIAR_CONTAS_PAGAR)
				.trnId("00987")
				.trnDocumento("00987")
				.trnHistorico("Teste de conciliação 987")
				.build();
		
		transacoes.add(t2);
		
		
		conciliacaoBancariaDTO.setTransacoes(transacoes);
		
		conciliacaoBancariaDTO = conciliacaoBancariaService.aplicarConciliacaoBancaria(conciliacaoBancariaDTO);
		
		transacoes = conciliacaoBancariaDTO.getTransacoes();
		
		assertThat(transacoes).hasSize(2)
		.extracting(ConciliacaoTransacaoDTO::getTituloConciliadoId,
				ConciliacaoTransacaoDTO::getTituloConciliadoDesc, 
				ConciliacaoTransacaoDTO::getConciliadoComErro,
				ConciliacaoTransacaoDTO::getConciliadoMsg,
				ConciliacaoTransacaoDTO::getDataConciliacao,
				ConciliacaoTransacaoDTO::getSituacaoConciliacaoTrn
				)
		.contains(
				tuple(contaPagar1.getId(), null,                       /*getConciliadoComErro=*/true,  /*getConciliadoMsg=*/erroMsg,   null,            SituacaoConciliacaoTrn.CONCILIAR_CONTAS_PAGAR),
				tuple(contaPagar2.getId(), contaPagar2.getDescricao(), /*getConciliadoComErro=*/false, /*getConciliadoMsg=*/"Sucesso", LocalDate.now(), SituacaoConciliacaoTrn.CONCILIADO_CONTAS_PAGAR)
				);
		
		
		List<ContaPagarEntity> contas = Arrays.asList(contaPagar1, contaPagar2);
		assertThat(contas).hasSize(2)
		.extracting(ContaPagarEntity::getDataPagamento,
				ContaPagarEntity::getValorPago,
				ContaPagarEntity::getFormaPagamento,
				ContaPagarEntity::getContaBancaria,
				ContaPagarEntity::getIdConcBancaria,
				ContaPagarEntity::getNumDocConcBancaria,
				ContaPagarEntity::getHistConcBancaria
				)
		.contains(
				tuple(null,            null,             FormaPagamento.CONTA_BANCARIA, newContaBancariaConciliacao(), null,          null,                 null),
				tuple(t2.getTrnData(), t2.getTrnValor(), FormaPagamento.CONTA_BANCARIA, newContaBancariaConciliacao(), t2.getTrnId(), t2.getTrnDocumento(), t2.getTrnHistorico())
				);
	}
	
	@Test
	public void testAplicarConciliacaoBancariaComErroBancoNaoEncontrado() {
		
		ContaPagarEntity contaPagar1 = newContaPagar1();
		ContaPagarEntity contaPagar2 = newContaPagar2();
		
		ConciliacaoBancariaDTO conciliacaoBancariaDTO = new ConciliacaoBancariaDTO();
		
		String bancoId = BANCO_ID + "-xxx";
		conciliacaoBancariaDTO.setBancoId(bancoId);
		conciliacaoBancariaDTO.setAgenciaId(AGENDIA_ID);
		conciliacaoBancariaDTO.setContaId(CONTA_BANCARIA_ID);
		
		String erroMsg = MessageFormat.format("Conta bancária não encontrada para bancoId:{0}, agenciaId:{1}, contaBancariaId:{2}", bancoId, AGENDIA_ID, CONTA_BANCARIA_ID);
		
		UUID unknownId = UUID.randomUUID();
		String tituloConciliadoDesc = contaPagar1.getDescricao() + " xxx";
		List<ConciliacaoTransacaoDTO> transacoes = new ArrayList<>();
		ConciliacaoTransacaoDTO t1 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(BigDecimal.valueOf(100.55))
				.trnTipo(TipoTransacao.DEBITO)
				.trnData(LocalDate.now().minusDays(1))
				.tituloConciliadoId(unknownId)
				.tituloConciliadoDesc(tituloConciliadoDesc)
				.situacaoConciliacaoTrn(SituacaoConciliacaoTrn.CONCILIAR_CONTAS_PAGAR)
				.trnId("00123")
				.trnDocumento("00123")
				.trnHistorico("Teste de conciliação 123")
				.build();
		
		transacoes.add(t1);
		
		ConciliacaoTransacaoDTO t2 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(BigDecimal.valueOf(200.65))
				.trnTipo(TipoTransacao.DEBITO)
				.trnData(LocalDate.now().minusDays(2))
				.tituloConciliadoId(contaPagar2.getId())
				.tituloConciliadoDesc(contaPagar2.getDescricao())
				.situacaoConciliacaoTrn(SituacaoConciliacaoTrn.CONCILIAR_CONTAS_PAGAR)
				.trnId("00987")
				.trnDocumento("00987")
				.trnHistorico("Teste de conciliação 987")
				.build();
		
		transacoes.add(t2);
		
		
		conciliacaoBancariaDTO.setTransacoes(transacoes);
		
		conciliacaoBancariaDTO = conciliacaoBancariaService.aplicarConciliacaoBancaria(conciliacaoBancariaDTO);
		
		transacoes = conciliacaoBancariaDTO.getTransacoes();
		
		assertThat(transacoes).hasSize(2)
		.extracting(ConciliacaoTransacaoDTO::getTituloConciliadoId,
				ConciliacaoTransacaoDTO::getTituloConciliadoDesc, 
				ConciliacaoTransacaoDTO::getConciliadoComErro,
				ConciliacaoTransacaoDTO::getConciliadoMsg,
				ConciliacaoTransacaoDTO::getDataConciliacao,
				ConciliacaoTransacaoDTO::getSituacaoConciliacaoTrn
				)
		.contains(
				tuple(unknownId,           tituloConciliadoDesc,       /*getConciliadoComErro=*/true, /*getConciliadoMsg=*/erroMsg, null, SituacaoConciliacaoTrn.CONCILIAR_CONTAS_PAGAR),
				tuple(contaPagar2.getId(), contaPagar2.getDescricao(), /*getConciliadoComErro=*/true, /*getConciliadoMsg=*/erroMsg, null, SituacaoConciliacaoTrn.CONCILIAR_CONTAS_PAGAR)
				);
		
		
		List<ContaPagarEntity> contas = Arrays.asList(contaPagar1, contaPagar2);
		assertThat(contas).hasSize(2)
		.extracting(ContaPagarEntity::getDataPagamento,
				ContaPagarEntity::getValorPago,
				ContaPagarEntity::getFormaPagamento,
				ContaPagarEntity::getContaBancaria,
				ContaPagarEntity::getIdConcBancaria,
				ContaPagarEntity::getNumDocConcBancaria,
				ContaPagarEntity::getHistConcBancaria
				)
		.contains(
				tuple(null, null, FormaPagamento.CONTA_BANCARIA, newContaBancariaConciliacao(), null, null, null),
				tuple(null, null, FormaPagamento.CONTA_BANCARIA, newContaBancariaConciliacao(), null, null, null)
				);
		
	}
	
	// BEGIN TESTS DEPENDENCIES
	
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
