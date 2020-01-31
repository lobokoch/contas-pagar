/**********************************************************************************************
Code generated with MKL Plug-in version: 60.0.6
Copyright: Kerubin - kerubin.platform@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.contapagar;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
		
@ApiModel(description = "Details about sums of Contas a pagar")
public class ContaPagarSumFields {
	
	@ApiModelProperty(notes = "Sum of Valor da conta", required = true, position = 4)
	private java.math.BigDecimal sumValor;
	
	@ApiModelProperty(notes = "Sum of Descontos", position = 10)
	private java.math.BigDecimal sumValorDesconto;
	
	@ApiModelProperty(notes = "Sum of Multas", position = 11)
	private java.math.BigDecimal sumValorMulta;
	
	@ApiModelProperty(notes = "Sum of Juros mora", position = 12)
	private java.math.BigDecimal sumValorJuros;
	
	@ApiModelProperty(notes = "Sum of Acréscimos", position = 13)
	private java.math.BigDecimal sumValorAcrescimos;
	
	@ApiModelProperty(notes = "Sum of Valor pago", position = 14)
	private java.math.BigDecimal sumValorPago;
	
	public ContaPagarSumFields() {
		// Contructor for reflexion, injection, Jackson, QueryDSL, etc proposal.
	}
	
	public java.math.BigDecimal getSumValor() {
		return sumValor;
	}
	
	public java.math.BigDecimal getSumValorDesconto() {
		return sumValorDesconto;
	}
	
	public java.math.BigDecimal getSumValorMulta() {
		return sumValorMulta;
	}
	
	public java.math.BigDecimal getSumValorJuros() {
		return sumValorJuros;
	}
	
	public java.math.BigDecimal getSumValorAcrescimos() {
		return sumValorAcrescimos;
	}
	
	public java.math.BigDecimal getSumValorPago() {
		return sumValorPago;
	}
	
	public void setSumValor(java.math.BigDecimal sumValor) {
		this.sumValor = sumValor;
	}
	
	public void setSumValorDesconto(java.math.BigDecimal sumValorDesconto) {
		this.sumValorDesconto = sumValorDesconto;
	}
	
	public void setSumValorMulta(java.math.BigDecimal sumValorMulta) {
		this.sumValorMulta = sumValorMulta;
	}
	
	public void setSumValorJuros(java.math.BigDecimal sumValorJuros) {
		this.sumValorJuros = sumValorJuros;
	}
	
	public void setSumValorAcrescimos(java.math.BigDecimal sumValorAcrescimos) {
		this.sumValorAcrescimos = sumValorAcrescimos;
	}
	
	public void setSumValorPago(java.math.BigDecimal sumValorPago) {
		this.sumValorPago = sumValorPago;
	}

}
