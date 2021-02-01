package com.sergio.app.item;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
public class AppConfig {

	/**
	 * Cliente para trabajar con Api rest
	 * para acceder a otros microservicios
	 * @return
	 */
	@Bean("ClientRest")
	@LoadBalanced
	public RestTemplate registerTemplate() {
		return new RestTemplate();
	}
}
