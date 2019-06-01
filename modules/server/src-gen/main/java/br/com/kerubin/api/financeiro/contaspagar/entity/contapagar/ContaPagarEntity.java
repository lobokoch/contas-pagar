/**********************************************************************************************
Code generated with MKL Plug-in version: 3.5.1
Code generated at time stamp: 2019-06-01T15:26:20.750
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.contapagar;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import br.com.kerubin.api.database.entity.AuditingEntity;
import javax.persistence.GeneratedValue;
import org.hibernate.annotations.GenericGenerator;
import br.com.kerubin.api.financeiro.contaspagar.entity.planoconta.PlanoContaEntity;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import br.com.kerubin.api.financeiro.contaspagar.FormaPagamento;
import br.com.kerubin.api.financeiro.contaspagar.entity.contabancaria.ContaBancariaEntity;
import br.com.kerubin.api.financeiro.contaspagar.entity.cartaocredito.CartaoCreditoEntity;
import br.com.kerubin.api.financeiro.contaspagar.entity.fornecedor.FornecedorEntity;

@Entity
@Table(name = "conta_pagar")
public class ContaPagarEntity extends AuditingEntity {

	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Id
	@Column(name="id")
	private java.util.UUID id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "plano_contas")
	private PlanoContaEntity planoContas;
	
	@Column(name="descricao")
	private String descricao;
	
	@Column(name="data_vencimento")
	private java.time.LocalDate dataVencimento;
	
	@Column(name="valor")
	private java.math.BigDecimal valor;
	
	@Enumerated(EnumType.STRING)
	@Column(name="forma_pagamento")
	private FormaPagamento formaPagamento;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "conta_bancaria")
	private ContaBancariaEntity contaBancaria;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cartao_credito")
	private CartaoCreditoEntity cartaoCredito;
	
	@Column(name="outros_descricao")
	private String outrosDescricao;
	
	@Column(name="data_pagamento")
	private java.time.LocalDate dataPagamento;
	
	@Column(name="valor_desconto")
	private java.math.BigDecimal valorDesconto;
	
	@Column(name="valor_multa")
	private java.math.BigDecimal valorMulta;
	
	@Column(name="valor_juros")
	private java.math.BigDecimal valorJuros;
	
	@Column(name="valor_acrescimos")
	private java.math.BigDecimal valorAcrescimos;
	
	@Column(name="valor_pago")
	private java.math.BigDecimal valorPago;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fornecedor")
	private FornecedorEntity fornecedor;
	
	@Column(name="num_documento")
	private String numDocumento;
	
	@Column(name="observacoes")
	private String observacoes;
	
	@Column(name="agrupador")
	private String agrupador;
	
	public java.util.UUID getId() {
		return id;
	}
	
	public PlanoContaEntity getPlanoContas() {
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
	
	public ContaBancariaEntity getContaBancaria() {
		return contaBancaria;
	}
	
	public CartaoCreditoEntity getCartaoCredito() {
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
	
	public FornecedorEntity getFornecedor() {
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
	
	public void setId(java.util.UUID id) {
		this.id = id;
	}
	
	public void setPlanoContas(PlanoContaEntity planoContas) {
		this.planoContas = planoContas;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao != null ? descricao.trim() : descricao; // Chamadas REST fazem trim.
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
	
	public void setContaBancaria(ContaBancariaEntity contaBancaria) {
		this.contaBancaria = contaBancaria;
	}
	
	public void setCartaoCredito(CartaoCreditoEntity cartaoCredito) {
		this.cartaoCredito = cartaoCredito;
	}
	
	public void setOutrosDescricao(String outrosDescricao) {
		this.outrosDescricao = outrosDescricao != null ? outrosDescricao.trim() : outrosDescricao; // Chamadas REST fazem trim.
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
	
	public void setFornecedor(FornecedorEntity fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento != null ? numDocumento.trim() : numDocumento; // Chamadas REST fazem trim.
	}
	
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes != null ? observacoes.trim() : observacoes; // Chamadas REST fazem trim.
	}
	
	public void setAgrupador(String agrupador) {
		this.agrupador = agrupador != null ? agrupador.trim() : agrupador; // Chamadas REST fazem trim.
	}
	
	public void assign(ContaPagarEntity source) {
		if (source != null) {
			this.setId(source.getId());
			this.setPlanoContas(source.getPlanoContas());
			this.setDescricao(source.getDescricao());
			this.setDataVencimento(source.getDataVencimento());
			this.setValor(source.getValor());
			this.setFormaPagamento(source.getFormaPagamento());
			this.setContaBancaria(source.getContaBancaria());
			this.setCartaoCredito(source.getCartaoCredito());
			this.setOutrosDescricao(source.getOutrosDescricao());
			this.setDataPagamento(source.getDataPagamento());
			this.setValorDesconto(source.getValorDesconto());
			this.setValorMulta(source.getValorMulta());
			this.setValorJuros(source.getValorJuros());
			this.setValorAcrescimos(source.getValorAcrescimos());
			this.setValorPago(source.getValorPago());
			this.setFornecedor(source.getFornecedor());
			this.setNumDocumento(source.getNumDocumento());
			this.setObservacoes(source.getObservacoes());
			this.setAgrupador(source.getAgrupador());
			this.setCreatedBy(source.getCreatedBy());
			this.setCreatedDate(source.getCreatedDate());
			this.setLastModifiedBy(source.getLastModifiedBy());
			this.setLastModifiedDate(source.getLastModifiedDate());
		}
	}
	
	public ContaPagarEntity clone() {
		ContaPagarEntity theClone = new ContaPagarEntity();
		theClone.setId(this.getId());
		theClone.setPlanoContas(this.getPlanoContas());
		theClone.setDescricao(this.getDescricao());
		theClone.setDataVencimento(this.getDataVencimento());
		theClone.setValor(this.getValor());
		theClone.setFormaPagamento(this.getFormaPagamento());
		theClone.setContaBancaria(this.getContaBancaria());
		theClone.setCartaoCredito(this.getCartaoCredito());
		theClone.setOutrosDescricao(this.getOutrosDescricao());
		theClone.setDataPagamento(this.getDataPagamento());
		theClone.setValorDesconto(this.getValorDesconto());
		theClone.setValorMulta(this.getValorMulta());
		theClone.setValorJuros(this.getValorJuros());
		theClone.setValorAcrescimos(this.getValorAcrescimos());
		theClone.setValorPago(this.getValorPago());
		theClone.setFornecedor(this.getFornecedor());
		theClone.setNumDocumento(this.getNumDocumento());
		theClone.setObservacoes(this.getObservacoes());
		theClone.setAgrupador(this.getAgrupador());
		theClone.setCreatedBy(this.getCreatedBy());
		theClone.setCreatedDate(this.getCreatedDate());
		theClone.setLastModifiedBy(this.getLastModifiedBy());
		theClone.setLastModifiedDate(this.getLastModifiedDate());
		
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
		ContaPagarEntity other = (ContaPagarEntity) obj;
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
