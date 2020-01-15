/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-13T08:12:17.669
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/


package br.com.kerubin.api.financeiro.contaspagar.entity.cartaocredito;

import javax.inject.Inject;
import org.springframework.stereotype.Component;
import br.com.kerubin.api.servicecore.mapper.ObjectMapper;
import br.com.kerubin.api.cadastros.banco.entity.cartaocredito.CartaoCreditoEvent;

@Component
public class CartaoCreditoDTOConverter {

	@Inject
	private ObjectMapper mapper;

	public CartaoCredito convertEntityToDto(CartaoCreditoEntity entity) {
		CartaoCredito dto = null;
		if (entity != null) {
			dto = mapper.map(entity, CartaoCredito.class, true); // Do not permit passwords fields go outside.
		}
		return dto;
	}


	public CartaoCreditoEntity convertDtoToEntity(CartaoCredito dto) {
		CartaoCreditoEntity entity = null;
		if (dto != null) {
			entity = mapper.map(dto, CartaoCreditoEntity.class);
		}
		return entity;
	}


	public CartaoCreditoEntity convertDtoToEntity(CartaoCreditoEvent dto) {
		CartaoCreditoEntity entity = null;
		if (dto != null) {
			entity = mapper.map(dto, CartaoCreditoEntity.class);
		}
		return entity;
	}


}