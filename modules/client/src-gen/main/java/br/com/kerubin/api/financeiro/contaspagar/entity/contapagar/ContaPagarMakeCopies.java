/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-13T08:12:17.669
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.contapagar;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Details about Contas a pagar")
public class ContaPagarMakeCopies {
	
	@NotNull(message="'Id' é obrigatório.")
	@ApiModelProperty(notes = "Identificador único", required = true, position = 0)
	private java.util.UUID id;
	
	@Min(value = 1, message = "A quantidade de cópias não pode ser menor que 1.")
	@Max(value = 60, message = "A quantidade de cópias não pode ser maior que 60.")
	@ApiModelProperty(notes = "Número de cópias", required = true, position = 1)
	private Long numberOfCopies;
	
	@Min(value = 1, message = "O intervalo não pode ser menor que 1.")
	@Max(value = 1000, message = "O intervalo não pode ser maior que 1000.")
	@ApiModelProperty(notes = "Campo de referência para intervalo", required = true, position = 2)
	private Long referenceFieldInterval;
	
	@NotBlank(message = "O campo 'Agrupador' deve ser informado.")
	@ApiModelProperty(notes = "Identificador para agrupamento da conta", required = true, position = 3)
	private String agrupador;;
	
	public ContaPagarMakeCopies() {
		// Contructor for reflexion, injection, Jackson, QueryDSL, etc proposal.
	}
	
	public java.util.UUID getId() {
		return id;
	}
	
	public Long getNumberOfCopies() {
		return numberOfCopies;
	}
	
	public Long getReferenceFieldInterval() {
		return referenceFieldInterval;
	}
	
	public String getAgrupador() {
		return agrupador;
	}
	
	public void setId(java.util.UUID id) {
		this.id = id;
	}
	
	public void setNumberOfCopies(Long numberOfCopies) {
		this.numberOfCopies = numberOfCopies;
	}
	
	public void setReferenceFieldInterval(Long referenceFieldInterval) {
		this.referenceFieldInterval = referenceFieldInterval;
	}
	
	public void setAgrupador(String agrupador) {
		this.agrupador = agrupador;
	}

}
