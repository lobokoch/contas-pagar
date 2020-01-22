/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-22T06:59:30.300
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.banco;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.kerubin.api.cadastros.banco.entity.banco.BancoEvent;

import br.com.kerubin.api.messaging.core.DomainEventEnvelope;

@Service
public class BancoSubscriberEventHandler {
	
	private static final Logger log = LoggerFactory.getLogger(BancoSubscriberEventHandler.class);
	
	@Autowired
	private BancoRepository bancoRepository;
	
	@Autowired
	private BancoService bancoService;
	
	@Autowired
	BancoDTOConverter bancoDTOConverter;
	
	@RabbitListener(queues = "#{bancoQueue.name}")
	public void onReceiveEvent(DomainEventEnvelope<BancoEvent> envelope) {
		// WARNING: all the code MUST be inside the try catch code. If an error occurs, must be throw AmqpRejectAndDontRequeueException.
		try {
			switch (envelope.getPrimitive()) {
			case BancoEvent.BANCO_CREATED:
			case BancoEvent.BANCO_UPDATED:
			
				saveBanco(envelope.getPayload());
				break;
			
			case BancoEvent.BANCO_DELETED:
				deleteBanco(envelope.getPayload());
				break;
			
			default:
				log.warn("Unexpected entity event: {} for: {}.", envelope.getPrimitive(), "br.com.kerubin.api.cadastros.banco.entity.banco.Banco");
				break;
			}
		} catch(Exception e) {
			log.error("Error receiven event with envelope: " + envelope, e);
			throw new AmqpRejectAndDontRequeueException(e);
		}
	}
	
	private void saveBanco(BancoEvent bancoEvent) {
		saveBanco(bancoEvent, false);
	}
	
	private void saveBanco(BancoEvent bancoEvent, boolean isDeleted) {
		BancoEntity entity = buildBancoEntity(bancoEvent);
		Optional<BancoEntity> optionalEntity = bancoRepository.findById(bancoEvent.getId());
		if (optionalEntity.isPresent()) {
			if (isDeleted) {
				entity = optionalEntity.get();
				entity.setDeleted(true);
			}
			bancoService.update(entity.getId(), entity);
		}
		else {
			bancoService.create(entity);
		}
	}
	
	private void deleteBanco(BancoEvent bancoEvent) {
		Optional<BancoEntity> optionalEntity = bancoRepository.findById(bancoEvent.getId());
		if (optionalEntity.isPresent()) {
			try {
				bancoService.delete(bancoEvent.getId());
			} catch(DataIntegrityViolationException e) {
				log.warn("Record cannot be deleted, will be deactivated instead: " + bancoEvent);
				try {
					saveBanco(bancoEvent, true);
				} catch(Exception e2) {
					log.error("Record cannot be deactivated: " + bancoEvent, e2);
				}
			}
		}
	}

	private BancoEntity buildBancoEntity(BancoEvent bancoEvent) {
		BancoEntity entity = bancoDTOConverter.convertDtoToEntity(bancoEvent);
		return entity;
	}

}
