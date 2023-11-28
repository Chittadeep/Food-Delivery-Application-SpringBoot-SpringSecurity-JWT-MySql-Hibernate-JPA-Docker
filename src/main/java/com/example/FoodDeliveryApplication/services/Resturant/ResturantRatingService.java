package com.example.FoodDeliveryApplication.services.Resturant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import com.example.FoodDeliveryApplication.entities.Resturant.ResturantRating;
import com.example.FoodDeliveryApplication.exceptions.EntityDoesNotExistException;
import com.example.FoodDeliveryApplication.repository.Resturant.ResturantRatingRepository;

@Service
public class ResturantRatingService {
    @Autowired
    ResturantRatingRepository resturantRatingRepository;

    public List<ResturantRating> getAllResturantRating()
    {
        return (List<ResturantRating>) resturantRatingRepository.findAll();
    }

    public ResturantRating createResturantRating(ResturantRating resturantRating)
    {
        resturantRatingRepository.save(resturantRating);
        return resturantRating;
    }

    public ResturantRating getResturantRatingById(int resturantRatingId)
    {
        return resturantRatingRepository.findById(resturantRatingId).orElseThrow(()->new EntityDoesNotExistException("No resturant rating exists with this id"));
    }

    public List<ResturantRating> getResturantRatingByUserId(int userId)
    {
        return resturantRatingRepository.getResturantRatingByUser_UserId(userId);
    }

    public List<ResturantRating> getResturantRatingByResturantId(int resturantID)
    {
        return resturantRatingRepository.getResturantRatingByResturant_ResturantId(resturantID);
    }

    public ResturantRating getResturantRatingByResturantIdAndUserId(int resturantID, int userId)
    {
        return resturantRatingRepository.getResturantRatingByResturant_ResturantIdAndUser_UserId(resturantID, userId);
    }
}
