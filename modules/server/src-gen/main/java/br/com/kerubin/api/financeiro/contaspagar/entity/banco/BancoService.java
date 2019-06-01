/**********************************************************************************************
Code generated with MKL Plug-in version: 3.5.1
Code generated at time stamp: 2019-06-01T15:26:20.750
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.banco;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;

public interface BancoService {
	
	public BancoEntity create(BancoEntity bancoEntity);
	
	public BancoEntity read(java.util.UUID id);
	
	public BancoEntity update(java.util.UUID id, BancoEntity bancoEntity);
	
	public void delete(java.util.UUID id);
	
	public Page<BancoEntity> list(BancoListFilter bancoListFilter, Pageable pageable);
	
	public Collection<BancoAutoComplete> autoComplete(String query);
	
	public Collection<BancoNomeAutoComplete> bancoNomeAutoComplete(String query);
}
