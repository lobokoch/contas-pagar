/**********************************************************************************************
Code generated with MKL Plug-in version: 55.0.3
Copyright: Kerubin - kerubin.platform@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


		
@ApiModel(description = "Details about Tipo da conta bancária")
public enum TipoContaBancaria {
	@ApiModelProperty(notes = "Conta corrente")
	CONTA_CORRENTE,
	
	@ApiModelProperty(notes = "Conta poupança")
	CONTA_POUPANCA,
	
	@ApiModelProperty(notes = "Conta salário")
	CONTA_SALARIO,
	
	@ApiModelProperty(notes = "Conta investimento")
	CONTA_INVESTIMENTO;
}

