 package com.jsp.medlife;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MedLifeApplication {

	public static void main(String[] args) 
	{
		SpringApplication.run(MedLifeApplication.class, args);
		
		System.out.println("med-life started...");
	}

} 
