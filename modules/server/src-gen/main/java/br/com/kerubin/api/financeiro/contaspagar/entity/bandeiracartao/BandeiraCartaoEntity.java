/**********************************************************************************************
Code generated with MKL Plug-in version: 3.5.1
Code generated at time stamp: 2019-06-01T15:26:20.750
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.bandeiracartao;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;

@Entity
@Table(name = "bandeira_cartao")
public class BandeiraCartaoEntity  {

	@Id
	@Column(name="id")
	private java.util.UUID id;
	
	@Column(name="nome_bandeira")
	private String nomeBandeira;
	
	@Column(name="deleted")
	private Boolean deleted = false;
	
	public java.util.UUID getId() {
		return id;
	}
	
	public String getNomeBandeira() {
		return nomeBandeira;
	}
	
	public Boolean getDeleted() {
		return deleted;
	}
	
	public void setId(java.util.UUID id) {
		this.id = id;
	}
	
	public void setNomeBandeira(String nomeBandeira) {
		this.nomeBandeira = nomeBandeira != null ? nomeBandeira.trim() : nomeBandeira; // Chamadas REST fazem trim.
	}
	
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	
	public void assign(BandeiraCartaoEntity source) {
		if (source != null) {
			this.setId(source.getId());
			this.setNomeBandeira(source.getNomeBandeira());
			this.setDeleted(source.getDeleted());
		}
	}
	
	public BandeiraCartaoEntity clone() {
		BandeiraCartaoEntity theClone = new BandeiraCartaoEntity();
		theClone.setId(this.getId());
		theClone.setNomeBandeira(this.getNomeBandeira());
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
		BandeiraCartaoEntity other = (BandeiraCartaoEntity) obj;
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
