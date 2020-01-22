/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-22T06:59:30.300
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.contabancaria;

import org.springframework.util.CollectionUtils;

import org.springframework.stereotype.Component;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

@Component
public class ContaBancariaListFilterPredicateImpl implements ContaBancariaListFilterPredicate {
	
	@Override
	public Predicate mountAndGetPredicate(ContaBancariaListFilter contaBancariaListFilter) {
		if (contaBancariaListFilter == null) {
			return null;
		}
		
		QContaBancariaEntity qEntity = QContaBancariaEntity.contaBancariaEntity;
		BooleanBuilder where = new BooleanBuilder();
		
		// Begin field: numeroConta
		if (!CollectionUtils.isEmpty(contaBancariaListFilter.getNumeroConta())) {
			BooleanExpression inExpression = qEntity.numeroConta.in(contaBancariaListFilter.getNumeroConta());
			where.and(inExpression);
		}
		// End field: numeroConta
		
		return where;
	}

}

