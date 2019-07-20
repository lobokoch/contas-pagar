package br.com.kerubin.api.financeiro.contaspagar.model;

import java.math.BigDecimal;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ContasPagarHojeResumoCompleto {
	
	private ContasPagarSituacaoDoAnoSum contasPagarSituacaoDoAnoSum;
	private List<ContasPagarHojeResumo> contasPagarHojeResumo;
	private BigDecimal contasPagarHojeResumoSum;
	
	public ContasPagarHojeResumoCompleto() {
		
	}

}
