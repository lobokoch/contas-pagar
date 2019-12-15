package br.com.kerubin.api.financeiro.contaspagar.conciliacaobancaria;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlanoContaDTO {
	
	private java.util.UUID id;
	private String codigo;
	private String descricao;	

}
