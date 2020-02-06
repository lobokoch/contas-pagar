package br.com.kerubin.api.financeiro.contaspagar.event.user;

import br.com.kerubin.api.messaging.core.DefaultDomainEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserAccountConfirmedEvent extends DefaultDomainEvent {
	
	public static final String USER_ACCOUNT_CONFIRMED_EVENT = "userAccountConfirmedEvent";
	
	private String username;
	private String tenant;
	private String tenantAccountType;

}
