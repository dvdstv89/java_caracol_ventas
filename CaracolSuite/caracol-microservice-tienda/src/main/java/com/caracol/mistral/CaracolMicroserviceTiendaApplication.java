package com.caracol.mistral;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class CaracolMicroserviceTiendaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CaracolMicroserviceTiendaApplication.class, args);
	}

}
