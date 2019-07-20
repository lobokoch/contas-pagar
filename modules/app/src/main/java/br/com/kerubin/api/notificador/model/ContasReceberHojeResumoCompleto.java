package br.com.kerubin.api.notificador.model;

import java.math.BigDecimal;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ContasReceberHojeResumoCompleto {
	
	private ContasReceberSituacaoDoAnoSum contasReceberSituacaoDoAnoSum;
	private List<ContasReceberHojeResumo> contasReceberHojeResumo;
	private BigDecimal contasReceberHojeResumoSum;
	
	public ContasReceberHojeResumoCompleto() {
		
	}

}
