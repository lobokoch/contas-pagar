/**********************************************************************************************
Code generated with MKL Plug-in version: 60.0.6
Copyright: Kerubin - kerubin.platform@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.banco;

import java.util.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface BancoService {
	
	public BancoEntity create(BancoEntity bancoEntity);
	
	public BancoEntity read(java.util.UUID id);
	
	public BancoEntity update(java.util.UUID id, BancoEntity bancoEntity);
	
	public void delete(java.util.UUID id);
	
	public Page<BancoEntity> list(BancoListFilter bancoListFilter, Pageable pageable);
	
	public Collection<BancoAutoComplete> autoComplete(String query);
	
	 
	
	public Collection<BancoNomeAutoComplete> bancoNomeAutoComplete(String query);
	// findBy methods
	public BancoEntity findBancoByNumero(String numero);
}
