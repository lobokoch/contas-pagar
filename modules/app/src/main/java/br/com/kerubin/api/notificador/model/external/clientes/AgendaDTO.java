package br.com.kerubin.api.notificador.model.external.clientes;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AgendaDTO {
	
	private List<CompromissoDTO> compromissos;
	private List<RecursoDTO> recursos;

}
