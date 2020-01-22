/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-22T06:59:30.300
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.planoconta;

import lombok.Getter;
import lombok.Setter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Details about Plano de contas")
@Getter @Setter
public class PlanoContaAutoCompleteImpl implements PlanoContaAutoComplete {

	@ApiModelProperty(notes = "Identificador único", required = true, position = 0)
	private java.util.UUID id;
	
	@ApiModelProperty(notes = "Código", required = true, position = 1)
	private String codigo;
	
	@ApiModelProperty(notes = "Descrição", required = true, position = 2)
	private String descricao;
	
	public PlanoContaAutoCompleteImpl() {
		// Contructor for reflexion, injection, Jackson, QueryDSL, etc proposal.
	}

}
