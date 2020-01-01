package br.com.kerubin.api.notificador.model;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class NotifyUsersAboutTheBillsAsyncResult {
	
	private UUID ticket;

}
