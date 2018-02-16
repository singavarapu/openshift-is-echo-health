package com.travelport.springbootkrupa;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.any;

@Configuration
@EnableSwagger2 // the two annotations has to be defined to identify this config at compile time
public class SwaggerConfig {

  @Bean
  public Docket greetingApi() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select().apis(RequestHandlerSelectors.basePackage("com.travelport"))
        .paths(any())
        .build()
        .apiInfo(metaData());
  }

  private ApiInfo metaData() {
    return new ApiInfo(
        "Travelport GreetingService REST API",
        "Spring Boot REST API for Travel Content",
        "1.0",
        "Terms of service",
        new Contact(
            "Krupa Singavarapu",
            "https://springframework.guru/about/",
            "krupa.singavarapu@travelport.com"),
        "Apache License Version 2.0",
        "https://www.apache.org/licenses/LICENSE-2.0");
  }
}
