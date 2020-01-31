/**********************************************************************************************
Code generated with MKL Plug-in version: 60.0.6
Copyright: Kerubin - kerubin.platform@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.agenciabancaria;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
		
@ApiModel(description = "Details about lookup result of Agência bancária")
public class AgenciaBancariaLookupResult {

	@ApiModelProperty(notes = "Identificador único", required = true, position = 0)
	private java.util.UUID id;
	
	@ApiModelProperty(notes = "Número da agência", required = true, position = 2)
	private String numeroAgencia;
	
	@ApiModelProperty(notes = "Dígito", required = true, position = 3)
	private String digitoAgencia;
	
	@ApiModelProperty(notes = "Endereço/localização da agência", position = 4)
	private String endereco;
	
	
	public AgenciaBancariaLookupResult() {
		// Contructor for reflexion, injection, Jackson, QueryDSL, etc proposal.
	}
	
	
	public java.util.UUID getId() {
		return id;
	}
	
	public String getNumeroAgencia() {
		return numeroAgencia;
	}
	
	public String getDigitoAgencia() {
		return digitoAgencia;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setId(java.util.UUID id) {
		this.id = id;
	}
	
	public void setNumeroAgencia(String numeroAgencia) {
		this.numeroAgencia = numeroAgencia;
	}
	
	public void setDigitoAgencia(String digitoAgencia) {
		this.digitoAgencia = digitoAgencia;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AgenciaBancariaLookupResult other = (AgenciaBancariaLookupResult) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		
		return true;
	}
	
	@Override
	public int hashCode() {
		return 31;
	}
	
	/* 
	@Override
	public String toString() {
		// Enabling toString for JPA entities will implicitly trigger lazy loading on all fields.
	}
	*/

}
