/**********************************************************************************************
Code generated with MKL Plug-in version: 3.9.0
Code generated at time stamp: 2019-06-08T10:32:58.121
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.banco;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;

@Entity
@Table(name = "banco")
public class BancoEntity  {

	@Id
	@Column(name="id")
	private java.util.UUID id;
	
	@Column(name="numero")
	private String numero;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="deleted")
	private Boolean deleted = false;
	
	public java.util.UUID getId() {
		return id;
	}
	
	public String getNumero() {
		return numero;
	}
	
	public String getNome() {
		return nome;
	}
	
	public Boolean getDeleted() {
		return deleted;
	}
	
	public void setId(java.util.UUID id) {
		this.id = id;
	}
	
	public void setNumero(String numero) {
		this.numero = numero != null ? numero.trim() : numero; // Chamadas REST fazem trim.
	}
	
	public void setNome(String nome) {
		this.nome = nome != null ? nome.trim() : nome; // Chamadas REST fazem trim.
	}
	
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	
	public void assign(BancoEntity source) {
		if (source != null) {
			this.setId(source.getId());
			this.setNumero(source.getNumero());
			this.setNome(source.getNome());
			this.setDeleted(source.getDeleted());
		}
	}
	
	public BancoEntity clone() {
		BancoEntity theClone = new BancoEntity();
		theClone.setId(this.getId());
		theClone.setNumero(this.getNumero());
		theClone.setNome(this.getNome());
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
		BancoEntity other = (BancoEntity) obj;
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
