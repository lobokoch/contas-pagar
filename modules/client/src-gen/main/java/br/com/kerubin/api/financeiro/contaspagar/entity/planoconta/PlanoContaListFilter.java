/**********************************************************************************************
Code generated with MKL Plug-in version: 55.0.3
Copyright: Kerubin - kerubin.platform@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.planoconta;


import java.util.HashMap;
import java.util.Map;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

		
@ApiModel(description = "Details about list filter of Plano de contas")
public class PlanoContaListFilter {

	@ApiModelProperty(notes = "Código", position = 1)
	private java.util.List<String> codigo;
	
	@ApiModelProperty(notes = "Descrição", position = 2)
	private java.util.List<String> descricao;
	
	@ApiModelProperty(notes = "Ativo is not null", position = 6)
	private Boolean ativoIsNotNull;
	
	@ApiModelProperty(notes = "Ativo is null", position = 6)
	private Boolean ativoIsNull;
	
	// Map field for developer customizing parameters.
	@ApiModelProperty(notes = "Campo tipo mapa (chave = valor) onde o desenvolvedor pode passar seus parâmetros personalizados no formato objeto JSON.", position = 9999)
	private Map<Object, Object> customParams = new HashMap<>();
	
	public java.util.List<String> getCodigo() {
		return codigo;
	}
	
	public void setCodigo(java.util.List<String> codigo) {
		this.codigo = codigo;
	}
	
	public java.util.List<String> getDescricao() {
		return descricao;
	}
	
	public void setDescricao(java.util.List<String> descricao) {
		this.descricao = descricao;
	}
	
	public Boolean isAtivoIsNotNull() {
		return ativoIsNotNull != null && ativoIsNotNull;
	}
	
	public Boolean getAtivoIsNotNull() {
		return ativoIsNotNull;
	}
	
	public void setAtivoIsNotNull(Boolean ativoIsNotNull) {
		this.ativoIsNotNull = ativoIsNotNull;
	}
	
	public Boolean isAtivoIsNull() {
		return ativoIsNull != null && ativoIsNull;
	}
	
	public Boolean getAtivoIsNull() {
		return ativoIsNull;
	}
	
	public void setAtivoIsNull(Boolean ativoIsNull) {
		this.ativoIsNull = ativoIsNull;
	}
	
	
	public Map<Object, Object> getCustomParams() {
		return customParams;
	}
	
	public void setCustomParams(Map<Object, Object> customParams) {
		this.customParams = customParams;
	}
	
}
