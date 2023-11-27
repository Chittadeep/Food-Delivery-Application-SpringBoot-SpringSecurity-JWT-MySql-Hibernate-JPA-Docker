package com.example.FoodDeliveryApplication.entities.Resturant;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class ResturantRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int resturantRatingId;
    @NotBlank(message = "Resturant rating cannot be created without resturantID")
    private int resturantId;
    @NotBlank(message = "Resturant rating cannot be created without userID")
    private int userId;
    @NotBlank(message = "Resturant rating cannot be created without rating")
    @Length(max = 6, min = 1)
    private int rating;
    
    public ResturantRating() {}
    
    public int getResturantRatingId() {
        return resturantRatingId;
    }
    public void setResturantRatingId(int resturantRatingId) {
        this.resturantRatingId = resturantRatingId;
    }
    public int getResturantId() {
        return resturantId;
    }
    public void setResturantId(int resturantId) {
        this.resturantId = resturantId;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }


}
