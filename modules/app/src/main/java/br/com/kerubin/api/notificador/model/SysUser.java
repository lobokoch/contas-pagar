/**********************************************************************************************
Code generated with MKL Plug-in version: 7.0.0
Code generated at time stamp: 2019-07-13T11:14:05.586
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.notificador.model;

public class SysUser {

	private java.util.UUID id;
	
	private String name;
	
	private String email;
	
	private String password;
	
	private String confirmPassword;
	
	private Boolean active = false;
	
	private Boolean administrator = false;
	
	private AccountType accountType;
	
	private TenantLookupResult tenant;
	
	private java.time.LocalDateTime activationDate;
	
	private Boolean confirmed = false;
	
	private java.time.LocalDateTime confirmationDate;
	
	private String confirmationId;
	
	private String createdBy;
	
	private java.time.LocalDateTime createdDate;
	
	private String lastModifiedBy;
	
	private java.time.LocalDateTime lastModifiedDate;
	
	
	public SysUser() {
		// Contructor for reflexion, injection, Jackson, QueryDSL, etc proposal.
	}
	
	
	public java.util.UUID getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getConfirmPassword() {
		return confirmPassword;
	}
	
	public Boolean getActive() {
		return active;
	}
	
	public Boolean getAdministrator() {
		return administrator;
	}
	
	public AccountType getAccountType() {
		return accountType;
	}
	
	public TenantLookupResult getTenant() {
		return tenant;
	}
	
	public java.time.LocalDateTime getActivationDate() {
		return activationDate;
	}
	
	public Boolean getConfirmed() {
		return confirmed;
	}
	
	public java.time.LocalDateTime getConfirmationDate() {
		return confirmationDate;
	}
	
	public String getConfirmationId() {
		return confirmationId;
	}
	
	public String getCreatedBy() {
		return createdBy;
	}
	
	public java.time.LocalDateTime getCreatedDate() {
		return createdDate;
	}
	
	public String getLastModifiedBy() {
		return lastModifiedBy;
	}
	
	public java.time.LocalDateTime getLastModifiedDate() {
		return lastModifiedDate;
	}
	
	public void setId(java.util.UUID id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	public void setActive(Boolean active) {
		this.active = active;
	}
	
	public void setAdministrator(Boolean administrator) {
		this.administrator = administrator;
	}
	
	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}
	
	public void setTenant(TenantLookupResult tenant) {
		this.tenant = tenant;
	}
	
	public void setActivationDate(java.time.LocalDateTime activationDate) {
		this.activationDate = activationDate;
	}
	
	public void setConfirmed(Boolean confirmed) {
		this.confirmed = confirmed;
	}
	
	public void setConfirmationDate(java.time.LocalDateTime confirmationDate) {
		this.confirmationDate = confirmationDate;
	}
	
	public void setConfirmationId(String confirmationId) {
		this.confirmationId = confirmationId;
	}
	
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	public void setCreatedDate(java.time.LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}
	
	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}
	
	public void setLastModifiedDate(java.time.LocalDateTime lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SysUser other = (SysUser) obj;
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
