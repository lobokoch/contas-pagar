/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-22T06:59:30.300
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


		
@ApiModel(description = "Details about Forma de pagamento")
public enum FormaPagamento {
	@ApiModelProperty(notes = "Dinheiro")
	DINHEIRO,
	
	@ApiModelProperty(notes = "Conta bancária")
	CONTA_BANCARIA,
	
	@ApiModelProperty(notes = "Cartão de crédito")
	CARTAO_CREDITO,
	
	@ApiModelProperty(notes = "Vale refeição")
	VALE_REFEICAO,
	
	@ApiModelProperty(notes = "Vale alimentação")
	VALE_ALIMENTACAO,
	
	@ApiModelProperty(notes = "Cheque")
	CHEQUE,
	
	@ApiModelProperty(notes = "Outros")
	OUTROS;
}

