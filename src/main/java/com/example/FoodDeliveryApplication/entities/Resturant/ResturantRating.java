package com.example.FoodDeliveryApplication.entities.Resturant;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.validator.constraints.Length;

import com.example.FoodDeliveryApplication.entities.User.User;

import jakarta.persistence.Column;
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
    @Length(max = 6, min = 1)
    private int rating;
    @ColumnDefault(value="false")
    private boolean completed;
    
    //foreign relations owning side
    @ManyToOne
    @JoinColumn(name = "resturantId", referencedColumnName = "resturantId")
    private Resturant resturant;

    @ManyToOne
    @JoinColumn(name="userId", referencedColumnName = "userId")
    private User user;

    public ResturantRating() {}
    
    public int getResturantRatingId() {
        return resturantRatingId;
    }
    public void setResturantRatingId(int resturantRatingId) {
        this.resturantRatingId = resturantRatingId;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

}
