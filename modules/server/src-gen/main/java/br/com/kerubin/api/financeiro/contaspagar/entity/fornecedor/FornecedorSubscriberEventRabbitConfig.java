/**********************************************************************************************
Code generated with MKL Plug-in version: 3.5.1
Code generated at time stamp: 2019-06-02T06:23:49.436
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.fornecedor;

import java.text.MessageFormat;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import br.com.kerubin.api.messaging.core.DomainEntityEventsPublisher;
import br.com.kerubin.api.messaging.core.DomainEvent;

import br.com.kerubin.api.cadastros.fornecedor.CadastrosFornecedorConstants;

@ComponentScan({"br.com.kerubin.api.messaging.core"})
@Configuration
public class FornecedorSubscriberEventRabbitConfig {
	
	private static final String ENTITY_NAME = "Fornecedor";
	private static final String ENTITY_KEY = "entity";
	
	@Bean
	public TopicExchange fornecedorTopic() {
		String topicName = MessageFormat.format("{0}_{1}_{2}_{3}", 
			DomainEvent.APPLICATION, CadastrosFornecedorConstants.DOMAIN, 
			CadastrosFornecedorConstants.SERVICE, DomainEntityEventsPublisher.TOPIC_PREFFIX);
		
		return new TopicExchange(topicName);
	}
	
	@Bean
	public Queue fornecedorQueue() {
		String queueName = MessageFormat.format("{0}_{1}_{2}_{3}_{4}", 
			DomainEvent.APPLICATION, CadastrosFornecedorConstants.DOMAIN, 
			CadastrosFornecedorConstants.SERVICE, ENTITY_KEY, ENTITY_NAME);
		
		return new Queue(queueName, true);
	}
	
	@Bean
	public Binding fornecedorBinding(@Qualifier("fornecedorTopic") TopicExchange topic, 
			@Qualifier("fornecedorQueue") Queue queue) {
		
		String rountingKey = MessageFormat.format("{0}.{1}.{2}.{3}.{4}", 
				DomainEvent.APPLICATION, CadastrosFornecedorConstants.DOMAIN, 
				CadastrosFornecedorConstants.SERVICE, ENTITY_KEY, ENTITY_NAME);
		
		return BindingBuilder
				.bind(queue)
				.to(topic)
				.with(rountingKey);
	}

}
