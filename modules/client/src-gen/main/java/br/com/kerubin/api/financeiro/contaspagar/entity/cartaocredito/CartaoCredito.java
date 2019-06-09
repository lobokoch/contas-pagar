/**********************************************************************************************
Code generated with MKL Plug-in version: 3.9.0
Code generated at time stamp: 2019-06-08T10:32:58.121
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.cartaocredito;

import javax.validation.constraints.NotNull;
import br.com.kerubin.api.financeiro.contaspagar.entity.banco.BancoLookupResult;
import javax.validation.constraints.NotBlank;
import br.com.kerubin.api.financeiro.contaspagar.entity.bandeiracartao.BandeiraCartaoLookupResult;

public class CartaoCredito {

	private java.util.UUID id;
	
	@NotNull(message="'Banco' é obrigatório.")
	private BancoLookupResult banco;
	
	@NotBlank(message="'Nome do títular do cartão' é obrigatório.")
	private String nomeTitular;
	
	@NotBlank(message="'Número do cartão' é obrigatório.")
	private String numeroCartao;
	
	private java.time.LocalDate validade;
	
	private java.math.BigDecimal valorLimite;
	
	@NotNull(message="'Bandeira do cartão' é obrigatório.")
	private BandeiraCartaoLookupResult bandeiraCartao;
	
	@NotNull(message="'Cartão ativo' é obrigatório.")
	private Boolean ativo = true;
	
	private Boolean deleted = false;
	
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
