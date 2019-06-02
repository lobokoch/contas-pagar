/**********************************************************************************************
Code generated with MKL Plug-in version: 3.5.1
Code generated at time stamp: 2019-06-02T06:23:49.436
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.planoconta;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import br.com.kerubin.api.financeiro.planocontas.TipoPlanoContaFinanceiro;

import br.com.kerubin.api.financeiro.planocontas.entity.planoconta.PlanoContaEvent;

import br.com.kerubin.api.messaging.core.DomainEventEnvelope;

@Service
public class PlanoContaSubscriberEventHandler {
	
	private static final Logger log = LoggerFactory.getLogger(PlanoContaSubscriberEventHandler.class);
	
	@Autowired
	private PlanoContaRepository planoContaRepository;
	
	@Autowired
	private PlanoContaService planoContaService;
	
	@Autowired
	PlanoContaDTOConverter planoContaDTOConverter;
	
	@RabbitListener(queues = "#{planoContaQueue.name}")
	public void onReceiveEvent(DomainEventEnvelope<PlanoContaEvent> envelope) {
		// Begin code from Subscribe Rule
		boolean accepted = envelope.getPayload().getTipoFinanceiro().equals(TipoPlanoContaFinanceiro.DESPESA);
		if (!accepted) {
			return; // Rejects this subscribe.
		}
		// End code from Subscribe Rule
		
		try {
			switch (envelope.getPrimitive()) {
			case PlanoContaEvent.PLANO_CONTA_CREATED:
			case PlanoContaEvent.PLANO_CONTA_UPDATED:
			
				savePlanoConta(envelope.getPayload());
				break;
			
			case PlanoContaEvent.PLANO_CONTA_DELETED:
				deletePlanoConta(envelope.getPayload());
				break;
			
			default:
				log.warn("Unexpected entity event: {} for: {}.", envelope.getPrimitive(), "br.com.kerubin.api.financeiro.planocontas.entity.planoconta.PlanoConta");
				break;
			}
		} catch(Exception e) {
			log.error("Error receiven event with envelope: " + envelope, e);
			throw new AmqpRejectAndDontRequeueException(e);
		}
	}
	
	private void savePlanoConta(PlanoContaEvent planoContaEvent) {
		savePlanoConta(planoContaEvent, false);
	}
	
	private void savePlanoConta(PlanoContaEvent planoContaEvent, boolean isDeleted) {
		PlanoContaEntity entity = buildPlanoContaEntity(planoContaEvent);
		Optional<PlanoContaEntity> optionalEntity = planoContaRepository.findById(planoContaEvent.getId());
		if (optionalEntity.isPresent()) {
			if (isDeleted) {
				entity = optionalEntity.get();
				entity.setDeleted(true);
			}
			planoContaService.update(entity.getId(), entity);
		}
		else {
			planoContaService.create(entity);
		}
	}
	
	private void deletePlanoConta(PlanoContaEvent planoContaEvent) {
		Optional<PlanoContaEntity> optionalEntity = planoContaRepository.findById(planoContaEvent.getId());
		if (optionalEntity.isPresent()) {
			try {
				planoContaService.delete(planoContaEvent.getId());
			} catch(DataIntegrityViolationException e) {
				savePlanoConta(planoContaEvent, true);
			}
		}
	}

	private PlanoContaEntity buildPlanoContaEntity(PlanoContaEvent planoContaEvent) {
		PlanoContaEntity entity = planoContaDTOConverter.convertDtoToEntity(planoContaEvent);
		return entity;
	}

}
