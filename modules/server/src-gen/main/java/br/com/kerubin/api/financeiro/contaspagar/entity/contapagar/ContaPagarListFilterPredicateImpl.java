/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-13T08:12:17.669
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
		
		// Begin field: descricao
		if (!CollectionUtils.isEmpty(contaPagarListFilter.getDescricao())) {
			BooleanExpression inExpression = qEntity.descricao.in(contaPagarListFilter.getDescricao());
			where.and(inExpression);
		}
		// End field: descricao
		
		
		// Begin field: DataVencimento
		java.time.LocalDate fieldFromDataVencimento = contaPagarListFilter.getDataVencimentoFrom();
		java.time.LocalDate fieldToDataVencimento = contaPagarListFilter.getDataVencimentoTo();
		
		if (fieldFromDataVencimento != null && fieldToDataVencimento != null) {
			if (fieldFromDataVencimento.isAfter(fieldToDataVencimento)) {
				throw new IllegalArgumentException("Valor de \"Vencimento de\" não pode ser maior do que valor de \"Até\".");
			}
			
			BooleanExpression between = qEntity.dataVencimento.between(fieldFromDataVencimento, fieldToDataVencimento);
			where.and(between);
		}
		else {
			if (fieldFromDataVencimento != null) {
				where.and(qEntity.dataVencimento.goe(fieldFromDataVencimento));
			}
			else if (fieldToDataVencimento != null) {
				where.and(qEntity.dataVencimento.loe(fieldToDataVencimento));				
			}
		}
		// End field: DataVencimento
		
		
		// Begin field: Valor
		java.math.BigDecimal fieldFromValor = contaPagarListFilter.getValorFrom();
		java.math.BigDecimal fieldToValor = contaPagarListFilter.getValorTo();
		
		if (fieldFromValor != null && fieldToValor != null) {
			
			BooleanExpression between = qEntity.valor.between(fieldFromValor, fieldToValor);
			where.and(between);
		}
		else {
			if (fieldFromValor != null) {
				where.and(qEntity.valor.goe(fieldFromValor));
			}
			else if (fieldToValor != null) {
				where.and(qEntity.valor.loe(fieldToValor));				
			}
		}
		// End field: Valor
		
		// Begin field: formaPagamento
		if (contaPagarListFilter.getFormaPagamento() != null) {
			BooleanExpression formaPagamentoIsEqualTo = qEntity.formaPagamento.eq(contaPagarListFilter.getFormaPagamento());
			where.and(formaPagamentoIsEqualTo);
		}
		// End field: formaPagamento
		
		// Begin field: DataPagamento
		if ( ! (contaPagarListFilter.isDataPagamentoIsNull() && contaPagarListFilter.isDataPagamentoIsNotNull()) ) {
			
			if (contaPagarListFilter.getDataPagamentoIsNull() != null) {
				if (contaPagarListFilter.isDataPagamentoIsNull()) {
					where.and(qEntity.dataPagamento.isNull());
				}
				else {
					where.and(qEntity.dataPagamento.isNotNull());
				}
			}
			
			if (contaPagarListFilter.getDataPagamentoIsNotNull() != null) {
				if (contaPagarListFilter.isDataPagamentoIsNotNull()) {
					where.and(qEntity.dataPagamento.isNotNull());
				}
				else {
					where.and(qEntity.dataPagamento.isNull());
				}
			}
			
		}
		// End field: DataPagamento
		
		// Begin field: IdConcBancaria
		if (contaPagarListFilter.getIdConcBancariaIsNotNull() != null) {		
			if (contaPagarListFilter.isIdConcBancariaIsNotNull()) {
				where.and(qEntity.idConcBancaria.isNotNull());
			}
			else {
				where.and(qEntity.idConcBancaria.isNull());
			}
		}
		// End field: IdConcBancaria
		
		// Begin field: histConcBancaria
		if (!CollectionUtils.isEmpty(contaPagarListFilter.getHistConcBancaria())) {
			BooleanExpression inExpression = qEntity.histConcBancaria.in(contaPagarListFilter.getHistConcBancaria());
			where.and(inExpression);
		}
		// End field: histConcBancaria
		
		// Begin field: agrupador
		if (!CollectionUtils.isEmpty(contaPagarListFilter.getAgrupador())) {
			BooleanExpression inExpression = qEntity.agrupador.in(contaPagarListFilter.getAgrupador());
			where.and(inExpression);
		}
		// End field: agrupador
		
		return where;
	}

}

