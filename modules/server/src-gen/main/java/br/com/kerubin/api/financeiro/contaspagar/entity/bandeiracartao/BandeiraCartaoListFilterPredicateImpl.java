/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-22T06:59:30.300
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.bandeiracartao;

import org.springframework.util.CollectionUtils;

import org.springframework.stereotype.Component;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

@Component
public class BandeiraCartaoListFilterPredicateImpl implements BandeiraCartaoListFilterPredicate {
	
	@Override
	public Predicate mountAndGetPredicate(BandeiraCartaoListFilter bandeiraCartaoListFilter) {
		if (bandeiraCartaoListFilter == null) {
			return null;
		}
		
		QBandeiraCartaoEntity qEntity = QBandeiraCartaoEntity.bandeiraCartaoEntity;
		BooleanBuilder where = new BooleanBuilder();
		
		// Begin field: nomeBandeira
		if (!CollectionUtils.isEmpty(bandeiraCartaoListFilter.getNomeBandeira())) {
			BooleanExpression inExpression = qEntity.nomeBandeira.in(bandeiraCartaoListFilter.getNomeBandeira());
			where.and(inExpression);
		}
		// End field: nomeBandeira
		
		return where;
	}

}

