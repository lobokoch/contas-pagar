/**********************************************************************************************
Code generated with MKL Plug-in version: 60.0.6
Copyright: Kerubin - kerubin.platform@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.fornecedor;


import java.util.HashMap;
import java.util.Map;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

		
@ApiModel(description = "Details about list filter of Fornecedor")
public class FornecedorListFilter {

	@ApiModelProperty(notes = "Nome", position = 2)
	private java.util.List<String> nome;
	
	// Map field for developer customizing parameters.
	@ApiModelProperty(notes = "Campo tipo mapa (chave = valor) onde o desenvolvedor pode passar seus parâmetros personalizados no formato objeto JSON.", position = 9999)
	private Map<Object, Object> customParams = new HashMap<>();
	
	public java.util.List<String> getNome() {
		return nome;
	}
	
	public void setNome(java.util.List<String> nome) {
		this.nome = nome;
	}
	
	public Map<Object, Object> getCustomParams() {
		return customParams;
	}
	
	public void setCustomParams(Map<Object, Object> customParams) {
		this.customParams = customParams;
	}
	
}
