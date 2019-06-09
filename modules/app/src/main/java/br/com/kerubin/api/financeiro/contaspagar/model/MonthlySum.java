package br.com.kerubin.api.financeiro.contaspagar.model;

import lombok.Data;

@Data
public class MonthlySum {

	private java.math.BigDecimal january;
	private java.math.BigDecimal february;
	private java.math.BigDecimal march;
	private java.math.BigDecimal april;
	private java.math.BigDecimal may;
	private java.math.BigDecimal june;
	private java.math.BigDecimal july;
	private java.math.BigDecimal august;
	private java.math.BigDecimal september;
	private java.math.BigDecimal october;
	private java.math.BigDecimal november;
	private java.math.BigDecimal december;

	public MonthlySum() {
		
	}

}
