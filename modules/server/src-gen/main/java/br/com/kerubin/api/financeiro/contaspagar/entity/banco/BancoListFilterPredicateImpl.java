/**********************************************************************************************
Code generated with MKL Plug-in version: 3.5.1
Code generated at time stamp: 2019-06-01T15:26:20.750
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.banco;

import org.springframework.util.CollectionUtils;

import org.springframework.stereotype.Component;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

@Component
public class BancoListFilterPredicateImpl implements BancoListFilterPredicate {
	
	@Override
	public Predicate mountAndGetPredicate(BancoListFilter bancoListFilter) {
		if (bancoListFilter == null) {
			return null;
		}
		
		QBancoEntity qEntity = QBancoEntity.bancoEntity;
		BooleanBuilder where = new BooleanBuilder();
		
		if (!CollectionUtils.isEmpty(bancoListFilter.getNome())) {
			BooleanExpression inExpression = qEntity.nome.in(bancoListFilter.getNome());
			where.and(inExpression);
		}
		
		return where;
	}

}

