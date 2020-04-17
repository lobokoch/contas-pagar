/**********************************************************************************************
Code generated by MKL Plug-in
Copyright: Kerubin - kerubin.platform@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.agenciabancaria;

import java.util.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.kerubin.api.financeiro.contaspagar.entity.banco.BancoAutoComplete;

public interface AgenciaBancariaService {
	
	public AgenciaBancariaEntity create(AgenciaBancariaEntity agenciaBancariaEntity);
	
	public AgenciaBancariaEntity read(java.util.UUID id);
	
	public AgenciaBancariaEntity update(java.util.UUID id, AgenciaBancariaEntity agenciaBancariaEntity);
	
	public void delete(java.util.UUID id);
	
	public void deleteInBulk(java.util.List<java.util.UUID> idList);
	
	public Page<AgenciaBancariaEntity> list(AgenciaBancariaListFilter agenciaBancariaListFilter, Pageable pageable);
	
	public Collection<AgenciaBancariaAutoComplete> autoComplete(String query);
	
	// Begin relationships autoComplete 
	public Collection<BancoAutoComplete> bancoBancoAutoComplete(String query);
	// End relationships autoComplete
	 
}
