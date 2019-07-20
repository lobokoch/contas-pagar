package br.com.kerubin.api.notificador.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ContasReceberHojeResumo {
	
	private UUID id;
	private String descricao;
	private LocalDate dataVencimento;
	private Long diasEmAtraso;
	private BigDecimal valor;
	
	public ContasReceberHojeResumo() {
		
	}

}
