package br.com.kerubin.api.notificador.event.handler;

import java.util.UUID;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.kerubin.api.notificador.contas.BillsNotifier;
import br.com.kerubin.api.notificador.model.NotifyUsersAboutTheBillsAsyncResult;

@RestController
@RequestMapping("financeiro/contas_pagar/notificator")
public class NotificatorHandler {
	
	@Inject
	private BillsNotifier billsNotifier;
	
	//@ResponseStatus(HttpStatus.NO_CONTENT)
	@PostMapping("/notifyUsersAboutTheBills")
	public ResponseEntity<NotifyUsersAboutTheBillsAsyncResult> notifyUsersAboutTheBills() {
		UUID ticket = billsNotifier.executeNotifyUsersAboutTheBills();
		NotifyUsersAboutTheBillsAsyncResult result = NotifyUsersAboutTheBillsAsyncResult.builder().ticket(ticket).build();
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}

}
