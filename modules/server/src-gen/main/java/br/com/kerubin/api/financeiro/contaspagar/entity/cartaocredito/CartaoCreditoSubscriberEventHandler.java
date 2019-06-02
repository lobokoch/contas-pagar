/**********************************************************************************************
Code generated with MKL Plug-in version: 3.5.1
Code generated at time stamp: 2019-06-02T06:23:49.436
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.cartaocredito;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.kerubin.api.cadastros.banco.entity.cartaocredito.CartaoCreditoEvent;

import br.com.kerubin.api.messaging.core.DomainEventEnvelope;

@Service
public class CartaoCreditoSubscriberEventHandler {
	
	private static final Logger log = LoggerFactory.getLogger(CartaoCreditoSubscriberEventHandler.class);
	
	@Autowired
	private CartaoCreditoRepository cartaoCreditoRepository;
	
	@Autowired
	private CartaoCreditoService cartaoCreditoService;
	
	@Autowired
	CartaoCreditoDTOConverter cartaoCreditoDTOConverter;
	
	@RabbitListener(queues = "#{cartaoCreditoQueue.name}")
	public void onReceiveEvent(DomainEventEnvelope<CartaoCreditoEvent> envelope) {
		try {
			switch (envelope.getPrimitive()) {
			case CartaoCreditoEvent.CARTAO_CREDITO_CREATED:
			case CartaoCreditoEvent.CARTAO_CREDITO_UPDATED:
			
				saveCartaoCredito(envelope.getPayload());
				break;
			
			case CartaoCreditoEvent.CARTAO_CREDITO_DELETED:
				deleteCartaoCredito(envelope.getPayload());
				break;
			
			default:
				log.warn("Unexpected entity event: {} for: {}.", envelope.getPrimitive(), "br.com.kerubin.api.cadastros.banco.entity.cartaocredito.CartaoCredito");
				break;
			}
		} catch(Exception e) {
			log.error("Error receiven event with envelope: " + envelope, e);
			throw new AmqpRejectAndDontRequeueException(e);
		}
	}
	
	private void saveCartaoCredito(CartaoCreditoEvent cartaoCreditoEvent) {
		saveCartaoCredito(cartaoCreditoEvent, false);
	}
	
	private void saveCartaoCredito(CartaoCreditoEvent cartaoCreditoEvent, boolean isDeleted) {
		CartaoCreditoEntity entity = buildCartaoCreditoEntity(cartaoCreditoEvent);
		Optional<CartaoCreditoEntity> optionalEntity = cartaoCreditoRepository.findById(cartaoCreditoEvent.getId());
		if (optionalEntity.isPresent()) {
			if (isDeleted) {
				entity = optionalEntity.get();
				entity.setDeleted(true);
			}
			cartaoCreditoService.update(entity.getId(), entity);
		}
		else {
			cartaoCreditoService.create(entity);
		}
	}
	
	private void deleteCartaoCredito(CartaoCreditoEvent cartaoCreditoEvent) {
		Optional<CartaoCreditoEntity> optionalEntity = cartaoCreditoRepository.findById(cartaoCreditoEvent.getId());
		if (optionalEntity.isPresent()) {
			try {
				cartaoCreditoService.delete(cartaoCreditoEvent.getId());
			} catch(DataIntegrityViolationException e) {
				saveCartaoCredito(cartaoCreditoEvent, true);
			}
		}
	}

	private CartaoCreditoEntity buildCartaoCreditoEntity(CartaoCreditoEvent cartaoCreditoEvent) {
		CartaoCreditoEntity entity = cartaoCreditoDTOConverter.convertDtoToEntity(cartaoCreditoEvent);
		return entity;
	}

}
