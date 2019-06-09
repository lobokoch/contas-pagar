/**********************************************************************************************
Code generated with MKL Plug-in version: 3.9.0
Code generated at time stamp: 2019-06-08T10:32:58.121
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.fornecedor;

import org.springframework.util.CollectionUtils;

import org.springframework.stereotype.Component;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

@Component
public class FornecedorListFilterPredicateImpl implements FornecedorListFilterPredicate {
	
	@Override
	public Predicate mountAndGetPredicate(FornecedorListFilter fornecedorListFilter) {
		if (fornecedorListFilter == null) {
			return null;
		}
		
		QFornecedorEntity qEntity = QFornecedorEntity.fornecedorEntity;
		BooleanBuilder where = new BooleanBuilder();
		
		if (!CollectionUtils.isEmpty(fornecedorListFilter.getNome())) {
			BooleanExpression inExpression = qEntity.nome.in(fornecedorListFilter.getNome());
			where.and(inExpression);
		}
		
		return where;
	}

}

