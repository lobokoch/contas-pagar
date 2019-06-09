/**********************************************************************************************
Code generated with MKL Plug-in version: 3.9.0
Code generated at time stamp: 2019-06-08T10:32:58.121
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/


package br.com.kerubin.api.financeiro.contaspagar.entity.fornecedor;

import javax.inject.Inject;
import org.springframework.stereotype.Component;
import br.com.kerubin.api.financeiro.contaspagar.ObjectMapper;
import br.com.kerubin.api.cadastros.fornecedor.entity.fornecedor.FornecedorEvent;

@Component
public class FornecedorDTOConverter {

	@Inject
	private ObjectMapper mapper;

	public Fornecedor convertEntityToDto(FornecedorEntity entity) {
		Fornecedor dto = null;
		if (entity != null) {
			dto = mapper.map(entity, Fornecedor.class);
		}
		return dto;
	}


	public FornecedorEntity convertDtoToEntity(Fornecedor dto) {
		FornecedorEntity entity = null;
		if (dto != null) {
			entity = mapper.map(dto, FornecedorEntity.class);
		}
		return entity;
	}


	public FornecedorEntity convertDtoToEntity(FornecedorEvent dto) {
		FornecedorEntity entity = null;
		if (dto != null) {
			entity = mapper.map(dto, FornecedorEntity.class);
		}
		return entity;
	}


}