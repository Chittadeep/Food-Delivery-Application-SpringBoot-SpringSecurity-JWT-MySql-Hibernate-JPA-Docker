package com.example.FoodDeliveryApplication.repository.Rider;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.FoodDeliveryApplication.entities.Rider.RiderRating;

@Repository
public interface RiderRatingRepositiory extends CrudRepository<RiderRating, Integer> {
    //@Query("Select rr from RiderRating rr where rr.riderId = :riderId")
    public List<RiderRating> getRiderRatingByRider_RiderId(int riderId);

    //@Query("Select rr from RiderRating rr where rr.userId = :userId")
    public List<RiderRating> getRiderRatingByUser_UserId(int userId);

    //@Query("Select rr from RiderRating rr where rr.riderId = :riderId and rr.userId= :userId")
    public RiderRating getRiderRatingByUser_UserIdAndRider_RiderId(int userId, int riderId);

}
