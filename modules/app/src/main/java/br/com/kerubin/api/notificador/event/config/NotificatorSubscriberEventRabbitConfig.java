/**********************************************************************************************
Code generated with MKL Plug-in version: 7.0.0
Code generated at time stamp: 2019-07-15T23:24:41.979
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.notificador.event.config;

import java.text.MessageFormat;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;

import br.com.kerubin.api.messaging.core.DomainEntityEventsPublisher;
import br.com.kerubin.api.messaging.core.DomainEvent;
import br.com.kerubin.api.notificador.contas.NotificatorServiceConstants;
import br.com.kerubin.api.notificador.event.model.NotifyBillsForUsersEvent;

// @ComponentScan({"br.com.kerubin.api.messaging.core"})
// @Configuration
public class NotificatorSubscriberEventRabbitConfig {
	
	// @Bean
	public TopicExchange notificationTopic() {
		String topicName = MessageFormat.format("{0}_{1}_{2}_{3}", //
			DomainEvent.APPLICATION, //
			NotificatorServiceConstants.DOMAIN, //
			NotificatorServiceConstants.SERVICE, //
			DomainEntityEventsPublisher.TOPIC_PREFFIX);
		
		return new TopicExchange(topicName);
	}
	
	// @Bean
	public Queue notificatorQueue() {
		// This service queue name for subscribe to the entity owner exchange topic.
		// kerubin_notificator_service_notifyBillsForUsers_notification.bills
		String queueName = MessageFormat.format("{0}_{1}_{2}_{3}_{4}", //
			DomainEvent.APPLICATION, //
			NotificatorServiceConstants.DOMAIN, //
			NotificatorServiceConstants.SERVICE, //
			//BillsNotifier.EVENT_KEY, //
			NotifyBillsForUsersEvent.NOTIFY_BILLS_FOR_USERS);
		
		return new Queue(queueName, true);
	}
	
	// @Bean
	public Binding notificatorBinding(@Qualifier("notificatorTopic") TopicExchange topic, 
			@Qualifier("notificatorQueue") Queue queue) {
		
		String rountingKey = MessageFormat.format("{0}.{1}.{2}.{3}.{4}", //
				DomainEvent.APPLICATION, //
				NotificatorServiceConstants.DOMAIN, //
				NotificatorServiceConstants.SERVICE, //
				//BillsNotifier.EVENT_KEY, //
				NotifyBillsForUsersEvent.NOTIFY_BILLS_FOR_USERS);
		
		return BindingBuilder
				.bind(queue) //
				.to(topic) //
				.with(rountingKey);
	}

}
