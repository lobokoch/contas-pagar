package br.com.kerubin.api.notificador.model.external.clientes;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParametrosAgendaDoDia {
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate data;
	
	private List<String> recursoEmails;

}
