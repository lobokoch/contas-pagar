/**********************************************************************************************
Code generated with MKL Plug-in version: 60.0.6
Copyright: Kerubin - kerubin.platform@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/


package br.com.kerubin.api.financeiro.contaspagar.entity.planoconta;

import javax.inject.Inject;
import org.springframework.stereotype.Component;
import br.com.kerubin.api.servicecore.mapper.ObjectMapper;
import br.com.kerubin.api.financeiro.planocontas.entity.planoconta.PlanoContaEvent;

@Component
public class PlanoContaDTOConverter {

	@Inject
	private ObjectMapper mapper;

	public PlanoConta convertEntityToDto(PlanoContaEntity entity) {
		PlanoConta dto = null;
		if (entity != null) {
			dto = mapper.map(entity, PlanoConta.class, true); // Do not permit passwords fields go outside.
		}
		return dto;
	}


	public PlanoContaEntity convertDtoToEntity(PlanoConta dto) {
		PlanoContaEntity entity = null;
		if (dto != null) {
			entity = mapper.map(dto, PlanoContaEntity.class);
		}
		return entity;
	}


	public PlanoContaEntity convertDtoToEntity(PlanoContaEvent dto) {
		PlanoContaEntity entity = null;
		if (dto != null) {
			entity = mapper.map(dto, PlanoContaEntity.class);
		}
		return entity;
	}


}