package com.example.FoodDeliveryApplication.repository.Resturant;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.FoodDeliveryApplication.entities.Resturant.Resturant;

@Repository
public interface ResturantRepository extends CrudRepository<Resturant, Integer> {
    
}
