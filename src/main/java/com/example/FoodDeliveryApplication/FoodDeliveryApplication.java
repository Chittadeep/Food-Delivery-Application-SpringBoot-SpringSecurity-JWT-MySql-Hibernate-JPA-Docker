package com.example.FoodDeliveryApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FoodDeliveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodDeliveryApplication.class, args);
		//new Thread(new AssignRiderService()).start();
	}

	// @Bean
	// public SessionFactory getSessionFactory(HibernateJPASessionFactoryBean hibernateEntityManagerFactory)
	// {
		
	// }
}
