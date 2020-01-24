/**********************************************************************************************
Code generated with MKL Plug-in version: 55.0.3
Copyright: Kerubin - kerubin.platform@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.contapagar;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import br.com.kerubin.api.financeiro.contaspagar.entity.planoconta.PlanoContaLookupResult;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import br.com.kerubin.api.financeiro.contaspagar.FormaPagamento;
import br.com.kerubin.api.financeiro.contaspagar.entity.contabancaria.ContaBancariaLookupResult;
import br.com.kerubin.api.financeiro.contaspagar.entity.cartaocredito.CartaoCreditoLookupResult;
import br.com.kerubin.api.financeiro.contaspagar.entity.fornecedor.FornecedorLookupResult;


@ApiModel(description = "Details about Contas a pagar")
public class ContaPagar {

	@ApiModelProperty(notes = "Identificador único", position = 0)
	private java.util.UUID id;
	
	@NotNull(message="\"Plano de contas\" é obrigatório.")
	@ApiModelProperty(notes = "Plano de contas", required = true, position = 1)
	private PlanoContaLookupResult planoContas;
	
	@NotBlank(message="\"Descrição da conta\" é obrigatório.")
	@Size(max = 255, message = "\"Descrição da conta\" pode ter no máximo 255 caracteres.")
	@ApiModelProperty(notes = "Descrição da conta", required = true, position = 2)
	private String descricao;
	
	@NotNull(message="\"Vencimento\" é obrigatório.")
	@ApiModelProperty(notes = "Vencimento", required = true, position = 3)
	private java.time.LocalDate dataVencimento;
	
	@NotNull(message="\"Valor da conta\" é obrigatório.")
	@ApiModelProperty(notes = "Valor da conta", required = true, position = 4)
	private java.math.BigDecimal valor;
	
	@NotNull(message="\"Forma de pagamento\" é obrigatório.")
	@ApiModelProperty(notes = "Forma de pagamento", required = true, position = 5)
	private FormaPagamento formaPagamento;
	
	@ApiModelProperty(notes = "Dados da conta bancária", position = 6)
	private ContaBancariaLookupResult contaBancaria;
	
	@ApiModelProperty(notes = "Dados do cartão de crédito", position = 7)
	private CartaoCreditoLookupResult cartaoCredito;
	
	@Size(max = 255, message = "\"Dados complementares\" pode ter no máximo 255 caracteres.")
	@ApiModelProperty(notes = "Dados complementares", position = 8)
	private String outrosDescricao;
	
	@ApiModelProperty(notes = "Data pagamento", position = 9)
	private java.time.LocalDate dataPagamento;
	
	@ApiModelProperty(notes = "Descontos", position = 10)
	private java.math.BigDecimal valorDesconto;
	
	@ApiModelProperty(notes = "Multas", position = 11)
	private java.math.BigDecimal valorMulta;
	
	@ApiModelProperty(notes = "Juros mora", position = 12)
	private java.math.BigDecimal valorJuros;
	
	@ApiModelProperty(notes = "Acréscimos", position = 13)
	private java.math.BigDecimal valorAcrescimos;
	
	@ApiModelProperty(notes = "Valor pago", position = 14)
	private java.math.BigDecimal valorPago;
	
	@ApiModelProperty(notes = "Fornecedor", position = 15)
	private FornecedorLookupResult fornecedor;
	
	@Size(max = 255, message = "\"Documento\" pode ter no máximo 255 caracteres.")
	@ApiModelProperty(notes = "Documento", position = 16)
	private String numDocumento;
	
	@Size(max = 255, message = "\"Id da conciliação bancária\" pode ter no máximo 255 caracteres.")
	@ApiModelProperty(notes = "Id da conciliação bancária", position = 17)
	private String idConcBancaria;
	
	@Size(max = 255, message = "\"Histórico da conciliação bancária\" pode ter no máximo 255 caracteres.")
	@ApiModelProperty(notes = "Histórico da conciliação bancária", position = 18)
	private String histConcBancaria;
	
	@Size(max = 255, message = "\"Documento da conciliação bancária\" pode ter no máximo 255 caracteres.")
	@ApiModelProperty(notes = "Documento da conciliação bancária", position = 19)
	private String numDocConcBancaria;
	
	@Size(max = 1000, message = "\"Observações\" pode ter no máximo 1000 caracteres.")
	@ApiModelProperty(notes = "Observações", position = 20)
	private String observacoes;
	
	@Size(max = 255, message = "\"Identificador para agrupamento da conta\" pode ter no máximo 255 caracteres.")
	@ApiModelProperty(notes = "Identificador para agrupamento da conta", position = 21)
	private String agrupador;
	
	@Size(max = 255, message = "\"Criado por\" pode ter no máximo 255 caracteres.")
	@ApiModelProperty(notes = "Criado por", position = 22)
	private String createdBy;
	
	@ApiModelProperty(notes = "Data de criação", position = 23)
	private java.time.LocalDateTime createdDate;
	
	@Size(max = 255, message = "\"Alterado por\" pode ter no máximo 255 caracteres.")
	@ApiModelProperty(notes = "Alterado por", position = 24)
	private String lastModifiedBy;
	
	@ApiModelProperty(notes = "Data de alteração", position = 25)
	private java.time.LocalDateTime lastModifiedDate;
	
	
	public ContaPagar() {
		// Contructor for reflexion, injection, Jackson, QueryDSL, etc proposal.
	}
	
	
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
	
	public String getIdConcBancaria() {
		return idConcBancaria;
	}
	
	public String getHistConcBancaria() {
		return histConcBancaria;
	}
	
	public String getNumDocConcBancaria() {
		return numDocConcBancaria;
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
	
	public void setIdConcBancaria(String idConcBancaria) {
		this.idConcBancaria = idConcBancaria;
	}
	
	public void setHistConcBancaria(String histConcBancaria) {
		this.histConcBancaria = histConcBancaria;
	}
	
	public void setNumDocConcBancaria(String numDocConcBancaria) {
		this.numDocConcBancaria = numDocConcBancaria;
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
