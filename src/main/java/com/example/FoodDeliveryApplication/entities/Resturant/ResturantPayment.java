package com.example.FoodDeliveryApplication.entities.Resturant;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class ResturantPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int resturantPaymentId;
    @NotBlank(message = "Resturant Payment cannot be created without Resturant Id")
    private int resturantId;
    @NotBlank(message = "Resturant Payment cannot be created without amount")
    private double amount;
    @NotBlank(message = "Resturant Payment cannot be created without transaction ID")
    private String transactionId;
    @CreationTimestamp
    private Date timeStamp;

    public ResturantPayment(){}

    public int getResturantPaymentId() {
        return resturantPaymentId;
    }
    public void setResturantPaymentId(int resturantPaymentId) {
        this.resturantPaymentId = resturantPaymentId;
    }
    public int getResturantId() {
        return resturantId;
    }
    public void setResturantId(int resturantId) {
        this.resturantId = resturantId;
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

    
}
