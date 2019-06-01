/**********************************************************************************************
Code generated with MKL Plug-in version: 3.5.1
Code generated at time stamp: 2019-06-01T15:26:20.750
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.planoconta;

import org.springframework.util.CollectionUtils;

import org.springframework.stereotype.Component;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

@Component
public class PlanoContaListFilterPredicateImpl implements PlanoContaListFilterPredicate {
	
	@Override
	public Predicate mountAndGetPredicate(PlanoContaListFilter planoContaListFilter) {
		if (planoContaListFilter == null) {
			return null;
		}
		
		QPlanoContaEntity qEntity = QPlanoContaEntity.planoContaEntity;
		BooleanBuilder where = new BooleanBuilder();
		
		if (!CollectionUtils.isEmpty(planoContaListFilter.getCodigo())) {
			BooleanExpression inExpression = qEntity.codigo.in(planoContaListFilter.getCodigo());
			where.and(inExpression);
		}
		
		if (!CollectionUtils.isEmpty(planoContaListFilter.getDescricao())) {
			BooleanExpression inExpression = qEntity.descricao.in(planoContaListFilter.getDescricao());
			where.and(inExpression);
		}
		
		if ( ! (planoContaListFilter.isAtivoIsNull() && planoContaListFilter.isAtivoIsNotNull()) ) {
					
			if (planoContaListFilter.isAtivoIsNull()) {
				where.and(qEntity.ativo.isNull());
			}
			else {
				where.and(qEntity.ativo.isNotNull());				
			}
			
			if (planoContaListFilter.isAtivoIsNotNull()) {
				where.and(qEntity.ativo.isNotNull());
			}
			else {
				where.and(qEntity.ativo.isNull());				
			}
			
		}
		
		return where;
	}

}

