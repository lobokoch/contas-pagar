/**********************************************************************************************
Code generated with MKL Plug-in version: 55.0.3
Copyright: Kerubin - kerubin.platform@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableWebMvc
@Import({ springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration.class })
public class Swagger2Config {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
			.select()
			.apis(RequestHandlerSelectors.basePackage("br.com.kerubin.api"))
			.paths(PathSelectors.any())
			.build()
			.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
			.title("API documentation for financeiro/contas_pagar")
			.description("This API documentation describes all available operations for service /api/financeiro/contas_pagar/*")
			.version("1.0.0")
			.build();
	}

	@Bean
	public WebMvcConfigurer webMvcConfigurer() {
		return new WebMvcConfigurer() {
			
			@Override
			public void addViewControllers(ViewControllerRegistry registry) {
			    registry.addRedirectViewController("/financeiro/contas_pagar/doc/v2/api-docs", "/v2/api-docs");
			    registry.addRedirectViewController("/financeiro/contas_pagar/doc/configuration/ui", "/configuration/ui");
			    registry.addRedirectViewController("/financeiro/contas_pagar/doc/configuration/security", "/configuration/security");
			    registry.addRedirectViewController("/financeiro/contas_pagar/doc/swagger-resources", "/swagger-resources");
			    registry.addRedirectViewController("/financeiro/contas_pagar/doc", "/financeiro/contas_pagar/doc/swagger-ui.html");
			    registry.addRedirectViewController("/financeiro/contas_pagar/doc/", "/financeiro/contas_pagar/doc/swagger-ui.html");
			    
			    registry.addRedirectViewController("/financeiro/contas_pagar/doc/swagger-resources/configuration/ui", "/swagger-resources/configuration/ui");
			    registry.addRedirectViewController("/financeiro/contas_pagar/doc/swagger-resources/configuration/security", "/swagger-resources/configuration/security");
			}

			@Override
			public void addResourceHandlers(ResourceHandlerRegistry registry) {
			    registry
			        .addResourceHandler("/financeiro/contas_pagar/doc/**").addResourceLocations("classpath:/META-INF/resources/");
			}
		};
	}

}

