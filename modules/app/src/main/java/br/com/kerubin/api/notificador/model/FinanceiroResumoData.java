package br.com.kerubin.api.notificador.model;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FinanceiroResumoData {
	
	private ContasPagarHojeResumoCompleto contasPagarHojeResumoCompleto;
	private ContasReceberHojeResumoCompleto contasReceberHojeResumoCompleto;
	private List<CaixaMovimentoItem> caixaMovimentoItens;

}
