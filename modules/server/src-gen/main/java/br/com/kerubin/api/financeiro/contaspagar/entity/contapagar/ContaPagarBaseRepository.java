/**********************************************************************************************
Code generated with MKL Plug-in version: 3.5.1
Code generated at time stamp: 2019-06-02T06:23:49.436
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.contapagar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import java.util.Collection;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ContaPagarBaseRepository extends JpaRepository<ContaPagarEntity, java.util.UUID>, QuerydslPredicateExecutor<ContaPagarEntity> {
	
	@Query("select distinct ac.id as id, ac.descricao as descricao from ContaPagarEntity ac where ( upper(ac.descricao) like upper(concat('%', :query, '%')) ) order by 1 asc")
	Collection<ContaPagarAutoComplete> autoComplete(@Param("query") String query);
	
	@Query("select distinct ac.descricao as descricao from ContaPagarEntity ac where ( upper(ac.descricao) like upper(concat('%', :query, '%')) ) order by 1 asc")
	Collection<ContaPagarDescricaoAutoComplete> contaPagarDescricaoAutoComplete(@Param("query") String query);
	
	@Query("select distinct ac.agrupador as agrupador from ContaPagarEntity ac where ( upper(ac.agrupador) like upper(concat('%', :query, '%')) ) order by 1 asc")
	Collection<ContaPagarAgrupadorAutoComplete> contaPagarAgrupadorAutoComplete(@Param("query") String query);
	
}
