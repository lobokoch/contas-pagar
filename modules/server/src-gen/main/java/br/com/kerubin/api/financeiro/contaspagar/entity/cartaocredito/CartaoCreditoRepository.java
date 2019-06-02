/**********************************************************************************************
Code generated with MKL Plug-in version: 3.5.1
Code generated at time stamp: 2019-06-02T06:23:49.436
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.cartaocredito;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import java.util.Collection;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

public interface CartaoCreditoRepository extends JpaRepository<CartaoCreditoEntity, java.util.UUID>, QuerydslPredicateExecutor<CartaoCreditoEntity> {
	
	@Query("select distinct ac.id as id, ac.nomeTitular as nomeTitular, ac.numeroCartao as numeroCartao from CartaoCreditoEntity ac where ( upper(ac.nomeTitular) like upper(concat('%', :query, '%')) ) or ( upper(ac.numeroCartao) like upper(concat('%', :query, '%')) ) order by 1 asc")
	Collection<CartaoCreditoAutoComplete> autoComplete(@Param("query") String query);
	
}
