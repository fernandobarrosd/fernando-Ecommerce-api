package com.fernando.fernando_ecommerce_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;

@SpringBootApplication
@OpenAPIDefinition(servers = {@Server(url = "/", description = "URL Default")})
public class FernandoEcommerceApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FernandoEcommerceApiApplication.class, args);
	}

}