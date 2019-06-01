/**********************************************************************************************
Code generated with MKL Plug-in version: 3.5.1
Code generated at time stamp: 2019-06-01T15:26:20.750
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.contapagar;

import javax.validation.constraints.NotNull;
import br.com.kerubin.api.financeiro.contaspagar.entity.planoconta.PlanoContaLookupResult;
import javax.validation.constraints.NotBlank;
import br.com.kerubin.api.financeiro.contaspagar.FormaPagamento;
import br.com.kerubin.api.financeiro.contaspagar.entity.contabancaria.ContaBancariaLookupResult;
import br.com.kerubin.api.financeiro.contaspagar.entity.cartaocredito.CartaoCreditoLookupResult;
import br.com.kerubin.api.financeiro.contaspagar.entity.fornecedor.FornecedorLookupResult;

public class ContaPagar {

	private java.util.UUID id;
	
	@NotNull(message="'Plano de contas' é obrigatório.")
	private PlanoContaLookupResult planoContas;
	
	@NotBlank(message="'Descrição da conta' é obrigatório.")
	private String descricao;
	
	@NotNull(message="'Vencimento' é obrigatório.")
	private java.time.LocalDate dataVencimento;
	
	@NotNull(message="'Valor da conta' é obrigatório.")
	private java.math.BigDecimal valor;
	
	@NotNull(message="'Forma de pagamento' é obrigatório.")
	private FormaPagamento formaPagamento;
	
	private ContaBancariaLookupResult contaBancaria;
	
	private CartaoCreditoLookupResult cartaoCredito;
	
	private String outrosDescricao;
	
	private java.time.LocalDate dataPagamento;
	
	private java.math.BigDecimal valorDesconto;
	
	private java.math.BigDecimal valorMulta;
	
	private java.math.BigDecimal valorJuros;
	
	private java.math.BigDecimal valorAcrescimos;
	
	private java.math.BigDecimal valorPago;
	
	private FornecedorLookupResult fornecedor;
	
	private String numDocumento;
	
	private String observacoes;
	
	private String agrupador;
	
	private String createdBy;
	
	private java.time.LocalDateTime createdDate;
	
	private String lastModifiedBy;
	
	private java.time.LocalDateTime lastModifiedDate;
	
	public java.util.UUID getId() {
		return id;
	}
	
	public PlanoContaLookupResult getPlanoContas() {
		return planoContas;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public java.time.LocalDate getDataVencimento() {
		return dataVencimento;
	}
	
	public java.math.BigDecimal getValor() {
		return valor;
	}
	
	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}
	
	public ContaBancariaLookupResult getContaBancaria() {
		return contaBancaria;
	}
	
	public CartaoCreditoLookupResult getCartaoCredito() {
		return cartaoCredito;
	}
	
	public String getOutrosDescricao() {
		return outrosDescricao;
	}
	
	public java.time.LocalDate getDataPagamento() {
		return dataPagamento;
	}
	
	public java.math.BigDecimal getValorDesconto() {
		return valorDesconto;
	}
	
	public java.math.BigDecimal getValorMulta() {
		return valorMulta;
	}
	
	public java.math.BigDecimal getValorJuros() {
		return valorJuros;
	}
	
	public java.math.BigDecimal getValorAcrescimos() {
		return valorAcrescimos;
	}
	
	public java.math.BigDecimal getValorPago() {
		return valorPago;
	}
	
	public FornecedorLookupResult getFornecedor() {
		return fornecedor;
	}
	
	public String getNumDocumento() {
		return numDocumento;
	}
	
	public String getObservacoes() {
		return observacoes;
	}
	
	public String getAgrupador() {
		return agrupador;
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
	
	public void setPlanoContas(PlanoContaLookupResult planoContas) {
		this.planoContas = planoContas;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public void setDataVencimento(java.time.LocalDate dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	
	public void setValor(java.math.BigDecimal valor) {
		this.valor = valor;
	}
	
	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	
	public void setContaBancaria(ContaBancariaLookupResult contaBancaria) {
		this.contaBancaria = contaBancaria;
	}
	
	public void setCartaoCredito(CartaoCreditoLookupResult cartaoCredito) {
		this.cartaoCredito = cartaoCredito;
	}
	
	public void setOutrosDescricao(String outrosDescricao) {
		this.outrosDescricao = outrosDescricao;
	}
	
	public void setDataPagamento(java.time.LocalDate dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	
	public void setValorDesconto(java.math.BigDecimal valorDesconto) {
		this.valorDesconto = valorDesconto;
	}
	
	public void setValorMulta(java.math.BigDecimal valorMulta) {
		this.valorMulta = valorMulta;
	}
	
	public void setValorJuros(java.math.BigDecimal valorJuros) {
		this.valorJuros = valorJuros;
	}
	
	public void setValorAcrescimos(java.math.BigDecimal valorAcrescimos) {
		this.valorAcrescimos = valorAcrescimos;
	}
	
	public void setValorPago(java.math.BigDecimal valorPago) {
		this.valorPago = valorPago;
	}
	
	public void setFornecedor(FornecedorLookupResult fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}
	
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	
	public void setAgrupador(String agrupador) {
		this.agrupador = agrupador;
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
		ContaPagar other = (ContaPagar) obj;
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
