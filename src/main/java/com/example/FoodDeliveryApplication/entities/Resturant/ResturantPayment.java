package com.example.FoodDeliveryApplication.entities.Resturant;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

@Entity
public class ResturantPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int resturantPaymentId;
    @NotBlank(message = "Resturant Payment cannot be created without amount")
    private double amount;
    @NotBlank(message = "Resturant Payment cannot be created without transaction ID")
    @Column(unique=true)
    private String transactionId;
    @CreationTimestamp
    private Date timeStamp;

    //foreign relations owning side
    @ManyToOne
    @JoinColumn(name = "resturantId", referencedColumnName = "resturantId")
    private Resturant resturant;

    public ResturantPayment(){}

    public int getResturantPaymentId() {
        return resturantPaymentId;
    }
    public void setResturantPaymentId(int resturantPaymentId) {
        this.resturantPaymentId = resturantPaymentId;
    }
    
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public String getTransactionId() {
        return transactionId;
    }
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
    public Date getTimeStamp() {
        return timeStamp;
    }
    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Resturant getResturant() {
        return resturant;
    }

    public void setResturant(Resturant resturant) {
        this.resturant = resturant;
    }
}
