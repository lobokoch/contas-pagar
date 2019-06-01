package br.com.kerubin.api.financeiro.contaspagar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import br.com.kerubin.api.database.core.ServiceContext;

@SpringBootApplication(
		exclude = { 
		        DataSourceAutoConfiguration.class,
		        HibernateJpaAutoConfiguration.class,
		        DataSourceTransactionManagerAutoConfiguration.class
		}
		, scanBasePackages = { "br.com.kerubin.api" }
)

@EnableEurekaClient
public class FinanceiroContasPagarApplication {

	public static void main(String[] args) {
		init();
		SpringApplication.run(FinanceiroContasPagarApplication.class, args);
	}
	
	private static void init() {
		ServiceContext.setDefaultDomain(FinanceiroContasPagarConstants.DOMAIN);
		ServiceContext.setDefaultService(FinanceiroContasPagarConstants.SERVICE);
		// ServiceConnectionProvider.INSTANCE.setMigrateDefaultTenant(true);
	}
}
