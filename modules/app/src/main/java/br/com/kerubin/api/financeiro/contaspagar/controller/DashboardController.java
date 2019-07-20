package br.com.kerubin.api.financeiro.contaspagar.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.kerubin.api.financeiro.contaspagar.model.ContasPagarHojeResumo;
import br.com.kerubin.api.financeiro.contaspagar.model.ContasPagarHojeResumoCompleto;
import br.com.kerubin.api.financeiro.contaspagar.model.ContasPagarSituacaoDoAnoSum;
import br.com.kerubin.api.financeiro.contaspagar.model.MonthlySumContasPagar;
import br.com.kerubin.api.financeiro.contaspagar.service.ContasPagarDashboardService;

@RestController
@RequestMapping("dashboard")
public class DashboardController {
	
	@Inject
	private ContasPagarDashboardService contasPagarDashboardService;
	
	@GetMapping("/getMonthlySumContasPagar")
	public MonthlySumContasPagar getMonthlySumContasPagar() {
		MonthlySumContasPagar result = contasPagarDashboardService.getMonthlySumContasPagar();
		return result;
	}
	
	@GetMapping("/getContasPagarSituacaoDoAno")
	public ContasPagarSituacaoDoAnoSum getContasPagarSituacaoDoAno() {
		ContasPagarSituacaoDoAnoSum result = contasPagarDashboardService.getContasPagarSituacaoDoAno();
		return result;
	}
	
	@GetMapping("/getContasPagarHojeResumo")
	public List<ContasPagarHojeResumo> getContasPagarHojeResumo() {
		List<ContasPagarHojeResumo> result = contasPagarDashboardService.getContasPagarHojeResumo();
		return result;
	}
	
	@GetMapping("/getContasPagarHojeResumoCompleto")
	public ContasPagarHojeResumoCompleto getContasPagarHojeResumoCompleto() {
		ContasPagarHojeResumoCompleto result = contasPagarDashboardService.getContasPagarHojeResumoCompleto();
		return result;
	}

}
