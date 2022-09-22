/*
package com.platform.yourregimen.cloud.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

@Configuration
public class GatewayConfig {

	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder) {
		return builder.routes()

				.route("ADMIN", r -> r.path("/usuarios/**").uri("https://microservice--admin.herokuapp.com/"))
				.route("DIET", r -> r.path("/service-diet/** ").uri("https://microservice-diet.herokuapp.com/"))
				.route("FINANCE", r -> r.path("/financeiro/**").uri("https://microservice-finance.herokuapp.com/"))
				.route("PACIENT", r -> r.path("/pacientes/**").uri("https://microservice-pacient.herokuapp.com/"))
				.build();
	}
}
*/
