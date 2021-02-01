package com.sergio.app.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan({"com.sergio.app.users.commons.models.entity"})
@SpringBootApplication
public class ServiceUsersApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceUsersApplication.class, args);
	}

}
