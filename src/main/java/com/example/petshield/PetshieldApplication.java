package com.example.petshield;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
//@OpenAPIDefinition(servers = {@Server(url = "https://dev.pet-shield.shop", description = "도메인 설명")})

public class PetshieldApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetshieldApplication.class, args);
	}

}
