/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-22T06:59:30.300
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.contabancaria;


import java.util.HashMap;
import java.util.Map;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

		
@ApiModel(description = "Details about list filter of Conta bancária")
public class ContaBancariaListFilter {

	@ApiModelProperty(notes = "Número da conta", position = 4)
	private java.util.List<String> numeroConta;
	
	// Map field for developer customizing parameters.
	@ApiModelProperty(notes = "Campo tipo mapa (chave = valor) onde o desenvolvedor pode passar seus parâmetros personalizados no formato objeto JSON.", position = 9999)
	private Map<Object, Object> customParams = new HashMap<>();
	
	public java.util.List<String> getNumeroConta() {
		return numeroConta;
	}
	
	public void setNumeroConta(java.util.List<String> numeroConta) {
		this.numeroConta = numeroConta;
	}
	
	public Map<Object, Object> getCustomParams() {
		return customParams;
	}
	
	public void setCustomParams(Map<Object, Object> customParams) {
		this.customParams = customParams;
	}
	
}
