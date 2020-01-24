/**********************************************************************************************
Code generated with MKL Plug-in version: 55.0.3
Copyright: Kerubin - kerubin.platform@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.banco;

import lombok.Getter;
import lombok.Setter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Details about Banco")
@Getter @Setter
public class BancoAutoCompleteImpl implements BancoAutoComplete {

	@ApiModelProperty(notes = "Identificador único", required = true, position = 0)
	private java.util.UUID id;
	
	@ApiModelProperty(notes = "Número", required = true, position = 1)
	private String numero;
	
	@ApiModelProperty(notes = "Nome", required = true, position = 2)
	private String nome;
	
	public BancoAutoCompleteImpl() {
		// Contructor for reflexion, injection, Jackson, QueryDSL, etc proposal.
	}

}
