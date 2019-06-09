package br.com.kerubin.api.financeiro.contaspagar.model;

import lombok.Data;

@Data
public class MonthlySumContasPagar {
	
	private MonthlySum apagar;
	private MonthlySum pago;
	
	public MonthlySumContasPagar(MonthlySum apagar, MonthlySum pago) {
		this.apagar = apagar;
		this.pago = pago;
	}
	
	public MonthlySumContasPagar() {
		apagar = new MonthlySum();
		pago = new MonthlySum();
	}

}
