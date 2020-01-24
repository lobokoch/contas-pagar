/**********************************************************************************************
Code generated with MKL Plug-in version: 55.0.3
Copyright: Kerubin - kerubin.platform@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.agenciabancaria;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.kerubin.api.cadastros.banco.entity.agenciabancaria.AgenciaBancariaEvent;

import br.com.kerubin.api.messaging.core.DomainEventEnvelope;

@Service
public class AgenciaBancariaSubscriberEventHandler {
	
	private static final Logger log = LoggerFactory.getLogger(AgenciaBancariaSubscriberEventHandler.class);
	
	@Autowired
	private AgenciaBancariaRepository agenciaBancariaRepository;
	
	@Autowired
	private AgenciaBancariaService agenciaBancariaService;
	
	@Autowired
	AgenciaBancariaDTOConverter agenciaBancariaDTOConverter;
	
	@RabbitListener(queues = "#{agenciaBancariaQueue.name}")
	public void onReceiveEvent(DomainEventEnvelope<AgenciaBancariaEvent> envelope) {
		// WARNING: all the code MUST be inside the try catch code. If an error occurs, must be throw AmqpRejectAndDontRequeueException.
		try {
			switch (envelope.getPrimitive()) {
			case AgenciaBancariaEvent.AGENCIA_BANCARIA_CREATED:
			case AgenciaBancariaEvent.AGENCIA_BANCARIA_UPDATED:
			
				saveAgenciaBancaria(envelope.getPayload());
				break;
			
			case AgenciaBancariaEvent.AGENCIA_BANCARIA_DELETED:
				deleteAgenciaBancaria(envelope.getPayload());
				break;
			
			default:
				log.warn("Unexpected entity event: {} for: {}.", envelope.getPrimitive(), "br.com.kerubin.api.cadastros.banco.entity.agenciabancaria.AgenciaBancaria");
				break;
			}
		} catch(Exception e) {
			log.error("Error receiven event with envelope: " + envelope, e);
			throw new AmqpRejectAndDontRequeueException(e);
		}
	}
	
	private void saveAgenciaBancaria(AgenciaBancariaEvent agenciaBancariaEvent) {
		saveAgenciaBancaria(agenciaBancariaEvent, false);
	}
	
	private void saveAgenciaBancaria(AgenciaBancariaEvent agenciaBancariaEvent, boolean isDeleted) {
		AgenciaBancariaEntity entity = buildAgenciaBancariaEntity(agenciaBancariaEvent);
		Optional<AgenciaBancariaEntity> optionalEntity = agenciaBancariaRepository.findById(agenciaBancariaEvent.getId());
		if (optionalEntity.isPresent()) {
			if (isDeleted) {
				entity = optionalEntity.get();
				entity.setDeleted(true);
			}
			agenciaBancariaService.update(entity.getId(), entity);
		}
		else {
			agenciaBancariaService.create(entity);
		}
	}
	
	private void deleteAgenciaBancaria(AgenciaBancariaEvent agenciaBancariaEvent) {
		Optional<AgenciaBancariaEntity> optionalEntity = agenciaBancariaRepository.findById(agenciaBancariaEvent.getId());
		if (optionalEntity.isPresent()) {
			try {
				agenciaBancariaService.delete(agenciaBancariaEvent.getId());
			} catch(DataIntegrityViolationException e) {
				log.warn("Record cannot be deleted, will be deactivated instead: " + agenciaBancariaEvent);
				try {
					saveAgenciaBancaria(agenciaBancariaEvent, true);
				} catch(Exception e2) {
					log.error("Record cannot be deactivated: " + agenciaBancariaEvent, e2);
				}
			}
		}
	}

	private AgenciaBancariaEntity buildAgenciaBancariaEntity(AgenciaBancariaEvent agenciaBancariaEvent) {
		AgenciaBancariaEntity entity = agenciaBancariaDTOConverter.convertDtoToEntity(agenciaBancariaEvent);
		return entity;
	}

}
