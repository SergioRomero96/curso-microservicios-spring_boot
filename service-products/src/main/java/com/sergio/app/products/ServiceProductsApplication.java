package com.sergio.app.products;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@EntityScan({"com.sergio.app.commons.models.entities"})
public class ServiceProductsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceProductsApplication.class, args);
	}

}
