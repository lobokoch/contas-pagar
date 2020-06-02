package br.com.kerubin.api.notificador.model.external.clientes;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecursoDTO {
	
	private UUID id;
	private String nome;
	private String email;
	private Long compromissosCount;

}
