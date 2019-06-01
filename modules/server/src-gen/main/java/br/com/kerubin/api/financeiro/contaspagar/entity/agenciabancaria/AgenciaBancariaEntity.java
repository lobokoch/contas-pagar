/**********************************************************************************************
Code generated with MKL Plug-in version: 3.5.1
Code generated at time stamp: 2019-06-01T15:26:20.750
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.agenciabancaria;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import br.com.kerubin.api.financeiro.contaspagar.entity.banco.BancoEntity;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "agencia_bancaria")
public class AgenciaBancariaEntity  {

	@Id
	@Column(name="id")
	private java.util.UUID id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "banco")
	private BancoEntity banco;
	
	@Column(name="numero_agencia")
	private String numeroAgencia;
	
	@Column(name="digito_agencia")
	private String digitoAgencia;
	
	@Column(name="endereco")
	private String endereco;
	
	@Column(name="deleted")
	private Boolean deleted = false;
	
	public java.util.UUID getId() {
		return id;
	}
	
	public BancoEntity getBanco() {
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
	
	public void setBanco(BancoEntity banco) {
		this.banco = banco;
	}
	
	public void setNumeroAgencia(String numeroAgencia) {
		this.numeroAgencia = numeroAgencia != null ? numeroAgencia.trim() : numeroAgencia; // Chamadas REST fazem trim.
	}
	
	public void setDigitoAgencia(String digitoAgencia) {
		this.digitoAgencia = digitoAgencia != null ? digitoAgencia.trim() : digitoAgencia; // Chamadas REST fazem trim.
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco != null ? endereco.trim() : endereco; // Chamadas REST fazem trim.
	}
	
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	
	public void assign(AgenciaBancariaEntity source) {
		if (source != null) {
			this.setId(source.getId());
			this.setBanco(source.getBanco());
			this.setNumeroAgencia(source.getNumeroAgencia());
			this.setDigitoAgencia(source.getDigitoAgencia());
			this.setEndereco(source.getEndereco());
			this.setDeleted(source.getDeleted());
		}
	}
	
	public AgenciaBancariaEntity clone() {
		AgenciaBancariaEntity theClone = new AgenciaBancariaEntity();
		theClone.setId(this.getId());
		theClone.setBanco(this.getBanco());
		theClone.setNumeroAgencia(this.getNumeroAgencia());
		theClone.setDigitoAgencia(this.getDigitoAgencia());
		theClone.setEndereco(this.getEndereco());
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
		AgenciaBancariaEntity other = (AgenciaBancariaEntity) obj;
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
