/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-22T06:59:30.300
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.cartaocredito;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import java.util.Collection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;

@Transactional(readOnly = true)
public interface CartaoCreditoRepository extends JpaRepository<CartaoCreditoEntity, java.util.UUID>, QuerydslPredicateExecutor<CartaoCreditoEntity> {
	
	// WARNING: supports only where clause with like for STRING fields. For relationships entities will get the first string autocomplete key field name.
	@Query("select distinct ac.id as id, ac.nomeTitular as nomeTitular, ac.numeroCartao as numeroCartao from CartaoCreditoEntity ac where ( upper(ac.nomeTitular) like upper(concat('%', :query, '%')) ) or ( upper(ac.numeroCartao) like upper(concat('%', :query, '%')) ) order by 1 asc")
	Collection<CartaoCreditoAutoComplete> autoComplete(@Param("query") String query);
}
