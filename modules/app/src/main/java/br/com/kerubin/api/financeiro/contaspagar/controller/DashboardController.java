package br.com.kerubin.api.financeiro.contaspagar.controller;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
