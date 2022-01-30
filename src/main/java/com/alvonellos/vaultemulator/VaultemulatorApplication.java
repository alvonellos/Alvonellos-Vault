package com.alvonellos.vaultemulator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class VaultemulatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(VaultemulatorApplication.class, args);
	}

}
