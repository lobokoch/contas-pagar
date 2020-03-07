package br.com.kerubin.api.financeiro.contaspagar.conciliacaobancaria;

import static br.com.kerubin.api.servicecore.util.CoreUtils.daysBetweenAbs;
import static br.com.kerubin.api.servicecore.util.CoreUtils.format;
import static br.com.kerubin.api.servicecore.util.CoreUtils.formatMoney;
import static br.com.kerubin.api.servicecore.util.CoreUtils.getDiff;
import static br.com.kerubin.api.servicecore.util.CoreUtils.getTokens;
import static br.com.kerubin.api.servicecore.util.CoreUtils.isEmpty;
import static br.com.kerubin.api.servicecore.util.CoreUtils.isEquals;
import static br.com.kerubin.api.servicecore.util.CoreUtils.isLt;
import static br.com.kerubin.api.servicecore.util.CoreUtils.isNotEmpty;
import static br.com.kerubin.api.servicecore.util.CoreUtils.isNotEquals;
import static br.com.kerubin.api.servicecore.util.CoreUtils.isZero;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import br.com.kerubin.api.financeiro.contaspagar.FormaPagamento;
import br.com.kerubin.api.financeiro.contaspagar.entity.contabancaria.ContaBancariaEntity;
import br.com.kerubin.api.financeiro.contaspagar.entity.contabancaria.ContaBancariaRepository;
import br.com.kerubin.api.financeiro.contaspagar.entity.contapagar.ContaPagarEntity;
import br.com.kerubin.api.financeiro.contaspagar.entity.contapagar.ContaPagarService;
import br.com.kerubin.api.financeiro.contaspagar.entity.contapagar.QContaPagarEntity;
import br.com.kerubin.api.financeiro.contaspagar.entity.fornecedor.QFornecedorEntity;
import br.com.kerubin.api.financeiro.contaspagar.entity.planoconta.PlanoContaEntity;
import br.com.kerubin.api.financeiro.contaspagar.entity.planoconta.PlanoContaService;
import br.com.kerubin.api.financeiro.contaspagar.repository.ContaPagarRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ConciliacaoBancariaServiceImpl implements ConciliacaoBancariaService {
	
	@PersistenceContext
	private EntityManager em;
	
	@Inject
	private ContaPagarService contaPagarService;
	
	@Inject
	private ContaPagarRepository contaPagarRepository;
	
	@Inject
	private ContaBancariaRepository contaBancariaRepository;
	
	@Inject
	private PlanoContaService planoContaService;
	
	
	@Transactional(readOnly = true)
	@Override
	public ConciliacaoBancariaDTO verificarTransacoes(ConciliacaoBancariaDTO conciliacaoBancariaDTO) {
		
		if (isEmpty(conciliacaoBancariaDTO) || isEmpty(conciliacaoBancariaDTO.getTransacoes())) {
			return conciliacaoBancariaDTO;
		}
		
		JPAQueryFactory query = new JPAQueryFactory(em);
		
		QContaPagarEntity qContaPagar = QContaPagarEntity.contaPagarEntity;
		QFornecedorEntity qFornecedor = QFornecedorEntity.fornecedorEntity;
		
		Map<String, ContaPagarEntity> lastVisitedList = new HashMap<>();
		
		conciliacaoBancariaDTO.getTransacoes().forEach(transacao -> {
			
			List<String> tokens = getTokens(transacao.getTrnHistorico());
			BooleanBuilder filtroDescricaoTokens = new BooleanBuilder();
			BooleanBuilder filtroFornecedorTokens = new BooleanBuilder();
			if (isNotEmpty(tokens)) {
				tokens.forEach(token -> filtroDescricaoTokens.or(qContaPagar.descricao.containsIgnoreCase(token)));
				tokens.forEach(token -> filtroFornecedorTokens.or(qContaPagar.fornecedor.nome.containsIgnoreCase(token)));
			}
			
			BooleanBuilder filtroDados = new BooleanBuilder();
			filtroDados
			.and(qContaPagar.idConcBancaria.eq(transacao.getTrnId()))
			.or(qContaPagar.valor.eq(transacao.getTrnValor()))
			.or(filtroDescricaoTokens) // Faz match com os tokens do histórico do extrato com as descrições das contas.
			.or(filtroFornecedorTokens); // Faz match com os tokens do nome do fornecedor.
			
			
			LocalDate dataToRef = transacao.getTrnData();
			String key = getTrnKey(transacao);
			ContaPagarEntity lastVisited = lastVisitedList.get(key);
			boolean hasLastVisited = isNotEmpty(lastVisited);
			boolean lastVisitedIsAfter = hasLastVisited && lastVisited.getDataVencimento().isAfter(dataToRef); // Se forem iguais, está valendo.
			if (lastVisitedIsAfter) {
				dataToRef = lastVisited.getDataVencimento();
			}
			
			LocalDate from = transacao.getTrnData().minusMonths(1);
			LocalDate to = dataToRef.plusMonths(1);
			
			BooleanBuilder filtroPerido = new BooleanBuilder();
			// A data da transação é igual a data de pagamento da conta
			filtroPerido.and(qContaPagar.dataPagamento.eq(transacao.getTrnData()))
			// A conta não está paga ainda e a data da transação está no intervalo do vencimento.
			.or(qContaPagar.dataPagamento.isNull().and(qContaPagar.dataVencimento.between(from, to)));
			// filtroPerido.and(qContaPagar.dataVencimento.between(from, to)).or(qContaPagar.dataPagamento.between(from, to));
			
			BooleanBuilder where = new BooleanBuilder();
			where.and(filtroDados).and(filtroPerido);
			
			JPAQuery<ContaPagarEntity> q = query
					.selectFrom(qContaPagar)
					.leftJoin(qContaPagar.fornecedor, qFornecedor)
					.where(where)
					.orderBy(qContaPagar.dataVencimento.asc());
			
			List<ContaPagarEntity> contas = q.fetch();
			
			
			if (isNotEmpty(contas)) {
				Map<ContaPagarEntity, Integer> scores = computeScore(contas, tokens, transacao);
				contas = contas.stream().filter(conta -> scores.get(conta).intValue() > 0).collect(Collectors.toList());
				
				Comparator<ContaPagarEntity> comparator = Comparator.comparing(ContaPagarEntity::getDataVencimento);
				ContaPagarEntity contaMaiorDataVencimento = contas.stream().max(comparator).get();
				
				ContaPagarEntity contaCandidata = null;
				boolean temAlgumaContaComConciliacao = contas.stream().anyMatch(it -> isConciliado(it, transacao)); 
				if (temAlgumaContaComConciliacao || !lastVisitedIsAfter) {
					List<ContaPagarEntity> contasClone = new ArrayList<>(contas);
					
					// Tem alguma conta conciliada?
					List<ContaPagarEntity> contasConciliadas = contas.stream()
							.filter(it -> isConciliado(it, transacao))
							.collect(Collectors.toList());
					
					contaCandidata = getContaComMaiorScore(contasConciliadas, scores);
					
					// Tem alguma conta paga?
					if (isEmpty(contaCandidata)) {
						List<ContaPagarEntity> contasPagas = contas.stream()
								.filter(it -> isPago(it, transacao))
								.collect(Collectors.toList());
						
						contaCandidata = getContaComMaiorScore(contasPagas, scores);
					}
					
					// Tem alguma conta em aberto?
					if (isEmpty(contaCandidata)) {
						List<ContaPagarEntity> contasEmAberto = contas.stream()
								.filter(it -> isEmAberto(it, transacao, contasClone))
								.collect(Collectors.toList());
						
						contaCandidata = getContaComMaiorScore(contasEmAberto, scores);
					}
					
					if (isEmpty(contaCandidata)) {
						// Os títulos devem ter valores diferentes da transação, então procura a conta de valor mais próximo.
						contaCandidata = getContaEmAbertoComValorMaisPerto(transacao.getTrnValor(), contasClone);
					}
					
					if (isEmpty(contaCandidata)) {
						contaCandidata = Collections.max(scores.entrySet(), Map.Entry.comparingByValue()).getKey();
					}
				}
				else {
					// Como tem contas em lastVisitedList, a de maior data encontrada é a candidata mais provável. Pagamentos antecipados.
					contaCandidata = contaMaiorDataVencimento;
				}
				
				lastVisitedList.put(key, contaMaiorDataVencimento);
				
				transacao.setTituloConciliadoId(contaCandidata.getId());
				transacao.setTituloConciliadoDesc(contaCandidata.getDescricao());
				
				transacao.setTituloConciliadoValor(contaCandidata.getDataPagamento() == null ? contaCandidata.getValor() : contaCandidata.getValorPago());
				transacao.setTituloConciliadoDataVen(contaCandidata.getDataVencimento());
				transacao.setTituloConciliadoDataPag(contaCandidata.getDataPagamento());
				PlanoContaDTO planoContas = contaCandidata.getPlanoContas() != null ? PlanoContaDTO.builder().id(contaCandidata.getPlanoContas().getId()).build() : null;
				transacao.setTituloPlanoContas(planoContas);
				
				SituacaoConciliacaoTrn situacaoConciliacaoTrn = transacao.getSituacaoConciliacaoTrn(); // Valor atual é o default.
				if (isNotEmpty(contaCandidata.getDataPagamento())) { // Já pagou, baixado.
					if (isNotEmpty(contaCandidata.getIdConcBancaria())) { // Pagamento normal, sem conciliação
						situacaoConciliacaoTrn = SituacaoConciliacaoTrn.CONCILIADO_CONTAS_PAGAR;
					}
					else {
						situacaoConciliacaoTrn = SituacaoConciliacaoTrn.CONTAS_PAGAR_BAIXADO_SEM_CONCILIACAO;
					}
				}
				else {
					situacaoConciliacaoTrn = SituacaoConciliacaoTrn.CONCILIAR_CONTAS_PAGAR;
				}
				transacao.setSituacaoConciliacaoTrn(situacaoConciliacaoTrn);
				
				BigDecimal valor = contaCandidata.getValor();
				if (isNotEquals(transacao.getTrnValor(), valor)) {
					BigDecimal valDiff = getDiff(transacao.getTrnValor(), valor);
					transacao.setConciliadoMsg(MessageFormat.format("Valor da transação e valor da conta são diferentes em: {0}", formatMoney(valDiff)));
				}
				
				/*BigDecimal diff = getDiff(transacao.getTrnValor(), contaCandidata.getValor());
				if (isGte(diff, transacao.getTrnValor().divide(BigDecimal.valueOf(2))) || isGte(diff, contaCandidata.getValor().divide(BigDecimal.valueOf(2)))) {
					transacao.setConciliadoMsg("Valor da conta e o valor da transação possuem diferença maior ou igual a 50%");
				}*/
				
				// Se já foi conciliado, remove as contas que não tem a ver com a conta que conciliou.
				if (contas.size() > 1 && SituacaoConciliacaoTrn.CONCILIADO_CONTAS_PAGAR.equals(situacaoConciliacaoTrn)) {
					contas.removeIf(it -> !it.getId().equals(transacao.getTituloConciliadoId()));
				}
				
				// Se já foi baixada, e tiver contas com a data de pagamento igual a data da transação, deixa apenas essas contas. 
				if (contas.size() > 1 && SituacaoConciliacaoTrn.CONTAS_PAGAR_BAIXADO_SEM_CONCILIACAO.equals(situacaoConciliacaoTrn)) {
					List<ContaPagarEntity> contasCandidatas = contas
							.stream()
							.filter(it -> isNotEmpty(it.getDataPagamento()) && it.getDataPagamento().equals(transacao.getTrnData()))
							.collect(Collectors.toList());
					
					if (!contasCandidatas.isEmpty()) {
						contas.removeIf(it1 -> !contasCandidatas.stream().anyMatch(it2 -> it2.getId().equals(it1.getId())));
					}
					
				}
				
				// Caso tenha mais de um título, empacota eles junto para o usuário decidir qual é o título certo.
				if (!contas.isEmpty()) {
					
					List<ConciliacaoTransacaoTituloDTO> titulos = contas.stream().map(it -> {
						PlanoContaDTO planoContasTitulo = it.getPlanoContas() != null ? PlanoContaDTO.builder().id(it.getPlanoContas().getId()).build() : null;
						ConciliacaoTransacaoTituloDTO titulo = ConciliacaoTransacaoTituloDTO.builder()
								.tituloConciliadoId(it.getId())
								.tituloConciliadoDesc(it.getDescricao())
								.tituloConciliadoValor(isEmpty(it.getDataPagamento()) ? it.getValor() : it.getValorPago())
								.tituloConciliadoDataVen(it.getDataVencimento())
								.tituloConciliadoDataPag(it.getDataPagamento())
								.tituloPlanoContas(planoContasTitulo)
								.build();
						
						// Situação do título
						SituacaoConciliacaoTrn situacaoConciliacaoTrn2 = titulo.getSituacaoConciliacaoTrn(); // Valor atual é o default.
						if (isNotEmpty(it.getDataPagamento())) { // Já pagou, baixado.
							if (isNotEmpty(it.getIdConcBancaria())) { // Pagamento normal, sem conciliação
								situacaoConciliacaoTrn2 = SituacaoConciliacaoTrn.CONCILIADO_CONTAS_PAGAR;
							}
							else {
								situacaoConciliacaoTrn2 = SituacaoConciliacaoTrn.CONTAS_PAGAR_BAIXADO_SEM_CONCILIACAO;
							}
						}
						else {
							situacaoConciliacaoTrn2 = SituacaoConciliacaoTrn.CONCILIAR_CONTAS_PAGAR;
						}
						titulo.setSituacaoConciliacaoTrn(situacaoConciliacaoTrn2);
						
						return titulo;
						
					}).collect(Collectors.toList());
					
					transacao.setConciliacaoTransacaoTitulosDTO(titulos);
					
				} // if (!contas.isEmpty())
				
			}//
			
		});
		
		return conciliacaoBancariaDTO;
	}
	
	public ContaPagarEntity getContaComMaiorScore(List<ContaPagarEntity> contas, Map<ContaPagarEntity, Integer> allScores) {
		if (isEmpty(contas)) {
			return null;
		}
		
		if (isEmpty(allScores)) {
			return contas.get(0);
		}
		
		Map<ContaPagarEntity, Integer> scores = allScores.entrySet().stream()
				.filter(entry -> contas.stream().anyMatch(conta -> conta.equals(entry.getKey())))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
		
		if (isEmpty(scores)) {
			return null;
		}
		
		ContaPagarEntity result = Collections.max(scores.entrySet(), Map.Entry.comparingByValue()).getKey();
		return result;
	}
	
	/**
	 * Generates a match score for each bill.
	 * */
	@Override
	public Map<ContaPagarEntity, Integer> computeScore(List<ContaPagarEntity> contas, List<String> tokens, ConciliacaoTransacaoDTO transacao) {
		Map<ContaPagarEntity, Integer> scores = new HashMap<>(contas.size());
		
		contas.forEach(conta -> {
			int score = 0;
			if (isNotEmpty(transacao)) {
				
				boolean isPaga = isNotEmpty(conta.getDataPagamento());
				if (isEquals(conta.getValor(), transacao.getTrnValor()) || (isPaga && isEquals(conta.getValorPago(), transacao.getTrnValor())) ) { // Valor bate.
					score += 7919; // 1000 ésimo número primo.
					
					
					if (isPaga && conta.getDataPagamento().equals(transacao.getTrnData())) { // Está paga e a data de pagamento bate.
						score += 997; // Maior primo antes de mil
					}
					
					if (conta.getDataVencimento().equals(transacao.getTrnData())) { // Data de vencimento bate.
						score += 541; // primo
					}					
					
				}
			}
			
			// Tokens da descrição da conta ou tokens do nome do fornecedor que batem com tokens da descrição da transação bancária
			List<String> descricaoTokens = getTokens(conta.getDescricao());
			List<String> nomeTokens = isNotEmpty(conta.getFornecedor()) ? getTokens(conta.getFornecedor().getNome()) : Collections.emptyList();
			
			for (String token: tokens) {
				
				for (String tokenDesc: descricaoTokens) {
					if (tokenDesc.startsWith(token) || token.startsWith(tokenDesc)) {
						score++;
					}
				} //for
				
				for (String tokenNome: nomeTokens) {
					if (tokenNome.startsWith(token) || token.startsWith(tokenNome)) {
						score++;
					}
				} // for
				
			}
			
			scores.put(conta, score);
		}); // forEach
		//.sorted(Comparator.comparing(ContaPagarEntity::getDataVencimento)/*.reversed()*/)
		
		
		return scores;		
	}

	private String getTrnKey(ConciliacaoTransacaoDTO transacao) {
		String key = transacao.getTrnValor().toString() + "_" + transacao.getTrnHistorico().toLowerCase().replaceAll(" ", "_");
		return key;
	}
	
	private boolean isConciliado(ContaPagarEntity conta, ConciliacaoTransacaoDTO transacao) {
		boolean result = transacao.getTrnId().equals(conta.getIdConcBancaria());
		return result;
	}
	
	public boolean isEmAberto(ContaPagarEntity conta, ConciliacaoTransacaoDTO transacao, 
			List<ContaPagarEntity> contas) {
		ContaPagarEntity contaMaisPerto = getContaEmAbertoComDataVencimentoMaisPerto(transacao.getTrnData(), contas);
		boolean result = isEquals(transacao.getTrnValor(), conta.getValor()) && //
				isEmpty(conta.getDataPagamento()) && //
				(isEmpty(contaMaisPerto) || conta.equals(contaMaisPerto));
		
		return result;
	}
	
	
	
	public ContaPagarEntity getContaEmAbertoComDataVencimentoMaisPerto(LocalDate dataRef, List<ContaPagarEntity> contas) {
		if (isEmpty(contas)) {
			return null;
		}
		
		contas = contas.stream().filter(it -> isEmpty(it.getDataPagamento())).collect(Collectors.toList());
		if (isEmpty(contas)) {
			return null;
		}
		
		ContaPagarEntity result = contas.get(0);
		long dif = daysBetweenAbs(dataRef, result.getDataVencimento());
		if (dif == 0) { // dataRef = dataVencimento, já retorna
			return result;
		}
		
		long minDif = dif;
		for (int i = 1; i < contas.size(); i++) {
			ContaPagarEntity conta = contas.get(i);
			dif = daysBetweenAbs(dataRef, conta.getDataVencimento());
			
			if (dif == 0) { // dataRef = dataVencimento, já retorna
				return result;
			}
			
			if (dif < minDif) {
				minDif = dif;
				result = conta;
			}
		}
		
		return result;
	}
	
	public ContaPagarEntity getContaEmAbertoComValorMaisPerto(BigDecimal valorRef, List<ContaPagarEntity> contas) {
		if (isEmpty(contas)) {
			return null;
		}
		
		contas = contas.stream().filter(it -> isEmpty(it.getDataPagamento())).collect(Collectors.toList());
		if (isEmpty(contas)) {
			return null;
		}
		
		ContaPagarEntity result = contas.get(0);
		BigDecimal dif = getDiff(result.getValor(), valorRef);
		if (isZero(dif)) { // valorRef == valor, já retorna
			return result;
		}
		
		BigDecimal minDif = dif;
		for (int i = 1; i < contas.size(); i++) {
			ContaPagarEntity conta = contas.get(i);
			dif = getDiff(conta.getValor(), valorRef);
			
			if (isZero(dif)) { // valorRef == valor, já retorna
				return result;
			}
			
			if (isLt(dif, minDif)) {
				minDif = dif;
				result = conta;
			}
		}
		
		return result;
	}

	private boolean isPago(ContaPagarEntity conta, ConciliacaoTransacaoDTO transacao) {
		boolean result = isEquals(transacao.getTrnValor(), conta.getValorPago()) && // Valor é igual e...
				isNotEmpty(conta.getDataPagamento()) && 
				transacao.getTrnData().equals(conta.getDataPagamento()); // a data da transação é igual a data de pagamento da conta.
		return result;
	}
	
	//@Transactional
	@Override
	public ConciliacaoBancariaDTO aplicarConciliacaoBancaria(ConciliacaoBancariaDTO conciliacaoBancariaDTO) {
		
		log.info("INICIO aplicarConciliacaoBancaria...");
		
		if (isEmpty(conciliacaoBancariaDTO) || isEmpty(conciliacaoBancariaDTO.getTransacoes())) {
			return conciliacaoBancariaDTO;
		}
		
		List<ConciliacaoTransacaoDTO> transacoes = conciliacaoBancariaDTO.getTransacoes(); 
		
		String bancoId = conciliacaoBancariaDTO.getBancoId();
		String agenciaId = conciliacaoBancariaDTO.getAgenciaId();
		String contaBancariaId = conciliacaoBancariaDTO.getContaId();
		
		String erroMsg = null;
		
		ContaBancariaEntity contaBancariaEntity = contaBancariaRepository.findByNumeroContaAndAgenciaNumeroAgenciaAndAgenciaBancoNumero(contaBancariaId, agenciaId, bancoId);
		if (isEmpty(contaBancariaEntity)) {
			erroMsg = MessageFormat.format("Conta bancária não encontrada para bancoId:{0}, agenciaId:{1}, contaBancariaId:{2}", bancoId, agenciaId, contaBancariaId);
			log.error(erroMsg);
			
			final String msg = erroMsg; 
			transacoes.forEach(it -> {
				it.setConciliadoComErro(true);
				it.setConciliadoMsg(msg);
			});
			
			return conciliacaoBancariaDTO;
		}
		
		for (ConciliacaoTransacaoDTO transacao : transacoes) {
			erroMsg = null;
			String logHeader = MessageFormat.format("Conta id: {0}, doc: {1}, histórico: {2}", 
					transacao.getId(), transacao.getTrnId(), transacao.getTrnHistorico());
			
			if (isEmpty(transacao.getTituloConciliadoId())) {
				erroMsg = "Id do título está vazio para baixar conta via conciliação";
			}
			
			if (isEmpty(erroMsg) && isEmpty(transacao.getTrnValor())) {
				erroMsg = "Valor da transação está vazio para baixar conta via conciliação";
			}
			
			if (isEmpty(erroMsg) && isEmpty(transacao.getTrnData())) {
				erroMsg = "Data da transação está vazia para baixar conta via conciliação";
			}
			
			if (isEmpty(erroMsg) && !transacao.isDebito()) {
				erroMsg = format("Tipo da transação deveria ser DÉDITO mas é: {0}", transacao.getTrnTipo());
			}
			
			if (isNotEmpty(erroMsg)) {
				log.error(erroMsg + ": " + logHeader);
				transacao.setConciliadoComErro(true);
				transacao.setConciliadoMsg(erroMsg);
				continue;
			}
			
			ContaPagarEntity conta = contaPagarRepository.findById(transacao.getTituloConciliadoId()).orElse(null);
			
			if (isEmpty(conta)) {
				erroMsg = "Título não localizado com o id: " + transacao.getTituloConciliadoId();
				log.error(erroMsg + ": " + logHeader);
				transacao.setConciliadoComErro(true);
				transacao.setConciliadoMsg(erroMsg);
				continue;
			}
			
			// Valida algumas coisas da conta
			if (isNotEmpty(conta.getDataPagamento())) {
				erroMsg = format("Conta já baixada em: {0}, doc: {1}", conta.getDataPagamento(), conta.getIdConcBancaria());
				log.error(erroMsg + ": " + logHeader);
				transacao.setConciliadoComErro(true);
				transacao.setConciliadoMsg(erroMsg);
				continue;
			}
			
			if (!isEquals(conta.getValor(), transacao.getTrnValor())) {
				erroMsg = format("Valor da conta: {0}, diverge do valor da transação de conciliação: {1}", conta.getValor(), transacao.getTrnValor());
				log.warn(erroMsg + ": " + logHeader);
				transacao.setConciliadoComErro(false); // Não é um erro, só um warning.
				transacao.setConciliadoMsg(erroMsg);
			}
			
			conta.setDataPagamento(transacao.getTrnData());
			conta.setValorPago(transacao.getTrnValor());
			conta.setDescricao(transacao.getTituloConciliadoDesc());
			conta.setFormaPagamento(FormaPagamento.CONTA_BANCARIA);
			conta.setContaBancaria(contaBancariaEntity);
			conta.setIdConcBancaria(transacao.getTrnId());
			conta.setNumDocConcBancaria(transacao.getTrnDocumento());
			conta.setHistConcBancaria(transacao.getTrnHistorico());
			
			String obs = isNotEmpty(conta.getObservacoes()) ? conta.getObservacoes() : "";
			obs = "*** Histórico: baixa via conciliação bancária:" +  transacao.getTrnHistorico() + "\r\n" + obs;
			conta.setObservacoes(obs);
			
			try {
				
				// Verifica se mudou o plano de contas pela tela de conciliação.
				UUID contaPlanoContasId = isNotEmpty(conta.getPlanoContas()) ? conta.getPlanoContas().getId() : null; 
				UUID dtoPlanoContasId = isNotEmpty(transacao.getTituloPlanoContas()) ? transacao.getTituloPlanoContas().getId() : null; 
				if (isNotEmpty(contaPlanoContasId) && isNotEmpty(dtoPlanoContasId) && !contaPlanoContasId.equals(dtoPlanoContasId)) {
					PlanoContaEntity planoContaEntity = planoContaService.read(dtoPlanoContasId);
					conta.setPlanoContas(planoContaEntity);
				}
				
				conta = contaPagarService.update(conta.getId(), conta);
				
				transacao.setConciliadoComErro(false);
				transacao.setSituacaoConciliacaoTrn(SituacaoConciliacaoTrn.CONCILIADO_CONTAS_PAGAR);
				transacao.setDataConciliacao(LocalDate.now());
				transacao.setTituloConciliadoDesc(conta.getDescricao());
			} catch (Exception e) {
				log.error("Erro ao baixar a conta a pagar via conciliação bancária:" + conta.getId(), e);
				transacao.setConciliadoComErro(true);
				transacao.setConciliadoMsg(e.getMessage());
			}
		}
		
		log.info("FIM aplicarConciliacaoBancaria...");
		
		return conciliacaoBancariaDTO;
	}
	
	

}
