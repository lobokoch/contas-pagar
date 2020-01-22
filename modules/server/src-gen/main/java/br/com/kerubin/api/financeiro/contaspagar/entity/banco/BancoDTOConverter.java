/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-22T06:59:30.300
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/


package br.com.kerubin.api.financeiro.contaspagar.entity.banco;

import javax.inject.Inject;
import org.springframework.stereotype.Component;
import br.com.kerubin.api.servicecore.mapper.ObjectMapper;
import br.com.kerubin.api.cadastros.banco.entity.banco.BancoEvent;

@Component
public class BancoDTOConverter {

	@Inject
	private ObjectMapper mapper;

	public Banco convertEntityToDto(BancoEntity entity) {
		Banco dto = null;
		if (entity != null) {
			dto = mapper.map(entity, Banco.class, true); // Do not permit passwords fields go outside.
		}
		return dto;
	}


	public BancoEntity convertDtoToEntity(Banco dto) {
		BancoEntity entity = null;
		if (dto != null) {
			entity = mapper.map(dto, BancoEntity.class);
		}
		return entity;
	}


	public BancoEntity convertDtoToEntity(BancoEvent dto) {
		BancoEntity entity = null;
		if (dto != null) {
			entity = mapper.map(dto, BancoEntity.class);
		}
		return entity;
	}


}