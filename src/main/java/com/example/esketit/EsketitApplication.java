package com.example.esketit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class EsketitApplication {

	public static void main(String[] args) {
		SpringApplication.run(EsketitApplication.class, args);
	}

}
