package com.example.FoodDeliveryApplication.repository.globals;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import com.example.FoodDeliveryApplication.entities.globals.LoginDetails;

@Repository
public interface LoginDetailsRepository extends CrudRepository<LoginDetails, Integer>{
    public LoginDetails getLoginDetailsByUserName(String userName);
}