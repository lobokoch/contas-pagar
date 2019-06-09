/**********************************************************************************************
Code generated with MKL Plug-in version: 3.9.0
Code generated at time stamp: 2019-06-08T10:32:58.121
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.contapagar;

import org.springframework.format.annotation.DateTimeFormat;

public class ContaPagarListFilter {

	private java.util.List<String> descricao;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private java.time.LocalDate dataVencimentoFrom;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private java.time.LocalDate dataVencimentoTo;
	
	private Boolean dataPagamentoIsNotNull;
	
	private Boolean dataPagamentoIsNull;
	
	private java.util.List<String> agrupador;
	
	public java.util.List<String> getDescricao() {
		return descricao;
	}
	
	public void setDescricao(java.util.List<String> descricao) {
		this.descricao = descricao;
	}
	
	public java.time.LocalDate getDataVencimentoFrom() {
		return dataVencimentoFrom;
	}
	
	public void setDataVencimentoFrom(java.time.LocalDate dataVencimentoFrom) {
		this.dataVencimentoFrom = dataVencimentoFrom;
	}
	
	public java.time.LocalDate getDataVencimentoTo() {
		return dataVencimentoTo;
	}
	
	public void setDataVencimentoTo(java.time.LocalDate dataVencimentoTo) {
		this.dataVencimentoTo = dataVencimentoTo;
	}
	
	public Boolean isDataPagamentoIsNotNull() {
		return dataPagamentoIsNotNull != null && dataPagamentoIsNotNull;
	}
	
	public void setDataPagamentoIsNotNull(Boolean dataPagamentoIsNotNull) {
		this.dataPagamentoIsNotNull = dataPagamentoIsNotNull;
	}
	
	public Boolean isDataPagamentoIsNull() {
		return dataPagamentoIsNull != null && dataPagamentoIsNull;
	}
	
	public void setDataPagamentoIsNull(Boolean dataPagamentoIsNull) {
		this.dataPagamentoIsNull = dataPagamentoIsNull;
	}
	
	
	public java.util.List<String> getAgrupador() {
		return agrupador;
	}
	
	public void setAgrupador(java.util.List<String> agrupador) {
		this.agrupador = agrupador;
	}

}
