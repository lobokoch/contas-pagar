/**********************************************************************************************
Code generated with MKL Plug-in version: 55.0.3
Copyright: Kerubin - kerubin.platform@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.contapagar;


import java.util.HashMap;
import java.util.Map;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;
import br.com.kerubin.api.financeiro.contaspagar.FormaPagamento;

		
@ApiModel(description = "Details about list filter of Contas a pagar")
public class ContaPagarListFilter {

	@ApiModelProperty(notes = "Descrição da conta", position = 2)
	private java.util.List<String> descricao;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@ApiModelProperty(notes = "Is between from Vencimento", position = 3)
	private java.time.LocalDate dataVencimentoFrom;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@ApiModelProperty(notes = "Is between to Vencimento", position = 3)
	private java.time.LocalDate dataVencimentoTo;
	
	@ApiModelProperty(notes = "Is between from Valor da conta", position = 4)
	private java.math.BigDecimal valorFrom;
	
	@ApiModelProperty(notes = "Is between to Valor da conta", position = 4)
	private java.math.BigDecimal valorTo;
	
	@ApiModelProperty(notes = "Forma de pagamento", position = 5)
	private FormaPagamento formaPagamento;
	
	@ApiModelProperty(notes = "Data pagamento is not null", position = 9)
	private Boolean dataPagamentoIsNotNull;
	
	@ApiModelProperty(notes = "Data pagamento is null", position = 9)
	private Boolean dataPagamentoIsNull;
	
	@ApiModelProperty(notes = "Id da conciliação bancária is not null", position = 17)
	private Boolean idConcBancariaIsNotNull;
	
	@ApiModelProperty(notes = "Histórico da conciliação bancária", position = 18)
	private java.util.List<String> histConcBancaria;
	
	@ApiModelProperty(notes = "Identificador para agrupamento da conta", position = 21)
	private java.util.List<String> agrupador;
	
	// Map field for developer customizing parameters.
	@ApiModelProperty(notes = "Campo tipo mapa (chave = valor) onde o desenvolvedor pode passar seus parâmetros personalizados no formato objeto JSON.", position = 9999)
	private Map<Object, Object> customParams = new HashMap<>();
	
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
	
	public java.math.BigDecimal getValorFrom() {
		return valorFrom;
	}
	
	public void setValorFrom(java.math.BigDecimal valorFrom) {
		this.valorFrom = valorFrom;
	}
	
	public java.math.BigDecimal getValorTo() {
		return valorTo;
	}
	
	public void setValorTo(java.math.BigDecimal valorTo) {
		this.valorTo = valorTo;
	}
	
	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}
			
	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	
	public Boolean isDataPagamentoIsNotNull() {
		return dataPagamentoIsNotNull != null && dataPagamentoIsNotNull;
	}
	
	public Boolean getDataPagamentoIsNotNull() {
		return dataPagamentoIsNotNull;
	}
	
	public void setDataPagamentoIsNotNull(Boolean dataPagamentoIsNotNull) {
		this.dataPagamentoIsNotNull = dataPagamentoIsNotNull;
	}
	
	public Boolean isDataPagamentoIsNull() {
		return dataPagamentoIsNull != null && dataPagamentoIsNull;
	}
	
	public Boolean getDataPagamentoIsNull() {
		return dataPagamentoIsNull;
	}
	
	public void setDataPagamentoIsNull(Boolean dataPagamentoIsNull) {
		this.dataPagamentoIsNull = dataPagamentoIsNull;
	}
	
	
	public Boolean isIdConcBancariaIsNotNull() {
		return idConcBancariaIsNotNull != null && idConcBancariaIsNotNull;
	}
	
	public Boolean getIdConcBancariaIsNotNull() {
		return idConcBancariaIsNotNull;
	}
			
	public void setIdConcBancariaIsNotNull(Boolean idConcBancariaIsNotNull) {
		this.idConcBancariaIsNotNull = idConcBancariaIsNotNull;
	}
	
	public java.util.List<String> getHistConcBancaria() {
		return histConcBancaria;
	}
	
	public void setHistConcBancaria(java.util.List<String> histConcBancaria) {
		this.histConcBancaria = histConcBancaria;
	}
	
	public java.util.List<String> getAgrupador() {
		return agrupador;
	}
	
	public void setAgrupador(java.util.List<String> agrupador) {
		this.agrupador = agrupador;
	}
	
	public Map<Object, Object> getCustomParams() {
		return customParams;
	}
	
	public void setCustomParams(Map<Object, Object> customParams) {
		this.customParams = customParams;
	}
	
}
