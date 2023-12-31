package com.example.FoodDeliveryApplication.repository.Resturant;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.FoodDeliveryApplication.entities.Resturant.Resturant;

@Repository
public interface ResturantRepository extends CrudRepository<Resturant, Integer> {
    public List<Resturant> getResturantByPincode(String pincode);

    public List<Resturant> getResturantByPincodeAndApproved(String pincode, boolean approved);

    public Resturant getResturantByEmail(String email);

    //getResturantByPincode and available==true change to be introduced
}
