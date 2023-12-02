package com.caracol.organizacion_empresarial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class CaracolMicroserviceOrganizacionEmpresarialApplication {

	public static void main(String[] args) {
		SpringApplication.run(CaracolMicroserviceOrganizacionEmpresarialApplication.class, args);
	}

}
