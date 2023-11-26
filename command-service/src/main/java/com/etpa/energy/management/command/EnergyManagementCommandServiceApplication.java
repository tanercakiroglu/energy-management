package com.etpa.energy.management.command;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.etpa.*"})
public class EnergyManagementCommandServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnergyManagementCommandServiceApplication.class, args);
	}

}
