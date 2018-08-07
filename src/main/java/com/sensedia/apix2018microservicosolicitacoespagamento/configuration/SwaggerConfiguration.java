package com.sensedia.apix2018microservicosolicitacoespagamento.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

import com.google.common.collect.Sets;
import com.sensedia.apix2018microservicosolicitacoespagamento.SolicitacoesPagamentoApplication;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage(SolicitacoesPagamentoApplication.class.getPackage().getName()))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo())
				.consumes(Sets.newHashSet(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.produces(Sets.newHashSet(MediaType.APPLICATION_JSON_UTF8_VALUE));
	}
	
	private ApiInfo apiInfo() {
	     return new ApiInfo("API Solicitações de pagamento", 
	    		 			"Serviço responsável pela solicitação de pagamentos", 
	    		 			"1.0.0",
	    		 			"http://sensedia.com", 
    		 				"suporte@sensedia.com", 
    		 				null, 
    		 				null);
	}

}