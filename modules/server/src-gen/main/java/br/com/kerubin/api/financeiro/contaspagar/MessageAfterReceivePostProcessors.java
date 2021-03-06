/**********************************************************************************************
Code generated by MKL Plug-in
Copyright: Kerubin - kerubin.platform@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar;

import static br.com.kerubin.api.messaging.utils.Utils.isEmpty;
import static br.com.kerubin.api.messaging.utils.Utils.isNotEmpty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;

import br.com.kerubin.api.database.core.ServiceContext;

import static br.com.kerubin.api.messaging.constants.MessagingConstants.HEADER_TENANT;
import static br.com.kerubin.api.messaging.constants.MessagingConstants.HEADER_USER;
import static br.com.kerubin.api.messaging.constants.MessagingConstants.HEADER_TENANT_ACCOUNT_TYPE;

public class MessageAfterReceivePostProcessors implements MessagePostProcessor {
	
	private static final Logger log = LoggerFactory.getLogger(MessageAfterReceivePostProcessors.class);
		
	@Override
	public Message postProcessMessage(Message message) throws AmqpException {
		log.info("Receiving message from broker RabbitMQ, message data: {}", message);
		
		Object tenant = message.getMessageProperties().getHeaders().get(HEADER_TENANT);
		Object user = message.getMessageProperties().getHeaders().get(HEADER_USER);
		Object tenantAccountType = message.getMessageProperties().getHeaders().get(HEADER_TENANT_ACCOUNT_TYPE);
		
		if (isEmpty(tenant) || isEmpty(user)) {
			log.error("Empty or null tenant/user received from broker in message header tenant: {}, user: {}, message: ", tenant, user, message);
			
			throw new IllegalStateException("Empty or null tenant/user received from broker in message header tenant: " + tenant + ", user: " + user);
		}
		
		ServiceContext.setTenant(tenant.toString());
		ServiceContext.setUser(user.toString());
		
		if (isNotEmpty(tenantAccountType)) {
			ServiceContext.setTenantAccountType(tenantAccountType.toString());
		}
		else {
			log.error("Empty or null tenantAccountType received from broker in message header tenant: {}, user: {}, message: ", tenant, user, message);
		}
		
		ServiceContext.setDomain(FinanceiroContasPagarConstants.DOMAIN);
		ServiceContext.setService(FinanceiroContasPagarConstants.SERVICE);
		
		return message;
	}

}

