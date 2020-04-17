package br.com.kerubin.api.financeiro.contaspagar.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ContaPagarMultipleResumo {
	
	private BigDecimal valorTotalPago;
	private LocalDate dataUltimoPagamento;
	private boolean temResumo = false;

}
