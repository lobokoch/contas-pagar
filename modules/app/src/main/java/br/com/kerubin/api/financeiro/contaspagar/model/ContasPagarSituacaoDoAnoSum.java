package br.com.kerubin.api.financeiro.contaspagar.model;

import lombok.Data;

@Data
public class ContasPagarSituacaoDoAnoSum {

	private java.math.BigDecimal valorVencido;
	private java.math.BigDecimal valorVenceHoje;
	private java.math.BigDecimal valorVenceAmanha;
	private java.math.BigDecimal valorVenceProximos7Dias;
	private java.math.BigDecimal valorVenceMesAtual;
	private java.math.BigDecimal valorVenceProximoMes;
	private java.math.BigDecimal valorPagoMesAtual;
	private java.math.BigDecimal valorPagoMesAnterior;

	public ContasPagarSituacaoDoAnoSum() {
		
	}

}
