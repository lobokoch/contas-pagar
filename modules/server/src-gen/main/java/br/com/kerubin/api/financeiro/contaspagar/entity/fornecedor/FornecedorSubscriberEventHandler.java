/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-22T06:59:30.300
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.fornecedor;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.kerubin.api.cadastros.fornecedor.entity.fornecedor.FornecedorEvent;

import br.com.kerubin.api.messaging.core.DomainEventEnvelope;

@Service
public class FornecedorSubscriberEventHandler {
	
	private static final Logger log = LoggerFactory.getLogger(FornecedorSubscriberEventHandler.class);
	
	@Autowired
	private FornecedorRepository fornecedorRepository;
	
	@Autowired
	private FornecedorService fornecedorService;
	
	@Autowired
	FornecedorDTOConverter fornecedorDTOConverter;
	
	@RabbitListener(queues = "#{fornecedorQueue.name}")
	public void onReceiveEvent(DomainEventEnvelope<FornecedorEvent> envelope) {
		// WARNING: all the code MUST be inside the try catch code. If an error occurs, must be throw AmqpRejectAndDontRequeueException.
		try {
			switch (envelope.getPrimitive()) {
			case FornecedorEvent.FORNECEDOR_CREATED:
			case FornecedorEvent.FORNECEDOR_UPDATED:
			
				saveFornecedor(envelope.getPayload());
				break;
			
			case FornecedorEvent.FORNECEDOR_DELETED:
				deleteFornecedor(envelope.getPayload());
				break;
			
			default:
				log.warn("Unexpected entity event: {} for: {}.", envelope.getPrimitive(), "br.com.kerubin.api.cadastros.fornecedor.entity.fornecedor.Fornecedor");
				break;
			}
		} catch(Exception e) {
			log.error("Error receiven event with envelope: " + envelope, e);
			throw new AmqpRejectAndDontRequeueException(e);
		}
	}
	
	private void saveFornecedor(FornecedorEvent fornecedorEvent) {
		saveFornecedor(fornecedorEvent, false);
	}
	
	private void saveFornecedor(FornecedorEvent fornecedorEvent, boolean isDeleted) {
		FornecedorEntity entity = buildFornecedorEntity(fornecedorEvent);
		Optional<FornecedorEntity> optionalEntity = fornecedorRepository.findById(fornecedorEvent.getId());
		if (optionalEntity.isPresent()) {
			if (isDeleted) {
				entity = optionalEntity.get();
				entity.setDeleted(true);
			}
			fornecedorService.update(entity.getId(), entity);
		}
		else {
			fornecedorService.create(entity);
		}
	}
	
	private void deleteFornecedor(FornecedorEvent fornecedorEvent) {
		Optional<FornecedorEntity> optionalEntity = fornecedorRepository.findById(fornecedorEvent.getId());
		if (optionalEntity.isPresent()) {
			try {
				fornecedorService.delete(fornecedorEvent.getId());
			} catch(DataIntegrityViolationException e) {
				log.warn("Record cannot be deleted, will be deactivated instead: " + fornecedorEvent);
				try {
					saveFornecedor(fornecedorEvent, true);
				} catch(Exception e2) {
					log.error("Record cannot be deactivated: " + fornecedorEvent, e2);
				}
			}
		}
	}

	private FornecedorEntity buildFornecedorEntity(FornecedorEvent fornecedorEvent) {
		FornecedorEntity entity = fornecedorDTOConverter.convertDtoToEntity(fornecedorEvent);
		return entity;
	}

}