package com.example.FoodDeliveryApplication;

import org.hibernate.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FoodDeliveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodDeliveryApplication.class, args);
	}

	// @Bean
	// public SessionFactory getSessionFactory(HibernateJPASessionFactoryBean hibernateEntityManagerFactory)
	// {
		
	// }
}
