package com.example.FoodDeliveryApplication.entities.Rider;

import org.hibernate.annotations.ColumnDefault;

import com.example.FoodDeliveryApplication.entities.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

@Entity
public class RiderRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int riderRatingId;
    //@NotBlank(message = "RiderRating cannot be created without rating")
    private int rating;
    @ColumnDefault(value = "false")
    private boolean completed;

    //foreign relations owning side
    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "riderId", referencedColumnName="riderId")
    private Rider rider;

    public RiderRating() {
    }

    public int getRiderRatingId() {
        return riderRatingId;
    }
    public void setRiderRatingId(int riderRatingId) {
        this.riderRatingId = riderRatingId;
    }
    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Rider getRider() {
        return rider;
    }

    public void setRider(Rider rider) {
        this.rider = rider;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

}
