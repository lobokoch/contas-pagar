package br.com.kerubin.api.financeiro.contaspagar.conciliacaobancaria;

import java.util.List;
import java.util.Map;

import br.com.kerubin.api.financeiro.contaspagar.entity.contapagar.ContaPagarEntity;

public interface ConciliacaoBancariaService {

	ConciliacaoBancariaDTO verificarTransacoes(ConciliacaoBancariaDTO conciliacaoBancariaDTO);

	ConciliacaoBancariaDTO aplicarConciliacaoBancaria(ConciliacaoBancariaDTO conciliacaoBancariaDTO);

	Map<ContaPagarEntity, Integer> computeScore(List<ContaPagarEntity> contas, List<String> tokens, ConciliacaoTransacaoDTO transacao);

}
