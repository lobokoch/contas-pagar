package br.com.kerubin.api.financeiro.contaspagar.service;

import java.util.List;

import br.com.kerubin.api.financeiro.contaspagar.model.ContasPagarHojeResumoDTO;
import br.com.kerubin.api.financeiro.contaspagar.model.ContasPagarSituacaoDoAnoSum;
import br.com.kerubin.api.financeiro.contaspagar.model.MonthlySumContasPagar;

public interface ContasPagarDashboardService {

	MonthlySumContasPagar getMonthlySumContasPagar();

	ContasPagarSituacaoDoAnoSum getContasPagarSituacaoDoAno();

	List<ContasPagarHojeResumoDTO> getContasPagarHojeResumo();

}
