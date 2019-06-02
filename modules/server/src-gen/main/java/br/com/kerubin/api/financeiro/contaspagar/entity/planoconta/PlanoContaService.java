/**********************************************************************************************
Code generated with MKL Plug-in version: 3.5.1
Code generated at time stamp: 2019-06-02T06:23:49.436
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.planoconta;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;

public interface PlanoContaService {
	
	public PlanoContaEntity create(PlanoContaEntity planoContaEntity);
	
	public PlanoContaEntity read(java.util.UUID id);
	
	public PlanoContaEntity update(java.util.UUID id, PlanoContaEntity planoContaEntity);
	
	public void delete(java.util.UUID id);
	
	public Page<PlanoContaEntity> list(PlanoContaListFilter planoContaListFilter, Pageable pageable);
	
	public Collection<PlanoContaAutoComplete> autoComplete(String query);
	
	public Collection<PlanoContaCodigoAutoComplete> planoContaCodigoAutoComplete(String query);
	
	public Collection<PlanoContaDescricaoAutoComplete> planoContaDescricaoAutoComplete(String query);
}
