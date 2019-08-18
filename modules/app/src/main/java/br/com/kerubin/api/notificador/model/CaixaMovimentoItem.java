package br.com.kerubin.api.notificador.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static br.com.kerubin.api.servicecore.util.CoreUtils.*;

@Getter
@Setter
@ToString
public class CaixaMovimentoItem {
	
	private String descricao;
	private BigDecimal credito;
	private BigDecimal dedito;
	private BigDecimal saldo;
	
	public CaixaMovimentoItem() {
		
	}
	
	public CaixaMovimentoItem(String descricao, BigDecimal credito, BigDecimal dedito, BigDecimal saldo) {
		this();
		this.descricao = descricao;
		this.credito = credito;
		this.dedito = dedito;
		this.saldo = saldo;
	}
	
	public CaixaMovimentoItem(String descricao, BigDecimal credito, BigDecimal dedito) {
		this();
		this.descricao = descricao;
		this.credito = credito;
		this.dedito = dedito;
		
		BigDecimal saldo = getSafeValue(credito).subtract(getSafeValue(dedito));
		this.saldo = saldo;
	}
	
}
