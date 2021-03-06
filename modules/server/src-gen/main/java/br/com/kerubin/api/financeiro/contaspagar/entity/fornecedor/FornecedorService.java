/**********************************************************************************************
Code generated by MKL Plug-in
Copyright: Kerubin - kerubin.platform@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.fornecedor;

import java.util.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface FornecedorService {
	
	public FornecedorEntity create(FornecedorEntity fornecedorEntity);
	
	public FornecedorEntity read(java.util.UUID id);
	
	public FornecedorEntity update(java.util.UUID id, FornecedorEntity fornecedorEntity);
	
	public void delete(java.util.UUID id);
	
	public void deleteInBulk(java.util.List<java.util.UUID> idList);
	
	public Page<FornecedorEntity> list(FornecedorListFilter fornecedorListFilter, Pageable pageable);
	
	public Collection<FornecedorAutoComplete> autoComplete(String query);
	
	 
	
	public Collection<FornecedorNomeAutoComplete> fornecedorNomeAutoComplete(String query);
}
