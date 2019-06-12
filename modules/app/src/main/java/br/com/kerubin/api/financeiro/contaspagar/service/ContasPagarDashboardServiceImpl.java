package br.com.kerubin.api.financeiro.contaspagar.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Coalesce;
import com.querydsl.core.types.dsl.DatePath;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.core.types.Expression;

import br.com.kerubin.api.financeiro.contaspagar.entity.contapagar.QContaPagarEntity;
import br.com.kerubin.api.financeiro.contaspagar.model.ContasPagarSituacaoDoAnoSum;
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
	
	@Transactional(readOnly = true)
	@Override
	public ContasPagarSituacaoDoAnoSum getContasPagarSituacaoDoAno() {
		JPAQueryFactory query = new JPAQueryFactory(em);
		
		Tuple data = query
				.selectDistinct(getContasPagarSituacaoDoAnoSumFieldsExpression().toArray(new Expression<?>[0]))
				.from(qContaPagar)
				.fetchOne();
		
		ContasPagarSituacaoDoAnoSum result = new ContasPagarSituacaoDoAnoSum();
		
		if (data != null) {
			result.setValorVencido(data.get(0, BigDecimal.class));
			result.setValorVenceHoje(data.get(1, BigDecimal.class));
			result.setValorVenceAmanha(data.get(2, BigDecimal.class));
			result.setValorVenceProximos7Dias(data.get(3, BigDecimal.class));
			result.setValorVenceMesAtual(data.get(4, BigDecimal.class));
			result.setValorVenceProximoMes(data.get(5, BigDecimal.class));
			result.setValorPagoMesAtual(data.get(6, BigDecimal.class));
			result.setValorPagoMesAnterior(data.get(7, BigDecimal.class));
		}
		
		return result;
	}
	
	private List<Expression<?>> getContasPagarSituacaoDoAnoSumFieldsExpression() {
		List<Expression<?>> result = new ArrayList<>();
		
		LocalDate today = LocalDate.now();
		int year = today.getYear();
		int month = today.getMonthValue();
		LocalDate monthStart = today.withDayOfMonth(1);
		LocalDate monthEnd = today.withDayOfMonth(today.lengthOfMonth());
		
		Coalesce<BigDecimal> valorSumCoalesce = qContaPagar.valor.sum().coalesce(BigDecimal.ZERO);
		Coalesce<BigDecimal> valorPagoSumCoalesce = qContaPagar.valorPago.sum().coalesce(BigDecimal.ZERO);
		BooleanExpression dataPagamentoIsNull = qContaPagar.dataPagamento.isNull();
		BooleanExpression dataPagamentoIsNotNull = qContaPagar.dataPagamento.isNotNull();
		DatePath<LocalDate> dataVencimento = qContaPagar.dataVencimento;
		DatePath<LocalDate> dataPagamento = qContaPagar.dataPagamento;
		
		// valorVencido
		JPQLQuery<BigDecimal> valorVencido = JPAExpressions
				.select(valorSumCoalesce.as("valorVencido"))
				.from(qContaPagar)
				.where(dataVencimento.lt(today).and(dataPagamentoIsNull));
		result.add(valorVencido);
		
		// valorVenceHoje
		JPQLQuery<BigDecimal> valorVenceHoje = JPAExpressions
				.select(valorSumCoalesce.as("valorVenceHoje"))
				.from(qContaPagar)
				.where(dataVencimento.eq(today).and(dataPagamentoIsNull));
		result.add(valorVenceHoje);
		
		// valorVenceAmanha
		JPQLQuery<BigDecimal> valorVenceAmanha = JPAExpressions
				.select(valorSumCoalesce.as("valorVenceAmanha"))
				.from(qContaPagar)
				.where(dataVencimento.eq(today.plusDays(1)).and(dataPagamentoIsNull));
		result.add(valorVenceAmanha);
		
		// valorVenceProximos7Dias
		JPQLQuery<BigDecimal> valorVenceProximos7Dias = JPAExpressions
				.select(valorSumCoalesce.as("valorVenceProximos7Dias"))
				.from(qContaPagar)
				.where(dataVencimento.between(today, today.plusDays(7)).and(dataPagamentoIsNull));
		result.add(valorVenceProximos7Dias);
		
		// valorVenceMesAtual
		JPQLQuery<BigDecimal> valorVenceMesAtual = JPAExpressions
				.select(valorSumCoalesce.as("valorVenceMesAtual"))
				.from(qContaPagar)
				.where(dataVencimento.between(monthStart, monthEnd).and(dataPagamentoIsNull));
		result.add(valorVenceMesAtual);
		
		// valorVenceProximoMes
		LocalDate nextMonth = today.plusMonths(1);
		LocalDate nextMonthStart = nextMonth.withDayOfMonth(1);
		LocalDate nextMonthEnd = nextMonth.withDayOfMonth(nextMonth.lengthOfMonth());
		JPQLQuery<BigDecimal> valorVenceProximoMes = JPAExpressions
				.select(valorSumCoalesce.as("valorVenceProximoMes"))
				.from(qContaPagar)
				.where(dataVencimento.between(nextMonthStart, nextMonthEnd).and(dataPagamentoIsNull));
		result.add(valorVenceProximoMes);
		
		// valorPagoMesAtual
		JPQLQuery<BigDecimal> valorPagoMesAtual = JPAExpressions
				.select(valorPagoSumCoalesce.as("valorPagoMesAtual"))
				.from(qContaPagar)
				.where(dataPagamento.between(monthStart, monthEnd).and(dataPagamentoIsNotNull));
		result.add(valorPagoMesAtual);
		
		// valorPagoMesAnterior
		LocalDate previousMonth = today.minusMonths(1);
		LocalDate previousMonthStart = previousMonth.withDayOfMonth(1);
		LocalDate previousMonthEnd = previousMonth.withDayOfMonth(previousMonth.lengthOfMonth());
		JPQLQuery<BigDecimal> valorPagoMesAnterior = JPAExpressions
				.select(valorPagoSumCoalesce.as("valorPagoMesAnterior"))
				.from(qContaPagar)
				.where(dataPagamento.between(previousMonthStart, previousMonthEnd).and(dataPagamentoIsNotNull));
		result.add(valorPagoMesAnterior);
		
		return result;
	}

}
