/**********************************************************************************************
Code generated with MKL Plug-in version: 3.9.0
Code generated at time stamp: 2019-06-08T10:32:58.121
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.fornecedor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import java.util.Collection;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

public interface FornecedorRepository extends JpaRepository<FornecedorEntity, java.util.UUID>, QuerydslPredicateExecutor<FornecedorEntity> {
	
	@Query("select distinct ac.id as id, ac.nome as nome from FornecedorEntity ac where ( upper(ac.nome) like upper(concat('%', :query, '%')) ) order by 1 asc")
	Collection<FornecedorAutoComplete> autoComplete(@Param("query") String query);
	
	@Query("select distinct ac.nome as nome from FornecedorEntity ac where ( upper(ac.nome) like upper(concat('%', :query, '%')) ) order by 1 asc")
	Collection<FornecedorNomeAutoComplete> fornecedorNomeAutoComplete(@Param("query") String query);
	
}
