/**********************************************************************************************
Code generated with MKL Plug-in version: 60.0.6
Copyright: Kerubin - kerubin.platform@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.cartaocredito;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import br.com.kerubin.api.financeiro.contaspagar.entity.banco.BancoLookupResult;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import br.com.kerubin.api.financeiro.contaspagar.entity.bandeiracartao.BandeiraCartaoLookupResult;


@ApiModel(description = "Details about Cartão de crédito")
public class CartaoCredito {

	@ApiModelProperty(notes = "Identificador único", position = 0)
	private java.util.UUID id;
	
	@NotNull(message="\"Banco\" é obrigatório.")
	@ApiModelProperty(notes = "Banco", required = true, position = 1)
	private BancoLookupResult banco;
	
	@NotBlank(message="\"Nome do títular do cartão\" é obrigatório.")
	@Size(max = 255, message = "\"Nome do títular do cartão\" pode ter no máximo 255 caracteres.")
	@ApiModelProperty(notes = "Nome do títular do cartão", required = true, position = 2)
	private String nomeTitular;
	
	@NotBlank(message="\"Número do cartão\" é obrigatório.")
	@Size(max = 50, message = "\"Número do cartão\" pode ter no máximo 50 caracteres.")
	@ApiModelProperty(notes = "Número do cartão", required = true, position = 3)
	private String numeroCartao;
	
	@ApiModelProperty(notes = "Validade", position = 4)
	private java.time.LocalDate validade;
	
	@ApiModelProperty(notes = "Limite de crédito", position = 5)
	private java.math.BigDecimal valorLimite;
	
	@NotNull(message="\"Bandeira do cartão\" é obrigatório.")
	@ApiModelProperty(notes = "Bandeira do cartão", required = true, position = 6)
	private BandeiraCartaoLookupResult bandeiraCartao;
	
	@NotNull(message="\"Cartão ativo\" é obrigatório.")
	@ApiModelProperty(notes = "Cartão ativo", required = true, position = 7)
	private Boolean ativo = true;
	
	@ApiModelProperty(notes = "inativo", position = 8)
	private Boolean deleted = false;
	
	
	public CartaoCredito() {
		// Contructor for reflexion, injection, Jackson, QueryDSL, etc proposal.
	}
	
	
	public java.util.UUID getId() {
		return id;
	}
	
	public BancoLookupResult getBanco() {
		return banco;
	}
	
	public String getNomeTitular() {
		return nomeTitular;
	}
	
	public String getNumeroCartao() {
		return numeroCartao;
	}
	
	public java.time.LocalDate getValidade() {
		return validade;
	}
	
	public java.math.BigDecimal getValorLimite() {
		return valorLimite;
	}
	
	public BandeiraCartaoLookupResult getBandeiraCartao() {
		return bandeiraCartao;
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
	
	public void setBanco(BancoLookupResult banco) {
		this.banco = banco;
	}
	
	public void setNomeTitular(String nomeTitular) {
		this.nomeTitular = nomeTitular;
	}
	
	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}
	
	public void setValidade(java.time.LocalDate validade) {
		this.validade = validade;
	}
	
	public void setValorLimite(java.math.BigDecimal valorLimite) {
		this.valorLimite = valorLimite;
	}
	
	public void setBandeiraCartao(BandeiraCartaoLookupResult bandeiraCartao) {
		this.bandeiraCartao = bandeiraCartao;
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
		CartaoCredito other = (CartaoCredito) obj;
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
