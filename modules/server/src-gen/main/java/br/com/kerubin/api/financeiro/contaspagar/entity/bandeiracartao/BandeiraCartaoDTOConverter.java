/**********************************************************************************************
Code generated with MKL Plug-in version: 3.9.0
Code generated at time stamp: 2019-06-08T10:32:58.121
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/


package br.com.kerubin.api.financeiro.contaspagar.entity.bandeiracartao;

import javax.inject.Inject;
import org.springframework.stereotype.Component;
import br.com.kerubin.api.financeiro.contaspagar.ObjectMapper;
import br.com.kerubin.api.cadastros.banco.entity.bandeiracartao.BandeiraCartaoEvent;

@Component
public class BandeiraCartaoDTOConverter {

	@Inject
	private ObjectMapper mapper;

	public BandeiraCartao convertEntityToDto(BandeiraCartaoEntity entity) {
		BandeiraCartao dto = null;
		if (entity != null) {
			dto = mapper.map(entity, BandeiraCartao.class);
		}
		return dto;
	}


	public BandeiraCartaoEntity convertDtoToEntity(BandeiraCartao dto) {
		BandeiraCartaoEntity entity = null;
		if (dto != null) {
			entity = mapper.map(dto, BandeiraCartaoEntity.class);
		}
		return entity;
	}


	public BandeiraCartaoEntity convertDtoToEntity(BandeiraCartaoEvent dto) {
		BandeiraCartaoEntity entity = null;
		if (dto != null) {
			entity = mapper.map(dto, BandeiraCartaoEntity.class);
		}
		return entity;
	}


}