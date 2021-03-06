/**********************************************************************************************
Code generated by MKL Plug-in
Copyright: Kerubin - kerubin.platform@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.contapagarmultiple;

import java.util.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.kerubin.api.financeiro.contaspagar.entity.fornecedor.FornecedorAutoComplete;
import br.com.kerubin.api.financeiro.contaspagar.entity.planoconta.PlanoContaAutoComplete;
import br.com.kerubin.api.financeiro.contaspagar.entity.contabancaria.ContaBancariaAutoComplete;
import br.com.kerubin.api.financeiro.contaspagar.entity.cartaocredito.CartaoCreditoAutoComplete;
import br.com.kerubin.api.financeiro.contaspagar.entity.contapagar.ContaPagarAutoComplete;

public interface ContaPagarMultipleService {
	
	public ContaPagarMultipleEntity create(ContaPagarMultipleEntity contaPagarMultipleEntity);
	
	public ContaPagarMultipleEntity read(java.util.UUID id);
	
	public ContaPagarMultipleEntity update(java.util.UUID id, ContaPagarMultipleEntity contaPagarMultipleEntity);
	
	public void delete(java.util.UUID id);
	
	public void deleteInBulk(java.util.List<java.util.UUID> idList);
	
	public Page<ContaPagarMultipleEntity> list(ContaPagarMultipleListFilter contaPagarMultipleListFilter, Pageable pageable);
	
	public Collection<ContaPagarMultipleAutoComplete> autoComplete(String query);
	
	// Begin relationships autoComplete 
	public Collection<FornecedorAutoComplete> fornecedorFornecedorAutoComplete(String query);
	public Collection<PlanoContaAutoComplete> planoContaPlanoContasAutoComplete(String query);
	public Collection<ContaBancariaAutoComplete> contaBancariaContaBancariaAutoComplete(String query);
	public Collection<CartaoCreditoAutoComplete> cartaoCreditoCartaoCreditoAutoComplete(String query);
	public Collection<ContaPagarAutoComplete> contaPagarContaPagarAutoComplete(String query);
	// End relationships autoComplete
	 
	
	public Collection<ContaPagarMultipleHistConcBancariaAutoComplete> contaPagarMultipleHistConcBancariaAutoComplete(String query);
	
	public ContaPagarMultipleSumFields getContaPagarMultipleSumFields(ContaPagarMultipleListFilter contaPagarMultipleListFilter);
}
