package br.com.kerubin.api.notificador.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserForNotification {
	
	private String name;
	private String email;
	private String tenant;
	
	public UserForNotification() {
		
	}
	
	public UserForNotification(String name, String email, String tenant) {
		this.name = name;
		this.email = email;
		this.tenant = tenant;
	}

}
