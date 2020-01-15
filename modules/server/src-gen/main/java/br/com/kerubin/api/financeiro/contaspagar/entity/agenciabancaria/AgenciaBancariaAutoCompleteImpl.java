/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-13T08:12:17.669
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.agenciabancaria;

import lombok.Getter;
import lombok.Setter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Details about Agência bancária")
@Getter @Setter
public class AgenciaBancariaAutoCompleteImpl implements AgenciaBancariaAutoComplete {

	@ApiModelProperty(notes = "Identificador único", required = true, position = 0)
	private java.util.UUID id;
	
	@ApiModelProperty(notes = "Número da agência", required = true, position = 2)
	private String numeroAgencia;
	
	@ApiModelProperty(notes = "Dígito", required = true, position = 3)
	private String digitoAgencia;
	
	@ApiModelProperty(notes = "Endereço/localização da agência", position = 4)
	private String endereco;
	
	public AgenciaBancariaAutoCompleteImpl() {
		// Contructor for reflexion, injection, Jackson, QueryDSL, etc proposal.
	}

}
