/**********************************************************************************************
Code generated with MKL Plug-in version: 27.0.12
Code generated at time stamp: 2019-11-06T06:23:00.711
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.conciliacaobancaria;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import org.apache.commons.lang3.RandomStringUtils;
import javax.inject.Inject;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.data.domain.AuditorAware;
import br.com.kerubin.api.database.entity.AuditorAwareImpl;
import org.springframework.context.annotation.Bean;


@TestPropertySource(locations = "classpath:conciliacao-bancaria.properties")
@DataJpaTest
@EnableJpaAuditing(auditorAwareRef="auditorAware")
public class FinanceiroContasPagarBaseEntityTest {
	protected LocalDate lastDate = LocalDate.now();
	
	@Inject
	protected TestEntityManager em;
	
	// BEGIN base configurations
	@TestConfiguration
	static class FinanceiroContasPagarBaseEntityTestConfig {
		
		@Bean
		public AuditorAware<String> auditorAware() {
			return new AuditorAwareImpl();
		}
		
		@Bean
		public br.com.kerubin.api.servicecore.mapper.ObjectMapper objectMapper() {
			return new br.com.kerubin.api.servicecore.mapper.ObjectMapper();
		}
	}
	// END base configurations
	
	protected LocalDate getNextDate() {
		if (lastDate == null) {
			lastDate = LocalDate.now();
		}
		LocalDate result = LocalDate.of(lastDate.getYear(), lastDate.getMonth(), lastDate.getDayOfMonth());
		lastDate = lastDate.plusDays(1);
		
		return result;
		
	}
	
	protected void resetNextDate() {
		lastDate = null;
	}
	
	protected String generateRandomString(int maxLength) {
		int length = (maxLength > 30) ? 30 : maxLength; 
		String chars = RandomStringUtils.randomAlphabetic(length - 1) + " ";
		String value = RandomStringUtils.random(length, chars).trim();
		
		// Must remove white spaces in the begining.
		int attempts= 0;
		while (value.length() < length && (attempts < Integer.MAX_VALUE) ) {
			attempts++;
			value = RandomStringUtils.random(length, chars).trim();
		} 
		return value;
	}
	
	protected <T> List<T> getRandomItemsOf(List<T> list, int size) {
		if (list == null || size <= 0) {
			return Collections.emptyList();
		}
		List<T> result = new ArrayList<>();
		Random ran = new Random();
		int bound = list.size();
		int attempts = 0;
		do {
			attempts++;
			int index = ran.nextInt(bound);
			T item = list.get(index);
			if (!result.contains(item)) {
				result.add(item);
			}
		} while (result.size() < size && (attempts < Integer.MAX_VALUE));
		
		return result;
		
	}
	

}
