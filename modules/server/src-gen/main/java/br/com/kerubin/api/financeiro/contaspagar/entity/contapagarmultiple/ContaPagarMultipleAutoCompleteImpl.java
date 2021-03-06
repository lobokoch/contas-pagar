/**********************************************************************************************
Code generated by MKL Plug-in
Copyright: Kerubin - kerubin.platform@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.contapagarmultiple;

import lombok.Getter;
import lombok.Setter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Details about Registros de pagamento")
@Getter @Setter
public class ContaPagarMultipleAutoCompleteImpl implements ContaPagarMultipleAutoComplete {

	@ApiModelProperty(notes = "Identificador único", required = true, position = 0)
	private java.util.UUID id;
	
	@ApiModelProperty(notes = "Descrição", required = true, position = 3)
	private String descricao;
	
	public ContaPagarMultipleAutoCompleteImpl() {
		// Contructor for reflexion, injection, Jackson, QueryDSL, etc proposal.
	}

}
