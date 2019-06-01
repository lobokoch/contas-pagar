/**********************************************************************************************
Code generated with MKL Plug-in version: 3.5.1
Code generated at time stamp: 2019-06-01T15:26:20.750
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.contabancaria;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;

public interface ContaBancariaService {
	
	public ContaBancariaEntity create(ContaBancariaEntity contaBancariaEntity);
	
	public ContaBancariaEntity read(java.util.UUID id);
	
	public ContaBancariaEntity update(java.util.UUID id, ContaBancariaEntity contaBancariaEntity);
	
	public void delete(java.util.UUID id);
	
	public Page<ContaBancariaEntity> list(ContaBancariaListFilter contaBancariaListFilter, Pageable pageable);
	
	public Collection<ContaBancariaAutoComplete> autoComplete(String query);
	
	public Collection<ContaBancariaNumeroContaAutoComplete> contaBancariaNumeroContaAutoComplete(String query);
}
