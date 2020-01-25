package br.com.kerubin.api.financeiro.contaspagar.conciliacaobancaria;

import java.util.List;

import br.com.kerubin.api.financeiro.contaspagar.entity.contapagar.ContaPagarEntity;

public interface ConciliacaoBancariaService {

	ConciliacaoBancariaDTO verificarTransacoes(ConciliacaoBancariaDTO conciliacaoBancariaDTO);

	ConciliacaoBancariaDTO aplicarConciliacaoBancaria(ConciliacaoBancariaDTO conciliacaoBancariaDTO);

	List<ContaPagarEntity> discardNotStartsWithTokens(List<ContaPagarEntity> contas, List<String> tokens);

}
