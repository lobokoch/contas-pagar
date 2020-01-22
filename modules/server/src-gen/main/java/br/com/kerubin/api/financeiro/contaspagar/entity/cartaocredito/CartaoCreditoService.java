/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-22T06:59:30.300
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.cartaocredito;

import java.util.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.kerubin.api.financeiro.contaspagar.entity.banco.BancoAutoComplete;
import br.com.kerubin.api.financeiro.contaspagar.entity.bandeiracartao.BandeiraCartaoAutoComplete;

public interface CartaoCreditoService {
	
	public CartaoCreditoEntity create(CartaoCreditoEntity cartaoCreditoEntity);
	
	public CartaoCreditoEntity read(java.util.UUID id);
	
	public CartaoCreditoEntity update(java.util.UUID id, CartaoCreditoEntity cartaoCreditoEntity);
	
	public void delete(java.util.UUID id);
	
	public Page<CartaoCreditoEntity> list(CartaoCreditoListFilter cartaoCreditoListFilter, Pageable pageable);
	
	public Collection<CartaoCreditoAutoComplete> autoComplete(String query);
	
	// Begin relationships autoComplete 
	public Collection<BancoAutoComplete> bancoBancoAutoComplete(String query);
	public Collection<BandeiraCartaoAutoComplete> bandeiraCartaoBandeiraCartaoAutoComplete(String query);
	// End relationships autoComplete
	 
}
