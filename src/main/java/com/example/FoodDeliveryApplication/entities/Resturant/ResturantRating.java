package com.example.FoodDeliveryApplication.entities.Resturant;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

@Entity
public class ResturantRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int resturantRatingId;
    @NotBlank(message = "Resturant rating cannot be created without userID")
    private int userId;
    @NotBlank(message = "Resturant rating cannot be created without rating")
    @Length(max = 6, min = 1)
    private int rating;
    
    //foreign relations owning side
    @ManyToOne
    @JoinColumn(name = "resturantId", referencedColumnName = "resturantId")
    private Resturant resturant;


    public ResturantRating() {}
    
    public int getResturantRatingId() {
        return resturantRatingId;
    }
    public void setResturantRatingId(int resturantRatingId) {
        this.resturantRatingId = resturantRatingId;
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

    public Resturant getResturant() {
        return resturant;
    }

    public void setResturant(Resturant resturant) {
        this.resturant = resturant;
    }

}
