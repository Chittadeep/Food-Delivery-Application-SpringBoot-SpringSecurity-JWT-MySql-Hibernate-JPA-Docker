package com.example.FoodDeliveryApplication.controllers.Rider;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.FoodDeliveryApplication.entities.Rider.RiderRating;
import com.example.FoodDeliveryApplication.services.Rider.RiderRatingService;

@RestController
public class RiderRatingController {
    @Autowired
    private RiderRatingService riderRatingService;

    @GetMapping(path="/riderRating")
    public ResponseEntity<List<RiderRating>> getAllRiderRatings()
    {
        return new ResponseEntity<List<RiderRating>>(riderRatingService.getAllRiderRatings(), HttpStatus.OK);
    }

    @PostMapping(path="/riderRating")
    public ResponseEntity<RiderRating> createRiderRating(@RequestBody RiderRating riderRating)
    {
        return new ResponseEntity<RiderRating>(riderRatingService.createRiderRating(riderRating), HttpStatus.OK);
    }

    @GetMapping(path ="/riderRating/{riderRatingId}")
    public ResponseEntity<RiderRating> getRiderRatingByRiderRatingId(@PathVariable int riderRatingId) {
        return new ResponseEntity<RiderRating>(riderRatingService.getRiderRatingByRiderRatingId(riderRatingId), HttpStatus.OK);
    }

    @GetMapping(path="/riderRating/rider/{riderId}")
    public ResponseEntity<List<RiderRating>> getRiderRatingByRiderId(@PathVariable int riderId)
    {
        return new ResponseEntity<List<RiderRating>>(riderRatingService.getRiderRatingByRiderId(riderId), HttpStatus.OK);
    }
    
    @GetMapping(path="/riderRating/user/{userId}")
    public ResponseEntity<List<RiderRating>> getRiderRatingByUserId(@PathVariable int userId)
    {
        return new ResponseEntity<List<RiderRating>>(riderRatingService.getRiderRatingByRiderId(userId), HttpStatus.OK);
    }
    
    @GetMapping(path = "/riderRating/user/{userId}/rider/{riderId}")
    public ResponseEntity<RiderRating> getRiderRatingByUserIdAndRiderId(@PathVariable int riderId, @PathVariable int userId)
    {
        return new ResponseEntity<RiderRating>(riderRatingService.getRiderRatingByUserIdAndRiderId(riderId, userId), HttpStatus.OK);
    }

    @PutMapping(path = "/riderRating")
    public ResponseEntity<RiderRating> updateRiderRating(@RequestBody RiderRating riderRating)
    {
        return new ResponseEntity<RiderRating>(riderRatingService.updateRiderRating(riderRating), HttpStatus.ACCEPTED);
    }
}
