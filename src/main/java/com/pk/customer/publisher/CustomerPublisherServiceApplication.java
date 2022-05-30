package com.pk.customer.publisher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Customer API", version = "2.0", description = "Customer Information"))
@ComponentScan(basePackages = "com.pk.customer.publisher")
public class CustomerPublisherServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerPublisherServiceApplication.class, args);
	}

}
