/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-13T08:12:17.669
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.contabancaria;

import java.util.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.kerubin.api.financeiro.contaspagar.entity.agenciabancaria.AgenciaBancariaAutoComplete;

public interface ContaBancariaService {
	
	public ContaBancariaEntity create(ContaBancariaEntity contaBancariaEntity);
	
	public ContaBancariaEntity read(java.util.UUID id);
	
	public ContaBancariaEntity update(java.util.UUID id, ContaBancariaEntity contaBancariaEntity);
	
	public void delete(java.util.UUID id);
	
	public Page<ContaBancariaEntity> list(ContaBancariaListFilter contaBancariaListFilter, Pageable pageable);
	
	public Collection<ContaBancariaAutoComplete> autoComplete(String query);
	
	// Begin relationships autoComplete 
	public Collection<AgenciaBancariaAutoComplete> agenciaBancariaAgenciaAutoComplete(String query);
	// End relationships autoComplete
	 
	
	public Collection<ContaBancariaNumeroContaAutoComplete> contaBancariaNumeroContaAutoComplete(String query);
}
