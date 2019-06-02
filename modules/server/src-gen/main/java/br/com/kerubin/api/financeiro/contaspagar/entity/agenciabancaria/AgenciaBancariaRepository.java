/**********************************************************************************************
Code generated with MKL Plug-in version: 3.5.1
Code generated at time stamp: 2019-06-02T06:23:49.436
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.agenciabancaria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import java.util.Collection;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

public interface AgenciaBancariaRepository extends JpaRepository<AgenciaBancariaEntity, java.util.UUID>, QuerydslPredicateExecutor<AgenciaBancariaEntity> {
	
	@Query("select distinct ac.id as id, ac.numeroAgencia as numeroAgencia, ac.digitoAgencia as digitoAgencia, ac.endereco as endereco from AgenciaBancariaEntity ac where ( upper(ac.numeroAgencia) like upper(concat('%', :query, '%')) ) or ( upper(ac.endereco) like upper(concat('%', :query, '%')) ) order by 1 asc")
	Collection<AgenciaBancariaAutoComplete> autoComplete(@Param("query") String query);
	
}
