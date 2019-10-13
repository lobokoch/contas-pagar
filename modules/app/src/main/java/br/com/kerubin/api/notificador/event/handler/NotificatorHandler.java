package br.com.kerubin.api.notificador.event.handler;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.kerubin.api.notificador.contas.BillsNotifier;

@RestController
@RequestMapping("financeiro/contas_pagar/notificator")
public class NotificatorHandler {
	
	@Inject
	private BillsNotifier billsNotifier;
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PostMapping("/notifyUsersAboutTheBills")
	public void notifyUsersAboutTheBills() {
		billsNotifier.executeNotifyUsersAboutTheBills();
	}

}
