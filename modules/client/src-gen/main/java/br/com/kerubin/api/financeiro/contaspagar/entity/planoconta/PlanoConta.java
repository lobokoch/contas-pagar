/**********************************************************************************************
Code generated with MKL Plug-in version: 60.0.6
Copyright: Kerubin - kerubin.platform@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.planoconta;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import br.com.kerubin.api.financeiro.contaspagar.TipoPlanoContaFinanceiro;
import br.com.kerubin.api.financeiro.contaspagar.TipoReceitaDespesa;
import br.com.kerubin.api.financeiro.contaspagar.entity.planoconta.PlanoContaLookupResult;


@ApiModel(description = "Details about Plano de contas")
public class PlanoConta {

	@ApiModelProperty(notes = "Identificador único", position = 0)
	private java.util.UUID id;
	
	@NotBlank(message="\"Código\" é obrigatório.")
	@Size(max = 255, message = "\"Código\" pode ter no máximo 255 caracteres.")
	@ApiModelProperty(notes = "Código", required = true, position = 1)
	private String codigo;
	
	@NotBlank(message="\"Descrição\" é obrigatório.")
	@Size(max = 255, message = "\"Descrição\" pode ter no máximo 255 caracteres.")
	@ApiModelProperty(notes = "Descrição", required = true, position = 2)
	private String descricao;
	
	@NotNull(message="\"tipoFinanceiro\" é obrigatório.")
	@ApiModelProperty(notes = "tipoFinanceiro", required = true, position = 3)
	private TipoPlanoContaFinanceiro tipoFinanceiro;
	
	@ApiModelProperty(notes = "Tipo receita/despesa", position = 4)
	private TipoReceitaDespesa tipoReceitaDespesa;
	
	@ApiModelProperty(notes = "Plano de conta pai", position = 5)
	private PlanoContaLookupResult planoContaPai;
	
	@NotNull(message="\"Ativo\" é obrigatório.")
	@ApiModelProperty(notes = "Ativo", required = true, position = 6)
	private Boolean ativo = true;
	
	@ApiModelProperty(notes = "inativo", position = 7)
	private Boolean deleted = false;
	
	
	public PlanoConta() {
		// Contructor for reflexion, injection, Jackson, QueryDSL, etc proposal.
	}
	
	
	public java.util.UUID getId() {
		return id;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public TipoPlanoContaFinanceiro getTipoFinanceiro() {
		return tipoFinanceiro;
	}
	
	public TipoReceitaDespesa getTipoReceitaDespesa() {
		return tipoReceitaDespesa;
	}
	
	public PlanoContaLookupResult getPlanoContaPai() {
		return planoContaPai;
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
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public void setTipoFinanceiro(TipoPlanoContaFinanceiro tipoFinanceiro) {
		this.tipoFinanceiro = tipoFinanceiro;
	}
	
	public void setTipoReceitaDespesa(TipoReceitaDespesa tipoReceitaDespesa) {
		this.tipoReceitaDespesa = tipoReceitaDespesa;
	}
	
	public void setPlanoContaPai(PlanoContaLookupResult planoContaPai) {
		this.planoContaPai = planoContaPai;
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
		PlanoConta other = (PlanoConta) obj;
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
