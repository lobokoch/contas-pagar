/**********************************************************************************************
Code generated with MKL Plug-in version: 55.0.3
Copyright: Kerubin - kerubin.platform@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/


package br.com.kerubin.api.financeiro.contaspagar.entity.contapagar;

import javax.inject.Inject;
import org.springframework.stereotype.Component;
import br.com.kerubin.api.servicecore.mapper.ObjectMapper;

@Component
public class ContaPagarDTOConverter {

	@Inject
	private ObjectMapper mapper;

	public ContaPagar convertEntityToDto(ContaPagarEntity entity) {
		ContaPagar dto = null;
		if (entity != null) {
			dto = mapper.map(entity, ContaPagar.class, true); // Do not permit passwords fields go outside.
		}
		return dto;
	}


	public ContaPagarEntity convertDtoToEntity(ContaPagar dto) {
		ContaPagarEntity entity = null;
		if (dto != null) {
			entity = mapper.map(dto, ContaPagarEntity.class);
		}
		return entity;
	}


}