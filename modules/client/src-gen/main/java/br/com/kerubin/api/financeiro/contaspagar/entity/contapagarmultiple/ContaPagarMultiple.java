/**********************************************************************************************
Code generated by MKL Plug-in
Copyright: Kerubin - kerubin.platform@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.contapagarmultiple;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import br.com.kerubin.api.financeiro.contaspagar.entity.fornecedor.FornecedorLookupResult;
import br.com.kerubin.api.financeiro.contaspagar.entity.planoconta.PlanoContaLookupResult;
import br.com.kerubin.api.financeiro.contaspagar.FormaPagamento;
import br.com.kerubin.api.financeiro.contaspagar.entity.contabancaria.ContaBancariaLookupResult;
import br.com.kerubin.api.financeiro.contaspagar.entity.cartaocredito.CartaoCreditoLookupResult;
import br.com.kerubin.api.financeiro.contaspagar.entity.contapagar.ContaPagarLookupResult;


@ApiModel(description = "Details about Registros de pagamento")
public class ContaPagarMultiple {

	@ApiModelProperty(notes = "Identificador único", position = 0)
	private java.util.UUID id;
	
	@NotNull(message="\"Data\" é obrigatório.")
	@PastOrPresent(message="A data de pagamento não deve ser uma data futura.")
	@ApiModelProperty(notes = "Data", required = true, position = 1)
	private java.time.LocalDate dataPagamento;
	
	@NotNull(message="\"Valor\" é obrigatório.")
	@Positive(message="O valor pago deve ser maior do zero.")
	@ApiModelProperty(notes = "Valor", required = true, position = 2)
	private java.math.BigDecimal valorPago;
	
	@NotBlank(message="\"Descrição\" é obrigatório.")
	@Size(max = 255, message = "\"Descrição\" pode ter no máximo 255 caracteres.")
	@ApiModelProperty(notes = "Descrição", required = true, position = 3)
	private String descricao;
	
	@ApiModelProperty(notes = "Fornecedor", position = 4)
	private FornecedorLookupResult fornecedor;
	
	@ApiModelProperty(notes = "Mostrar mais opções", position = 5)
	private Boolean maisOpcoes = false;
	
	@NotNull(message="\"Plano de contas\" é obrigatório.")
	@ApiModelProperty(notes = "Plano de contas", required = true, position = 6)
	private PlanoContaLookupResult planoContas;
	
	@NotNull(message="\"Forma de pagamento\" é obrigatório.")
	@ApiModelProperty(notes = "Forma de pagamento", required = true, position = 7)
	private FormaPagamento formaPagamento;
	
	@ApiModelProperty(notes = "Conta bancária", position = 8)
	private ContaBancariaLookupResult contaBancaria;
	
	@ApiModelProperty(notes = "Cartão de crédito", position = 9)
	private CartaoCreditoLookupResult cartaoCredito;
	
	@Size(max = 255, message = "\"Dados complementares\" pode ter no máximo 255 caracteres.")
	@ApiModelProperty(notes = "Dados complementares", position = 10)
	private String outrosDescricao;
	
	@NotNull(message="\"Conta a pagar pai\" é obrigatório.")
	@ApiModelProperty(notes = "Conta a pagar pai", required = true, position = 11)
	private ContaPagarLookupResult contaPagar;
	
	@Size(max = 255, message = "\"Id da conciliação bancária\" pode ter no máximo 255 caracteres.")
	@ApiModelProperty(notes = "Id da conciliação bancária", position = 12)
	private String idConcBancaria;
	
	@Size(max = 255, message = "\"Histórico da conciliação bancária\" pode ter no máximo 255 caracteres.")
	@ApiModelProperty(notes = "Histórico da conciliação bancária", position = 13)
	private String histConcBancaria;
	
	@Size(max = 255, message = "\"Documento da conciliação bancária\" pode ter no máximo 255 caracteres.")
	@ApiModelProperty(notes = "Documento da conciliação bancária", position = 14)
	private String numDocConcBancaria;
	
	@Size(max = 255, message = "\"Criado por\" pode ter no máximo 255 caracteres.")
	@ApiModelProperty(notes = "Criado por", position = 15)
	private String createdBy;
	
	@ApiModelProperty(notes = "Data de criação", position = 16)
	private java.time.LocalDateTime createdDate;
	
	@Size(max = 255, message = "\"Alterado por\" pode ter no máximo 255 caracteres.")
	@ApiModelProperty(notes = "Alterado por", position = 17)
	private String lastModifiedBy;
	
	@ApiModelProperty(notes = "Data de alteração", position = 18)
	private java.time.LocalDateTime lastModifiedDate;
	
	
	public ContaPagarMultiple() {
		// Contructor for reflexion, injection, Jackson, QueryDSL, etc proposal.
	}
	
	
	public java.util.UUID getId() {
		return id;
	}
	
	public java.time.LocalDate getDataPagamento() {
		return dataPagamento;
	}
	
	public java.math.BigDecimal getValorPago() {
		return valorPago;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public FornecedorLookupResult getFornecedor() {
		return fornecedor;
	}
	
	public Boolean getMaisOpcoes() {
		return maisOpcoes;
	}
	
	public PlanoContaLookupResult getPlanoContas() {
		return planoContas;
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
	
	public ContaPagarLookupResult getContaPagar() {
		return contaPagar;
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
	
	public void setDataPagamento(java.time.LocalDate dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	
	public void setValorPago(java.math.BigDecimal valorPago) {
		this.valorPago = valorPago;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public void setFornecedor(FornecedorLookupResult fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	public void setMaisOpcoes(Boolean maisOpcoes) {
		this.maisOpcoes = maisOpcoes;
	}
	
	public void setPlanoContas(PlanoContaLookupResult planoContas) {
		this.planoContas = planoContas;
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
	
	public void setContaPagar(ContaPagarLookupResult contaPagar) {
		this.contaPagar = contaPagar;
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
		ContaPagarMultiple other = (ContaPagarMultiple) obj;
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