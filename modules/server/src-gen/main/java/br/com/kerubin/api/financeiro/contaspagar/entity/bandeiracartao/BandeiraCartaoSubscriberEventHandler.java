/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-13T08:12:17.669
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.bandeiracartao;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.kerubin.api.cadastros.banco.entity.bandeiracartao.BandeiraCartaoEvent;

import br.com.kerubin.api.messaging.core.DomainEventEnvelope;

@Service
public class BandeiraCartaoSubscriberEventHandler {
	
	private static final Logger log = LoggerFactory.getLogger(BandeiraCartaoSubscriberEventHandler.class);
	
	@Autowired
	private BandeiraCartaoRepository bandeiraCartaoRepository;
	
	@Autowired
	private BandeiraCartaoService bandeiraCartaoService;
	
	@Autowired
	BandeiraCartaoDTOConverter bandeiraCartaoDTOConverter;
	
	@RabbitListener(queues = "#{bandeiraCartaoQueue.name}")
	public void onReceiveEvent(DomainEventEnvelope<BandeiraCartaoEvent> envelope) {
		// WARNING: all the code MUST be inside the try catch code. If an error occurs, must be throw AmqpRejectAndDontRequeueException.
		try {
			switch (envelope.getPrimitive()) {
			case BandeiraCartaoEvent.BANDEIRA_CARTAO_CREATED:
			case BandeiraCartaoEvent.BANDEIRA_CARTAO_UPDATED:
			
				saveBandeiraCartao(envelope.getPayload());
				break;
			
			case BandeiraCartaoEvent.BANDEIRA_CARTAO_DELETED:
				deleteBandeiraCartao(envelope.getPayload());
				break;
			
			default:
				log.warn("Unexpected entity event: {} for: {}.", envelope.getPrimitive(), "br.com.kerubin.api.cadastros.banco.entity.bandeiracartao.BandeiraCartao");
				break;
			}
		} catch(Exception e) {
			log.error("Error receiven event with envelope: " + envelope, e);
			throw new AmqpRejectAndDontRequeueException(e);
		}
	}
	
	private void saveBandeiraCartao(BandeiraCartaoEvent bandeiraCartaoEvent) {
		saveBandeiraCartao(bandeiraCartaoEvent, false);
	}
	
	private void saveBandeiraCartao(BandeiraCartaoEvent bandeiraCartaoEvent, boolean isDeleted) {
		BandeiraCartaoEntity entity = buildBandeiraCartaoEntity(bandeiraCartaoEvent);
		Optional<BandeiraCartaoEntity> optionalEntity = bandeiraCartaoRepository.findById(bandeiraCartaoEvent.getId());
		if (optionalEntity.isPresent()) {
			if (isDeleted) {
				entity = optionalEntity.get();
				entity.setDeleted(true);
			}
			bandeiraCartaoService.update(entity.getId(), entity);
		}
		else {
			bandeiraCartaoService.create(entity);
		}
	}
	
	private void deleteBandeiraCartao(BandeiraCartaoEvent bandeiraCartaoEvent) {
		Optional<BandeiraCartaoEntity> optionalEntity = bandeiraCartaoRepository.findById(bandeiraCartaoEvent.getId());
		if (optionalEntity.isPresent()) {
			try {
				bandeiraCartaoService.delete(bandeiraCartaoEvent.getId());
			} catch(DataIntegrityViolationException e) {
				log.warn("Record cannot be deleted, will be deactivated instead: " + bandeiraCartaoEvent);
				try {
					saveBandeiraCartao(bandeiraCartaoEvent, true);
				} catch(Exception e2) {
					log.error("Record cannot be deactivated: " + bandeiraCartaoEvent, e2);
				}
			}
		}
	}

	private BandeiraCartaoEntity buildBandeiraCartaoEntity(BandeiraCartaoEvent bandeiraCartaoEvent) {
		BandeiraCartaoEntity entity = bandeiraCartaoDTOConverter.convertDtoToEntity(bandeiraCartaoEvent);
		return entity;
	}

}
