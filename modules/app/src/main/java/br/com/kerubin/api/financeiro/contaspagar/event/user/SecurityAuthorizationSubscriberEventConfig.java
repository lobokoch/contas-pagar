
package br.com.kerubin.api.financeiro.contaspagar.event.user;

import javax.inject.Inject;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import br.com.kerubin.api.financeiro.contaspagar.messaging.FinanceiroContasPagarEventConfig;
import br.com.kerubin.api.messaging.utils.DomainEventUtils;

@ComponentScan({"br.com.kerubin.api.messaging.core"})
@Configuration
public class SecurityAuthorizationSubscriberEventConfig {
	
	@Inject
	@Qualifier(FinanceiroContasPagarEventConfig.FINANCEIRO_CONTAS_PAGAR_QUEUE)
	private Queue financeiroContasPagarQueue;
	
	@Bean
	public TopicExchange securityAuthorizationTopic() {
		// Listening to the Security Authorization Topic published messages.
		String topicName = DomainEventUtils.mountTopicName(
				SecurityAuthorizationConstants.DOMAIN, 
				SecurityAuthorizationConstants.SERVICE);
		
		return new TopicExchange(topicName);
	}
	
	@Bean
	public Binding securityAuthorizationBinding(@Qualifier("securityAuthorizationTopic") TopicExchange securityAuthorizationTopic
			) {
		
		String rountingKey = DomainEventUtils.mountRoutingKey(
				SecurityAuthorizationConstants.DOMAIN, //
				SecurityAuthorizationConstants.SERVICE, //
				UserAccountConfirmedEvent.USER_ACCOUNT_CONFIRMED_EVENT);
		
		return BindingBuilder //
				.bind(financeiroContasPagarQueue) //
				.to(securityAuthorizationTopic) //
				.with(rountingKey);
	}
	
	

}
