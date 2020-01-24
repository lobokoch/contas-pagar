/**********************************************************************************************
Code generated with MKL Plug-in version: 55.0.3
Copyright: Kerubin - kerubin.platform@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.bandeiracartao;

import java.util.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface BandeiraCartaoService {
	
	public BandeiraCartaoEntity create(BandeiraCartaoEntity bandeiraCartaoEntity);
	
	public BandeiraCartaoEntity read(java.util.UUID id);
	
	public BandeiraCartaoEntity update(java.util.UUID id, BandeiraCartaoEntity bandeiraCartaoEntity);
	
	public void delete(java.util.UUID id);
	
	public Page<BandeiraCartaoEntity> list(BandeiraCartaoListFilter bandeiraCartaoListFilter, Pageable pageable);
	
	public Collection<BandeiraCartaoAutoComplete> autoComplete(String query);
	
	 
	
	public Collection<BandeiraCartaoNomeBandeiraAutoComplete> bandeiraCartaoNomeBandeiraAutoComplete(String query);
}
