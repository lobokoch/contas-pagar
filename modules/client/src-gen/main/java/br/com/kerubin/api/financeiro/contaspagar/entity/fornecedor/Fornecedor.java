/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-22T06:59:30.300
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.fornecedor;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import br.com.kerubin.api.financeiro.contaspagar.TipoPessoa;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@ApiModel(description = "Details about Fornecedor")
public class Fornecedor {

	@ApiModelProperty(notes = "Identificador único", position = 0)
	private java.util.UUID id;
	
	@NotNull(message="\"tipoPessoa\" é obrigatório.")
	@ApiModelProperty(notes = "tipoPessoa", required = true, position = 1)
	private TipoPessoa tipoPessoa;
	
	@NotBlank(message="\"Nome\" é obrigatório.")
	@Size(max = 255, message = "\"Nome\" pode ter no máximo 255 caracteres.")
	@ApiModelProperty(notes = "Nome", required = true, position = 2)
	private String nome;
	
	@Size(max = 255, message = "\"Documento (CNPJ/CPF)\" pode ter no máximo 255 caracteres.")
	@ApiModelProperty(notes = "Documento (CNPJ/CPF)", position = 3)
	private String cnpjCPF;
	
	@ApiModelProperty(notes = "Ativo", position = 4)
	private Boolean ativo = true;
	
	@ApiModelProperty(notes = "inativo", position = 5)
	private Boolean deleted = false;
	
	
	public Fornecedor() {
		// Contructor for reflexion, injection, Jackson, QueryDSL, etc proposal.
	}
	
	
	public java.util.UUID getId() {
		return id;
	}
	
	public TipoPessoa getTipoPessoa() {
		return tipoPessoa;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getCnpjCPF() {
		return cnpjCPF;
	}
	
	public Boolean getAtivo() {
		return ativo;
	}
	
	public Boolean getDeleted() {
		return deleted;
	}
	
	public void setId(java.util.UUID id) {
		this.id = id;
	}
	
	public void setTipoPessoa(TipoPessoa tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setCnpjCPF(String cnpjCPF) {
		this.cnpjCPF = cnpjCPF;
	}
	
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
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
		Fornecedor other = (Fornecedor) obj;
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
