package com.example.FoodDeliveryApplication.repository.Resturant;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.FoodDeliveryApplication.entities.Resturant.ResturantRating;

@Repository
public interface ResturantRatingRepository extends CrudRepository<ResturantRating, Integer> {
    
    public List<ResturantRating> getResturantRatingByUser_UserId(int userId);
    
    public List<ResturantRating> getResturantRatingByResturant_ResturantId(int resturantId);

    public ResturantRating getResturantRatingByResturant_ResturantIdAndUser_UserId(int resturantId, int userId);
}
