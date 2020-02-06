package br.com.kerubin.api.financeiro.contaspagar.event.user;

import br.com.kerubin.api.messaging.core.DomainMessage;

public interface SecurityAuthorizationEventHandler {

	void doHandleEvent(DomainMessage message);

}
