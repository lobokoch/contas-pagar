/**********************************************************************************************
Code generated with MKL Plug-in version: 30.0.1
Code generated at time stamp: 2019-11-10T19:31:44.455
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.conciliacaobancaria;

public class ConciliacaoBancariaLookupResult {

	private java.util.UUID id;
	
	private String bancoId;
	
	
	public ConciliacaoBancariaLookupResult() {
		// Contructor for reflexion, injection, Jackson, QueryDSL, etc proposal.
	}
	
	
	public java.util.UUID getId() {
		return id;
	}
	
	public String getBancoId() {
		return bancoId;
	}
	
	public void setId(java.util.UUID id) {
		this.id = id;
	}
	
	public void setBancoId(String bancoId) {
		this.bancoId = bancoId;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConciliacaoBancariaLookupResult other = (ConciliacaoBancariaLookupResult) obj;
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
