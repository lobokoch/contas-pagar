/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-22T06:59:30.300
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
@ConfigurationProperties("kerubin.web")
@Getter
@Setter
@ToString
public class ServiceConfig {
	
	private static final String ALLOW_ORIGINS = "http://localhost:4200";
	
	private String allowOrigin;
	private boolean enableHttps;
	
	public ServiceConfig() {
		this.allowOrigin = ALLOW_ORIGINS;
	}

}

