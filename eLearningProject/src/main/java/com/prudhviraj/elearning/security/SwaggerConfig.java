package com.prudhviraj.elearning.security;

import java.util.Collections;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContext;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spi.service.contexts.SecurityContextBuilder;


@Configuration
public class SwaggerConfig {
	@Bean
	public Docket api() { 
	    return new Docket(DocumentationType.SWAGGER_2)
	            .apiInfo(getInfo())
	            //.securityContexts(Collections.singletonList(securityContext()))
	            .securitySchemes(Collections.singletonList(apiKey()))
	            .select()
	            .apis(RequestHandlerSelectors.any())
	            .paths(PathSelectors.any())
	            .build();
	}
	private ApiKey apiKey() {
	    return new ApiKey("apiKey", "X-API-KEY", "header");
	}

//	private SecurityContext securityContext() {
//	    return SecurityContext.builder()
//	            .securityReferences(defaultAuth())
//	            .forPaths(PathSelectors.any())
//	            .build();
//	}

	List<SecurityReference> defaultAuth() {
	    AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
	    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
	    authorizationScopes[0] = authorizationScope;
	    return Collections.singletonList(new SecurityReference("apiKey", authorizationScopes));
	}
	
//	@Bean
//    public Docket api() { 
//        return new Docket(DocumentationType.SWAGGER_2)
//        		.apiInfo(getInfo())
//        		.securityContexts(null)
//        		.securitySchemes(null)
//        		.select()
//        		.apis(RequestHandlerSelectors.any())
//        		.paths(PathSelectors.any())
//        		.build();
//        		
//                                         
//    }

	private ApiInfo getInfo() {
		
		return new ApiInfo("E-Learning Application : using java"," This project is developed by BAVIGADDA PRUDHVIRAJ", "1.0", "terms of services",
				new Contact("Bavigadda Prudhviraj","http://localhost:9091/swagger-ui/index.html#/","prudhviraj729@gmail.com"), "license of APIS", "API license URL",Collections.emptyList());
	}
	

}
