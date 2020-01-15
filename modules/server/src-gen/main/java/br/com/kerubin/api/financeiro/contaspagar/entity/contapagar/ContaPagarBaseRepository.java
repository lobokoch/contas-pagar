/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-13T08:12:17.669
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.contapagar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import java.util.Collection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ContaPagarBaseRepository extends JpaRepository<ContaPagarEntity, java.util.UUID>, QuerydslPredicateExecutor<ContaPagarEntity> {
	
	// WARNING: supports only where clause with like for STRING fields. For relationships entities will get the first string autocomplete key field name.
	@Query("select distinct ac.id as id, ac.descricao as descricao from ContaPagarEntity ac where ( upper(ac.descricao) like upper(concat('%', :query, '%')) ) order by 1 asc")
	Collection<ContaPagarAutoComplete> autoComplete(@Param("query") String query);
	// WARNING: supports only where clause with like for STRING fields. For relationships entities will get the first string autocomplete key field name.
	@Query("select distinct ac.descricao as descricao from ContaPagarEntity ac where ( upper(ac.descricao) like upper(concat('%', :query, '%')) ) order by 1 asc")
	Collection<ContaPagarDescricaoAutoComplete> contaPagarDescricaoAutoComplete(@Param("query") String query);
	// WARNING: supports only where clause with like for STRING fields. For relationships entities will get the first string autocomplete key field name.
	@Query("select distinct ac.histConcBancaria as histConcBancaria from ContaPagarEntity ac where ( upper(ac.histConcBancaria) like upper(concat('%', :query, '%')) ) order by 1 asc")
	Collection<ContaPagarHistConcBancariaAutoComplete> contaPagarHistConcBancariaAutoComplete(@Param("query") String query);
	// WARNING: supports only where clause with like for STRING fields. For relationships entities will get the first string autocomplete key field name.
	@Query("select distinct ac.agrupador as agrupador from ContaPagarEntity ac where ( upper(ac.agrupador) like upper(concat('%', :query, '%')) ) order by 1 asc")
	Collection<ContaPagarAgrupadorAutoComplete> contaPagarAgrupadorAutoComplete(@Param("query") String query);
}
