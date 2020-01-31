/**********************************************************************************************
Code generated with MKL Plug-in version: 60.0.6
Copyright: Kerubin - kerubin.platform@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.contabancaria;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import br.com.kerubin.api.financeiro.contaspagar.entity.agenciabancaria.AgenciaBancariaEntity;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import br.com.kerubin.api.financeiro.contaspagar.TipoContaBancaria;

@Entity
@Table(name = "conta_bancaria")
public class ContaBancariaEntity  {

	@Id
	@Column(name="id")
	private java.util.UUID id;
	
	@NotBlank(message="\"Nome do títular da conta\" é obrigatório.")
	@Size(max = 255, message = "\"Nome do títular da conta\" pode ter no máximo 255 caracteres.")
	@Column(name="nome_titular")
	private String nomeTitular;
	
	@NotNull(message="\"Agência bancária\" é obrigatório.")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "agencia")
	private AgenciaBancariaEntity agencia;
	
	@NotNull(message="\"Tipo da conta\" é obrigatório.")
	@Enumerated(EnumType.STRING)
	@Column(name="tipo_conta_bancaria")
	private TipoContaBancaria tipoContaBancaria;
	
	@NotBlank(message="\"Número da conta\" é obrigatório.")
	@Size(max = 30, message = "\"Número da conta\" pode ter no máximo 30 caracteres.")
	@Column(name="numero_conta")
	private String numeroConta;
	
	@Size(max = 10, message = "\"Dígito\" pode ter no máximo 10 caracteres.")
	@Column(name="digito")
	private String digito;
	
	@Column(name="data_validade")
	private java.time.LocalDate dataValidade;
	
	@NotNull(message="\"Conta ativa\" é obrigatório.")
	@Column(name="ativo")
	private Boolean ativo = true;
	
	@Column(name="deleted")
	private Boolean deleted = false;
	
	public java.util.UUID getId() {
		return id;
	}
	
	public String getNomeTitular() {
		return nomeTitular;
	}
	
	public AgenciaBancariaEntity getAgencia() {
		return agencia;
	}
	
	public TipoContaBancaria getTipoContaBancaria() {
		return tipoContaBancaria;
	}
	
	public String getNumeroConta() {
		return numeroConta;
	}
	
	public String getDigito() {
		return digito;
	}
	
	public java.time.LocalDate getDataValidade() {
		return dataValidade;
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
	
	public void setNomeTitular(String nomeTitular) {
		this.nomeTitular = nomeTitular != null ? nomeTitular.trim() : nomeTitular; // Chamadas REST fazem trim.
	}
	
	public void setAgencia(AgenciaBancariaEntity agencia) {
		this.agencia = agencia;
	}
	
	public void setTipoContaBancaria(TipoContaBancaria tipoContaBancaria) {
		this.tipoContaBancaria = tipoContaBancaria;
	}
	
	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta != null ? numeroConta.trim() : numeroConta; // Chamadas REST fazem trim.
	}
	
	public void setDigito(String digito) {
		this.digito = digito != null ? digito.trim() : digito; // Chamadas REST fazem trim.
	}
	
	public void setDataValidade(java.time.LocalDate dataValidade) {
		this.dataValidade = dataValidade;
	}
	
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	
	public void assign(ContaBancariaEntity source) {
		if (source != null) {
			this.setId(source.getId());
			this.setNomeTitular(source.getNomeTitular());
			this.setAgencia(source.getAgencia());
			this.setTipoContaBancaria(source.getTipoContaBancaria());
			this.setNumeroConta(source.getNumeroConta());
			this.setDigito(source.getDigito());
			this.setDataValidade(source.getDataValidade());
			this.setAtivo(source.getAtivo());
			this.setDeleted(source.getDeleted());
		}
	}
	
	public ContaBancariaEntity clone() {
		return clone(new java.util.HashMap<>());
	}
	
	public ContaBancariaEntity clone(java.util.Map<Object, Object> visited) {
		if (visited.containsKey(this)) {
			return (ContaBancariaEntity) visited.get(this);
		}
				
		ContaBancariaEntity theClone = new ContaBancariaEntity();
		visited.put(this, theClone);
		
		theClone.setId(this.getId());
		theClone.setNomeTitular(this.getNomeTitular());
		theClone.setAgencia(this.getAgencia() != null ? this.getAgencia().clone(visited) : null);
		theClone.setTipoContaBancaria(this.getTipoContaBancaria());
		theClone.setNumeroConta(this.getNumeroConta());
		theClone.setDigito(this.getDigito());
		theClone.setDataValidade(this.getDataValidade());
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
		ContaBancariaEntity other = (ContaBancariaEntity) obj;
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
