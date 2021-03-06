/**********************************************************************************************
Code generated by MKL Plug-in
Copyright: Kerubin - kerubin.platform@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.agenciabancaria;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import br.com.kerubin.api.financeiro.contaspagar.entity.banco.BancoEntity;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "agencia_bancaria")
public class AgenciaBancariaEntity  {

	@Id
	@Column(name="id")
	private java.util.UUID id;
	
	@NotNull(message="\"Banco\" é obrigatório.")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "banco")
	private BancoEntity banco;
	
	@NotBlank(message="\"Número da agência\" é obrigatório.")
	@Size(max = 50, message = "\"Número da agência\" pode ter no máximo 50 caracteres.")
	@Column(name="numero_agencia")
	private String numeroAgencia;
	
	@NotBlank(message="\"Dígito\" é obrigatório.")
	@Size(max = 10, message = "\"Dígito\" pode ter no máximo 10 caracteres.")
	@Column(name="digito_agencia")
	private String digitoAgencia;
	
	@Size(max = 255, message = "\"Endereço/localização da agência\" pode ter no máximo 255 caracteres.")
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
	
	public boolean isDeleted() {
		return Boolean.TRUE.equals(deleted);
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
		return clone(new java.util.HashMap<>());
	}
	
	public AgenciaBancariaEntity clone(java.util.Map<Object, Object> visited) {
		if (visited.containsKey(this)) {
			return (AgenciaBancariaEntity) visited.get(this);
		}
				
		AgenciaBancariaEntity theClone = new AgenciaBancariaEntity();
		visited.put(this, theClone);
		
		theClone.setId(this.getId());
		theClone.setBanco(this.getBanco() != null ? this.getBanco().clone(visited) : null);
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
			
		
		// Field: id
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id))
			return false;
		
		// Field: banco
		if (banco == null) {
			if (other.banco != null) {
				return false;
			}
		} else if (banco.getId() == null) {
			if (other.banco.getId() != null)
				return false;
		} else if (!banco.getId().equals(other.banco.getId())) 
			return false;
		
		// Field: numeroAgencia
		if (numeroAgencia == null) {
			if (other.numeroAgencia != null) {
				return false;
			}
		} else if (!numeroAgencia.equals(other.numeroAgencia))
			return false;
		
		// Field: digitoAgencia
		if (digitoAgencia == null) {
			if (other.digitoAgencia != null) {
				return false;
			}
		} else if (!digitoAgencia.equals(other.digitoAgencia))
			return false;
		
		// Field: endereco
		if (endereco == null) {
			if (other.endereco != null) {
				return false;
			}
		} else if (!endereco.equals(other.endereco))
			return false;
		
		// Field: deleted
		if (deleted == null) {
			if (other.deleted != null) {
				return false;
			}
		} else if (!deleted.equals(other.deleted))
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
