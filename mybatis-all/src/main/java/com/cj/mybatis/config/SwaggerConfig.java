package com.cj.mybatis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket docket(Environment env) {
		Profiles profiles = Profiles.of("dev");
        boolean flag = env.acceptsProfiles(profiles);
        return new Docket(DocumentationType.SWAGGER_2)
        		.apiInfo(apiInfo())
        		.enable(flag)
        		.select()
                .apis(RequestHandlerSelectors.basePackage("com.cj.mybatis.controller"))
                //.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                //.paths(PathSelectors.any())
                .build();
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Swagger Demo")
                .description("This for swagger demo")
                //.termsOfServiceUrl("http://blog.csdn.net/saytime")
                .version("1.0")
                .build();
	}
}
