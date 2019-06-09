/**********************************************************************************************
Code generated with MKL Plug-in version: 3.9.0
Code generated at time stamp: 2019-06-08T10:32:58.121
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.bandeiracartao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import java.util.Collection;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

public interface BandeiraCartaoRepository extends JpaRepository<BandeiraCartaoEntity, java.util.UUID>, QuerydslPredicateExecutor<BandeiraCartaoEntity> {
	
	@Query("select distinct ac.id as id, ac.nomeBandeira as nomeBandeira from BandeiraCartaoEntity ac where ( upper(ac.nomeBandeira) like upper(concat('%', :query, '%')) ) order by 1 asc")
	Collection<BandeiraCartaoAutoComplete> autoComplete(@Param("query") String query);
	
	@Query("select distinct ac.nomeBandeira as nomeBandeira from BandeiraCartaoEntity ac where ( upper(ac.nomeBandeira) like upper(concat('%', :query, '%')) ) order by 1 asc")
	Collection<BandeiraCartaoNomeBandeiraAutoComplete> bandeiraCartaoNomeBandeiraAutoComplete(@Param("query") String query);
	
}
