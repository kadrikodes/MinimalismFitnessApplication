package com.health.MinimalismFitnessApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MinimalismFitnessAppApplication {
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(MinimalismFitnessAppApplication.class, args);

		Populator populator = context.getBean(Populator.class);
		populator.populate();
	}

}
