package com.example.FoodDeliveryApplication;

import org.hibernate.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.FoodDeliveryApplication.services.AssignRiderService;

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
