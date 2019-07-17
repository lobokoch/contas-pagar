package br.com.kerubin.api.notificador.event.model;

import java.util.List;

import br.com.kerubin.api.messaging.core.DomainEvent;
import br.com.kerubin.api.notificador.model.UserForNotification;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NotifyBillsForUsersEvent implements DomainEvent {
	
	public static final String NOTIFY_BILLS_FOR_USERS = "notifyBillsForUsers";
	
	
	private List<UserForNotification> usersForNotification;
	
	public NotifyBillsForUsersEvent() {
		
	}
	
	public NotifyBillsForUsersEvent(List<UserForNotification> usersForNotification) {
		this.usersForNotification = usersForNotification;
	}

}
