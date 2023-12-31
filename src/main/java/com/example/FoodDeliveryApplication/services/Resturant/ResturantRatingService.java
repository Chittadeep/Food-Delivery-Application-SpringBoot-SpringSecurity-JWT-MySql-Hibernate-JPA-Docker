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
        return resturantRatingRepository.findById(resturantRatingId).orElseThrow(()->new RuntimeException("No resturant rating exists with this id"));
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

    public ResturantRating completeResturantRating(int resturantRatingId, int rating)
    {
        ResturantRating oldResturantRating = getResturantRatingById(resturantRatingId);
        if(rating>0 && rating<6)
        {
            oldResturantRating.setRating(rating);
            oldResturantRating.setCompleted(true);
            return resturantRatingRepository.save(oldResturantRating);    
        }
        throw new RuntimeException("the rating should be within range from 0 to 5");
    }
}
