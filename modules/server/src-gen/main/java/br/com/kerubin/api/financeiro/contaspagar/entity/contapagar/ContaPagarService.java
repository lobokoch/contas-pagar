/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-22T06:59:30.300
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.contapagar;

import java.util.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.kerubin.api.financeiro.contaspagar.entity.planoconta.PlanoContaAutoComplete;
import br.com.kerubin.api.financeiro.contaspagar.entity.contabancaria.ContaBancariaAutoComplete;
import br.com.kerubin.api.financeiro.contaspagar.entity.cartaocredito.CartaoCreditoAutoComplete;
import br.com.kerubin.api.financeiro.contaspagar.entity.fornecedor.FornecedorAutoComplete;

public interface ContaPagarService {
	
	public ContaPagarEntity create(ContaPagarEntity contaPagarEntity);
	
	public ContaPagarEntity read(java.util.UUID id);
	
	public ContaPagarEntity update(java.util.UUID id, ContaPagarEntity contaPagarEntity);
	
	public void delete(java.util.UUID id);
	
	public Page<ContaPagarEntity> list(ContaPagarListFilter contaPagarListFilter, Pageable pageable);
	
	public Collection<ContaPagarAutoComplete> autoComplete(String query);
	
	// Begin relationships autoComplete 
	public Collection<PlanoContaAutoComplete> planoContaPlanoContasAutoComplete(String query);
	public Collection<ContaBancariaAutoComplete> contaBancariaContaBancariaAutoComplete(String query);
	public Collection<CartaoCreditoAutoComplete> cartaoCreditoCartaoCreditoAutoComplete(String query);
	public Collection<FornecedorAutoComplete> fornecedorFornecedorAutoComplete(String query);
	// End relationships autoComplete
	 
	
	public Collection<ContaPagarDescricaoAutoComplete> contaPagarDescricaoAutoComplete(String query);
	
	public Collection<ContaPagarHistConcBancariaAutoComplete> contaPagarHistConcBancariaAutoComplete(String query);
	
	public Collection<ContaPagarAgrupadorAutoComplete> contaPagarAgrupadorAutoComplete(String query);
	
	public ContaPagarSumFields getContaPagarSumFields(ContaPagarListFilter contaPagarListFilter);
	
	public void actionBaixarContaComDataPagamentoHoje(java.util.UUID id);
	
	public void actionBaixarContaComDataPagamentoIgualDataVenciento(java.util.UUID id);
	
	public void actionEstornarPagamentoContaComUmClique(java.util.UUID id);
	
	public void actionFazerCopiasContaPagar(ContaPagarMakeCopies contaPagarMakeCopies);
}