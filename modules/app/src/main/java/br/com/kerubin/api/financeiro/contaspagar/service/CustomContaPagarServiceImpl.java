package br.com.kerubin.api.financeiro.contaspagar.service;

import java.util.UUID;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import br.com.kerubin.api.database.core.ServiceContext;
import br.com.kerubin.api.financeiro.contaspagar.FinanceiroContasPagarConstants;
import br.com.kerubin.api.financeiro.contaspagar.entity.contapagar.ContaPagarEntity;
import br.com.kerubin.api.financeiro.contaspagar.entity.contapagar.ContaPagarServiceImpl;
import br.com.kerubin.api.financeiro.contaspagar.event.ContaPagarEvent;
import br.com.kerubin.api.financeiro.contaspagar.repository.ContaPagarRepository;
import br.com.kerubin.api.messaging.core.DomainEntityEventsPublisher;
import br.com.kerubin.api.messaging.core.DomainEvent;
import br.com.kerubin.api.messaging.core.DomainEventEnvelope;
import br.com.kerubin.api.messaging.core.DomainEventEnvelopeBuilder;

@Primary
@Service
public class CustomContaPagarServiceImpl extends ContaPagarServiceImpl {
	
	private static final String ENTITY_KEY = "entity.ContaPagar";
	
	@Autowired
	private DomainEntityEventsPublisher publisher;
	
	@Inject
	private ContaPagarRepository contaPagarRepository;
	
	
	
	@Override
	public void actionBaixarContaComUmClique(UUID id) {
		// Baixa a conta
		super.actionBaixarContaComUmClique(id);
		
		// Busca a conta atualziada
		ContaPagarEntity entity = contaPagarRepository.findById(id).orElse(null);
		
		// Publica a mensagem de conta paga
		if (entity != null && entity.getDataPagamento() != null) {
			publishEvent(entity, ContaPagarEvent.CONTA_PAGAR_CONTAPAGA);
			
		}
		
	}
	
	protected void publishEvent(ContaPagarEntity entity, String eventName) {
		DomainEvent event = new ContaPagarEvent(entity.getId(), 
			entity.getPlanoContas() != null ? entity.getPlanoContas().getId() : null, 
			entity.getDescricao(), 
			entity.getFormaPagamento(), 
			entity.getContaBancaria() != null ? entity.getContaBancaria().getId() : null, 
			entity.getCartaoCredito() != null ? entity.getCartaoCredito().getId() : null, 
			entity.getDataPagamento(), 
			entity.getValorPago(), 
			entity.getFornecedor() != null ? entity.getFornecedor().getId() : null, 
			entity.getNumDocumento());
		
		DomainEventEnvelope<DomainEvent> envelope = DomainEventEnvelopeBuilder
				.getBuilder(eventName, event)
				.domain(FinanceiroContasPagarConstants.DOMAIN)
				.service(FinanceiroContasPagarConstants.SERVICE)
				.key(ENTITY_KEY)
				.tenant(ServiceContext.getTenant())
				.user(ServiceContext.getUser())
				.build();
		
		publisher.publish(envelope);
	}

}
