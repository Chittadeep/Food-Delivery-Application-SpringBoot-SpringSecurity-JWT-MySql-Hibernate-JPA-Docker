package com.example.FoodDeliveryApplication.services.Rider;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.FoodDeliveryApplication.entities.Rider.RiderRating;
import com.example.FoodDeliveryApplication.exceptions.EntityDoesNotExistException;
import com.example.FoodDeliveryApplication.repository.Rider.RiderRatingRepositiory;

@Service
public class RiderRatingService {
    @Autowired
    private RiderRatingRepositiory riderRatingRepositiory;

    public RiderRating createRiderRating(RiderRating riderRating)
    {
        riderRatingRepositiory.save(riderRating);
        return riderRating;
    }

    public List<RiderRating> getAllRiderRatings()
    {
        return (List<RiderRating>) riderRatingRepositiory.findAll();
    }

    public RiderRating getRiderRatingByRiderRatingId(int riderRatingId)
    {
        return riderRatingRepositiory.findById(riderRatingId).orElseThrow(()->new RuntimeException("No RiderRating exists with this riderRatingId"));
    }

    public List<RiderRating> getRiderRatingByRiderId(int riderId)
    {
        return riderRatingRepositiory.getRiderRatingByRider_RiderId(riderId);
    }

    public List<RiderRating> getRiderRatingByUserId(int userId)
    {
        return riderRatingRepositiory.getRiderRatingByUser_UserId(userId);
    }
    
    public RiderRating getRiderRatingByUserIdAndRiderId(int riderId, int userId)
    {
        return riderRatingRepositiory.getRiderRatingByUser_UserIdAndRider_RiderId(riderId, userId);
    }

    public RiderRating updateRiderRating(RiderRating riderRating)
    {
        RiderRating oldRiderRating=getRiderRatingByRiderRatingId(riderRating.getRiderRatingId());
        oldRiderRating.setRating(riderRating.getRating());
        riderRatingRepositiory.save(oldRiderRating);
        return oldRiderRating;
    }

    
}
