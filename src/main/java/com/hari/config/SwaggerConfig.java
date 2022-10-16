package com.hari.config;

import java.util.Collection;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
	
	@Bean
	   public Docket productApi() {
	      return new Docket(DocumentationType.SWAGGER_2).select().paths(PathSelectors.ant("/v1/sms/*"))
	         .apis(RequestHandlerSelectors.basePackage("com.hari")).build().apiInfo(apiDetails());
	   }

	private ApiInfo apiDetails()
	{
		return new ApiInfo("SMS-API", 
				"This API can send SMS , OTP and Verify OTP", 
				"1.0", 
				"Free to use", 
				new Contact("klinton", "https://www.instagram.com/frozen_mind330", "klinton@gmail.com"), 
				"API Licence", "https://www.instagram.com/frozen_mind330", Collections.emptySet());
	}
}
