/**********************************************************************************************
Code generated by MKL Plug-in
Copyright: Kerubin - kerubin.platform@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.contapagarmultiple;

import org.springframework.util.CollectionUtils;

import org.springframework.stereotype.Component;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

@Component
public class ContaPagarMultipleListFilterPredicateImpl implements ContaPagarMultipleListFilterPredicate {
	
	@Override
	public Predicate mountAndGetPredicate(ContaPagarMultipleListFilter contaPagarMultipleListFilter) {
		if (contaPagarMultipleListFilter == null) {
			return null;
		}
		
		QContaPagarMultipleEntity qEntity = QContaPagarMultipleEntity.contaPagarMultipleEntity;
		BooleanBuilder where = new BooleanBuilder();
		
		// Begin field: contaPagarId
		if (contaPagarMultipleListFilter.getContaPagarId() != null) {
			BooleanExpression contaPagarIdIsEqualTo = qEntity.contaPagar.id.eq(contaPagarMultipleListFilter.getContaPagarId());
			where.and(contaPagarIdIsEqualTo);
		}
		// End field: contaPagarId
		
		// Begin field: IdConcBancaria
		if (contaPagarMultipleListFilter.getIdConcBancariaIsNotNull() != null) {		
			if (contaPagarMultipleListFilter.isIdConcBancariaIsNotNull()) {
				where.and(qEntity.idConcBancaria.isNotNull());
			}
			else {
				where.and(qEntity.idConcBancaria.isNull());
			}
		}
		// End field: IdConcBancaria
		
		// Begin field: histConcBancaria
		if (!CollectionUtils.isEmpty(contaPagarMultipleListFilter.getHistConcBancaria())) {
			BooleanExpression inExpression = qEntity.histConcBancaria.in(contaPagarMultipleListFilter.getHistConcBancaria());
			where.and(inExpression);
		}
		// End field: histConcBancaria
		
		return where;
	}

}
