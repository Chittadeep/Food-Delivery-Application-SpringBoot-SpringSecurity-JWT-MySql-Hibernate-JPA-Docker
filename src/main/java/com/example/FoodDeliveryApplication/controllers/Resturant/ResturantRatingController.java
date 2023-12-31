package com.example.FoodDeliveryApplication.controllers.Resturant;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.FoodDeliveryApplication.entities.Resturant.ResturantRating;
import com.example.FoodDeliveryApplication.services.Resturant.ResturantRatingService;


@RestController
public class ResturantRatingController {
    @Autowired
    private ResturantRatingService resturantRatingService;
    
    @GetMapping(path = "/resturantRating")
    public ResponseEntity<List<ResturantRating>> getAllResturantRatings()
    {
        return new ResponseEntity<List<ResturantRating>>(resturantRatingService.getAllResturantRating(), HttpStatus.OK);
    }

    @PostMapping(path = "/resturantRating")
    public ResponseEntity<ResturantRating> createResturantRating(@RequestBody ResturantRating resturantRating)
    {
        return new ResponseEntity<ResturantRating>(resturantRatingService.createResturantRating(resturantRating), HttpStatus.CREATED);
    }

    @GetMapping(path = "/resturantRating/{resturantRatingId}")
    public ResponseEntity<ResturantRating> getResturantRatingById(@PathVariable int resturantRatingId)
    {
        return new ResponseEntity<ResturantRating>(resturantRatingService.getResturantRatingById(resturantRatingId), HttpStatus.OK);
    }

    @GetMapping(path = "/resturantRating/userId/{userId}")
    public ResponseEntity<List<ResturantRating>> getResturantRatingByUserId(@PathVariable int userId)
    {
        return new ResponseEntity<List<ResturantRating>>(resturantRatingService.getResturantRatingByUserId(userId), HttpStatus.OK);
    }

    @GetMapping(path="/resturantRating/ResturantId&UserId")
    public ResponseEntity<ResturantRating> getResturantRatingByResturantIdAndUserId(@RequestParam int resturantID, @RequestParam int userId)
    {
        return new ResponseEntity<ResturantRating>(resturantRatingService.getResturantRatingByResturantIdAndUserId(resturantID, userId), HttpStatus.OK);
    }

    @PatchMapping(path = "/completeResturantRating")
    public ResponseEntity<ResturantRating> completeResturantRating(@RequestParam int resturantRatingId, @RequestParam int rating)
    {
        return new ResponseEntity<ResturantRating>(resturantRatingService.completeResturantRating(resturantRatingId, rating), HttpStatus.ACCEPTED);
    }
}
