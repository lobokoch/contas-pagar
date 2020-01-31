/**********************************************************************************************
Code generated with MKL Plug-in version: 60.0.6
Copyright: Kerubin - kerubin.platform@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.fornecedor;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import br.com.kerubin.api.financeiro.contaspagar.TipoPessoa;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "fornecedor")
public class FornecedorEntity  {

	@Id
	@Column(name="id")
	private java.util.UUID id;
	
	@NotNull(message="\"tipoPessoa\" é obrigatório.")
	@Enumerated(EnumType.STRING)
	@Column(name="tipo_pessoa")
	private TipoPessoa tipoPessoa;
	
	@NotBlank(message="\"Nome\" é obrigatório.")
	@Size(max = 255, message = "\"Nome\" pode ter no máximo 255 caracteres.")
	@Column(name="nome")
	private String nome;
	
	@Size(max = 255, message = "\"Documento (CNPJ/CPF)\" pode ter no máximo 255 caracteres.")
	@Column(name="cnpj_cpf")
	private String cnpjCPF;
	
	@Column(name="ativo")
	private Boolean ativo = true;
	
	@Column(name="deleted")
	private Boolean deleted = false;
	
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
		this.nome = nome != null ? nome.trim() : nome; // Chamadas REST fazem trim.
	}
	
	public void setCnpjCPF(String cnpjCPF) {
		this.cnpjCPF = cnpjCPF != null ? cnpjCPF.trim() : cnpjCPF; // Chamadas REST fazem trim.
	}
	
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	
	public void assign(FornecedorEntity source) {
		if (source != null) {
			this.setId(source.getId());
			this.setTipoPessoa(source.getTipoPessoa());
			this.setNome(source.getNome());
			this.setCnpjCPF(source.getCnpjCPF());
			this.setAtivo(source.getAtivo());
			this.setDeleted(source.getDeleted());
		}
	}
	
	public FornecedorEntity clone() {
		return clone(new java.util.HashMap<>());
	}
	
	public FornecedorEntity clone(java.util.Map<Object, Object> visited) {
		if (visited.containsKey(this)) {
			return (FornecedorEntity) visited.get(this);
		}
				
		FornecedorEntity theClone = new FornecedorEntity();
		visited.put(this, theClone);
		
		theClone.setId(this.getId());
		theClone.setTipoPessoa(this.getTipoPessoa());
		theClone.setNome(this.getNome());
		theClone.setCnpjCPF(this.getCnpjCPF());
		theClone.setAtivo(this.getAtivo());
		theClone.setDeleted(this.getDeleted());
		
		return theClone;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FornecedorEntity other = (FornecedorEntity) obj;
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
