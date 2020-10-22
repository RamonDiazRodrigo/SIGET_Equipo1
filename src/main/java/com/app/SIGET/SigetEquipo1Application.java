package com.app.SIGET;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.app.SIGET.http"})
public class SigetEquipo1Application {

	public static void main(String[] args) {
		SpringApplication.run(SigetEquipo1Application.class, args);
	}

}
