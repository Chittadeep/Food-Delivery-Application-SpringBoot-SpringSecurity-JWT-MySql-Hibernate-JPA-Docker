package com.example.FoodDeliveryApplication.repository.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.FoodDeliveryApplication.entities.User.User;


@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    
}
