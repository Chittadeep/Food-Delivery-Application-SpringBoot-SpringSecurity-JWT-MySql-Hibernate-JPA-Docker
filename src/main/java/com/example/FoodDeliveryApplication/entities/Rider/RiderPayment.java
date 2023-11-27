package com.example.FoodDeliveryApplication.entities.Rider;

import java.sql.Date;

import org.hibernate.annotations.CurrentTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class RiderPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int riderPaymentId;
    @NotBlank(message = "RiderPayment cannot be created without riderId")
    private int riderId;
    @NotBlank(message = "RiderPayment cannot be created without amount")
    private double amount;
    @NotBlank(message = "RiderPayment cannot be created without transactionId")
    private String transactionId;
    @CurrentTimestamp
    private Date timestamp;

    public RiderPayment() {
    }
    
    public int getRiderPaymentId() {
        return riderPaymentId;
    }
    public void setRiderPaymentId(int riderPaymentId) {
        this.riderPaymentId = riderPaymentId;
    }
    public int getRiderId() {
        return riderId;
    }
    public void setRiderId(int riderId) {
        this.riderId = riderId;
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
    public Date getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    } 

}
