/**********************************************************************************************
Code generated with MKL Plug-in version: 3.9.0
Code generated at time stamp: 2019-06-08T10:32:58.121
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.contapagar;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;

public interface ContaPagarService {
	
	public ContaPagarEntity create(ContaPagarEntity contaPagarEntity);
	
	public ContaPagarEntity read(java.util.UUID id);
	
	public ContaPagarEntity update(java.util.UUID id, ContaPagarEntity contaPagarEntity);
	
	public void delete(java.util.UUID id);
	
	public Page<ContaPagarEntity> list(ContaPagarListFilter contaPagarListFilter, Pageable pageable);
	
	public Collection<ContaPagarAutoComplete> autoComplete(String query);
	
	public Collection<ContaPagarDescricaoAutoComplete> contaPagarDescricaoAutoComplete(String query);
	
	public Collection<ContaPagarAgrupadorAutoComplete> contaPagarAgrupadorAutoComplete(String query);
	
	public ContaPagarSumFields getContaPagarSumFields(ContaPagarListFilter contaPagarListFilter);
	
	public void actionBaixarContaComUmClique(java.util.UUID id);
	
	public void actionEstornarPagamentoContaComUmClique(java.util.UUID id);
	
	public void actionFazerCopiasContaPagar(ContaPagarMakeCopies contaPagarMakeCopies);
}
