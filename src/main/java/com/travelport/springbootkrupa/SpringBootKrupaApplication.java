package com.travelport.springbootkrupa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2 // it enables the swagger to be created for the application
@SpringBootApplication
@ComponentScan(basePackages = "com.travelport")
public class SpringBootKrupaApplication {

  public static void main(String[] args) {
    // cacerts has all ssl certificates for the endpoints defined in the controllers for the app
    System.setProperty("javax.net.ssl.trustStore", "src/main/resources/cacerts");
    System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
    SpringApplication.run(SpringBootKrupaApplication.class, args);
  }
}
