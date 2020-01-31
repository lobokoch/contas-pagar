/**********************************************************************************************
Code generated with MKL Plug-in version: 60.0.6
Copyright: Kerubin - kerubin.platform@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.agenciabancaria;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import br.com.kerubin.api.financeiro.contaspagar.entity.banco.BancoLookupResult;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@ApiModel(description = "Details about Agência bancária")
public class AgenciaBancaria {

	@ApiModelProperty(notes = "Identificador único", position = 0)
	private java.util.UUID id;
	
	@NotNull(message="\"Banco\" é obrigatório.")
	@ApiModelProperty(notes = "Banco", required = true, position = 1)
	private BancoLookupResult banco;
	
	@NotBlank(message="\"Número da agência\" é obrigatório.")
	@Size(max = 50, message = "\"Número da agência\" pode ter no máximo 50 caracteres.")
	@ApiModelProperty(notes = "Número da agência", required = true, position = 2)
	private String numeroAgencia;
	
	@NotBlank(message="\"Dígito\" é obrigatório.")
	@Size(max = 10, message = "\"Dígito\" pode ter no máximo 10 caracteres.")
	@ApiModelProperty(notes = "Dígito", required = true, position = 3)
	private String digitoAgencia;
	
	@Size(max = 255, message = "\"Endereço/localização da agência\" pode ter no máximo 255 caracteres.")
	@ApiModelProperty(notes = "Endereço/localização da agência", position = 4)
	private String endereco;
	
	@ApiModelProperty(notes = "inativo", position = 5)
	private Boolean deleted = false;
	
	
	public AgenciaBancaria() {
		// Contructor for reflexion, injection, Jackson, QueryDSL, etc proposal.
	}
	
	
	public java.util.UUID getId() {
		return id;
	}
	
	public BancoLookupResult getBanco() {
		return banco;
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
	
	public Boolean getDeleted() {
		return deleted;
	}
	
	public void setId(java.util.UUID id) {
		this.id = id;
	}
	
	public void setBanco(BancoLookupResult banco) {
		this.banco = banco;
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
	
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AgenciaBancaria other = (AgenciaBancaria) obj;
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
