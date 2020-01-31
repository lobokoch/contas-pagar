/**********************************************************************************************
Code generated with MKL Plug-in version: 60.0.6
Copyright: Kerubin - kerubin.platform@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.planoconta;

import java.util.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface PlanoContaService {
	
	public PlanoContaEntity create(PlanoContaEntity planoContaEntity);
	
	public PlanoContaEntity read(java.util.UUID id);
	
	public PlanoContaEntity update(java.util.UUID id, PlanoContaEntity planoContaEntity);
	
	public void delete(java.util.UUID id);
	
	public Page<PlanoContaEntity> list(PlanoContaListFilter planoContaListFilter, Pageable pageable);
	
	public Collection<PlanoContaAutoComplete> autoComplete(String query);
	
	// Begin relationships autoComplete 
	public Collection<PlanoContaAutoComplete> planoContaPlanoContaPaiAutoComplete(String query);
	// End relationships autoComplete
	 
	
	public Collection<PlanoContaCodigoAutoComplete> planoContaCodigoAutoComplete(String query);
	
	public Collection<PlanoContaDescricaoAutoComplete> planoContaDescricaoAutoComplete(String query);
}
