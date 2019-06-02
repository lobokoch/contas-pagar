/**********************************************************************************************
Code generated with MKL Plug-in version: 3.5.1
Code generated at time stamp: 2019-06-02T06:23:49.436
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.contapagar;

import org.springframework.util.CollectionUtils;

import org.springframework.stereotype.Component;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

@Component
public class ContaPagarListFilterPredicateImpl implements ContaPagarListFilterPredicate {
	
	@Override
	public Predicate mountAndGetPredicate(ContaPagarListFilter contaPagarListFilter) {
		if (contaPagarListFilter == null) {
			return null;
		}
		
		QContaPagarEntity qEntity = QContaPagarEntity.contaPagarEntity;
		BooleanBuilder where = new BooleanBuilder();
		
		if (!CollectionUtils.isEmpty(contaPagarListFilter.getDescricao())) {
			BooleanExpression inExpression = qEntity.descricao.in(contaPagarListFilter.getDescricao());
			where.and(inExpression);
		}
		
		java.time.LocalDate fieldFrom = contaPagarListFilter.getDataVencimentoFrom();
		java.time.LocalDate fieldTo = contaPagarListFilter.getDataVencimentoTo();
		
		if (fieldFrom != null && fieldTo != null) {
			if (fieldFrom.isAfter(fieldTo)) {
				throw new IllegalArgumentException("Valor de \"Vencimento de\" não pode ser maior do que valor de \"Até\".");
			}
			
			BooleanExpression between = qEntity.dataVencimento.between(fieldFrom, fieldTo);
			where.and(between);
		}
		else {
			if (fieldFrom != null) {
				where.and(qEntity.dataVencimento.goe(fieldFrom));
			}
			else if (fieldTo != null) {
				where.and(qEntity.dataVencimento.loe(fieldTo));				
			}
		}
		
		if ( ! (contaPagarListFilter.isDataPagamentoIsNull() && contaPagarListFilter.isDataPagamentoIsNotNull()) ) {
					
			if (contaPagarListFilter.isDataPagamentoIsNull()) {
				where.and(qEntity.dataPagamento.isNull());
			}
			else {
				where.and(qEntity.dataPagamento.isNotNull());				
			}
			
			if (contaPagarListFilter.isDataPagamentoIsNotNull()) {
				where.and(qEntity.dataPagamento.isNotNull());
			}
			else {
				where.and(qEntity.dataPagamento.isNull());				
			}
			
		}
		
		if (!CollectionUtils.isEmpty(contaPagarListFilter.getAgrupador())) {
			BooleanExpression inExpression = qEntity.agrupador.in(contaPagarListFilter.getAgrupador());
			where.and(inExpression);
		}
		
		return where;
	}

}

