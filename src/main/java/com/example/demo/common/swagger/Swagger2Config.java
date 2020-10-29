package com.example.demo.common.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {

	private boolean swagger_is_enable=true;

    @Bean
    public Docket controllerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
        		.enable(swagger_is_enable)
                .apiInfo(new ApiInfoBuilder()
                        .title("用户系统接口文档")
                        .description("用户系统接口文档")
                        .contact(new Contact("A", null, null))
                        .version("版本号:1.0")
                        .build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.fzm"))
                .paths(PathSelectors.any())
                .build();
    }
}
