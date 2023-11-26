package com.etpa.energy.management.query;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.etpa.*"})
public class EnergyManagementQueryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnergyManagementQueryServiceApplication.class, args);
	}

}
