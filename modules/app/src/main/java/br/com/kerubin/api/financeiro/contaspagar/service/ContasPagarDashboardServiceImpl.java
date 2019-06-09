package br.com.kerubin.api.financeiro.contaspagar.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.DatePath;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import br.com.kerubin.api.financeiro.contaspagar.entity.contapagar.QContaPagarEntity;
import br.com.kerubin.api.financeiro.contaspagar.model.MonthlySum;
import br.com.kerubin.api.financeiro.contaspagar.model.MonthlySumContasPagar;

@Service
public class ContasPagarDashboardServiceImpl implements ContasPagarDashboardService {
	
	private static final Map<String, Integer> MONTHS = new HashMap<>();
	private static final String JANUARY = "january";
	private static final String FEBRUARY = "february";
	private static final String MARCH = "march";
	private static final String APRIL = "april";
	private static final String MAY = "may";
	private static final String JUNE = "june";
	private static final String JULY = "july";
	private static final String AUGUST = "august";
	private static final String SEPTEMBER = "september";
	private static final String OCTOBER = "october";
	private static final String NOVEMBER = "november";
	private static final String DECEMBER = "december";
	
	@PersistenceContext
	private EntityManager em;
	
	private QContaPagarEntity qContaPagar;
	
	public ContasPagarDashboardServiceImpl() {
		qContaPagar = QContaPagarEntity.contaPagarEntity;
		MONTHS.put(JANUARY, 1);
		MONTHS.put(FEBRUARY, 2);
		MONTHS.put(MARCH, 3);
		MONTHS.put(APRIL, 4);
		MONTHS.put(MAY, 5);
		MONTHS.put(JUNE, 6);
		MONTHS.put(JULY, 7);
		MONTHS.put(AUGUST, 8);
		MONTHS.put(SEPTEMBER, 9);
		MONTHS.put(OCTOBER, 10);
		MONTHS.put(NOVEMBER, 11);
		MONTHS.put(DECEMBER, 12);
	}
	
	@Transactional(readOnly = true)
	@Override
	public MonthlySumContasPagar getMonthlySumContasPagar() {
		MonthlySum apagar = getMonthlySum(qContaPagar.dataVencimento, qContaPagar.valor);
		MonthlySum pagas = getMonthlySum(qContaPagar.dataPagamento, qContaPagar.valorPago);
		
		MonthlySumContasPagar result = new MonthlySumContasPagar(apagar, pagas);
		
		return result;
	}
	
	private MonthlySum getMonthlySum(DatePath<LocalDate> datePath, NumberPath<BigDecimal> numberPath) {
		JPAQueryFactory query = new JPAQueryFactory(em);
		
		
		Tuple data = query.selectDistinct(
						buildMonthSumExpression(JANUARY, datePath, numberPath),
						buildMonthSumExpression(FEBRUARY, datePath, numberPath),
						buildMonthSumExpression(MARCH, datePath, numberPath),
						buildMonthSumExpression(APRIL, datePath, numberPath),
						buildMonthSumExpression(MAY, datePath, numberPath),
						buildMonthSumExpression(JUNE, datePath, numberPath),
						buildMonthSumExpression(JULY, datePath, numberPath),
						buildMonthSumExpression(AUGUST, datePath, numberPath),
						buildMonthSumExpression(SEPTEMBER, datePath, numberPath),
						buildMonthSumExpression(OCTOBER, datePath, numberPath),
						buildMonthSumExpression(NOVEMBER, datePath, numberPath),
						buildMonthSumExpression(DECEMBER, datePath, numberPath)
				)
		.from(qContaPagar)
		.fetchOne();
		
		MonthlySum result = new MonthlySum();
		
		if (data != null) {
			result.setJanuary(data.get(0, BigDecimal.class));
			result.setFebruary(data.get(1, BigDecimal.class));
			result.setMarch(data.get(2, BigDecimal.class));
			result.setApril(data.get(3, BigDecimal.class));
			result.setMay(data.get(4, BigDecimal.class));
			result.setJune(data.get(5, BigDecimal.class));
			result.setJuly(data.get(6, BigDecimal.class));
			result.setAugust(data.get(7, BigDecimal.class));
			result.setSeptember(data.get(8, BigDecimal.class));
			result.setOctober(data.get(9, BigDecimal.class));
			result.setNovember(data.get(10, BigDecimal.class));
			result.setDecember(data.get(11, BigDecimal.class));
		}
		
		return result;
	}
	
	private JPQLQuery<BigDecimal> buildMonthSumExpression(String monthName, DatePath<LocalDate> datePath, NumberPath<BigDecimal> numberPath) {
		int year = LocalDate.now().getYear();
		int month = MONTHS.get(monthName);
		
		Predicate yearAndMonthFilter = datePath.year().eq(year).and(datePath.month().eq(month));
		
		JPQLQuery<BigDecimal> result = JPAExpressions
				.select(numberPath.sum().coalesce(BigDecimal.ZERO).as(monthName))
				.from(qContaPagar)
				.where(yearAndMonthFilter);
		
		return result;
	}
	
	/*private JPQLQuery<BigDecimal> buildMonthSumExpression_(String monthName) {
		int year = LocalDate.now().getYear();
		int month = MONTHS.get(monthName);
		
		Predicate yearAndMonthFilter = qContaPagar.dataVencimento.year().eq(year).and(qContaPagar.dataVencimento.month().eq(month));
		
		JPQLQuery<BigDecimal> result = JPAExpressions
			.select(qContaPagar.valor.sum().coalesce(BigDecimal.ZERO).as(monthName))
			.from(qContaPagar)
			.where(yearAndMonthFilter);
		
		return result;
	}*/
	
	
	
	/*@Transactional(readOnly = true)
	//@Override
	public MonthlySum getMonthlySumContasPagar_() {
		JPAQueryFactory query = new JPAQueryFactory(em);
		
		
		MonthlySum result = query.selectDistinct(
				Projections.bean(MonthlySum.class, 
						buildMonthSumExpression("january"),
						buildMonthSumExpression("february"),
						buildMonthSumExpression("march"),
						buildMonthSumExpression("april"),
						buildMonthSumExpression("may"),
						buildMonthSumExpression("june"),
						buildMonthSumExpression("july"),
						buildMonthSumExpression("august"),
						buildMonthSumExpression("september"),
						buildMonthSumExpression("october"),
						buildMonthSumExpression("november"),
						buildMonthSumExpression("december")
						))
				.from(qContaPagar)
				.fetchOne();
		
		return result;
	}*/

}
