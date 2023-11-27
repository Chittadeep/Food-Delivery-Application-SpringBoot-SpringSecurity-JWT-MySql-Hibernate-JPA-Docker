package com.example.FoodDeliveryApplication.entities.User;

import java.util.Date;

import com.example.FoodDeliveryApplication.entities.Enums.ModeOfPayment;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class UserPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userPaymentId;
    @NotBlank(message = "User payment cannot be generated without orderId")
    private int orderId;
    @NotBlank(message = "User payment cannot be generated without totalAmount")
    private double totalAmount;
    @NotBlank(message = "User payment cannot be generated without baseAmount")
    private double baseAmount;
    @NotBlank(message = "User payment cannot be generated without gstAmount")
    private double gstAmount;
    @NotBlank(message = "User payment cannot be generated without miscellaneousFee")
    private double miscellaneousFee;
    @NotBlank(message = "User payment cannot be generated without deliveryFee")
    private double deliveryFee;
    @NotBlank(message = "User payment cannot be generated without tip")
    private double tip;
    @NotBlank
    private Date timestamp;
    @NotBlank(message = "User payment cannot be generated without transactionId")
    //unique for transaction id
    private String transactionId;
    @NotBlank(message = "User payment cannot be generated without modeOfPayment")
    private ModeOfPayment modeOfPayment;

    public UserPayment(){}
        
    public int getUserPaymentId() {
        return userPaymentId;
    }
    public void setUserPaymentId(int userPaymentId) {
        this.userPaymentId = userPaymentId;
    }
    public int getOrderId() {
        return orderId;
    }
    
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    public double getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
    public double getBaseAmount() {
        return baseAmount;
    }
    public void setBaseAmount(double baseAmount) {
        this.baseAmount = baseAmount;
    }
    public double getGstAmount() {
        return gstAmount;
    }
    public void setGstAmount(double gstAmount) {
        this.gstAmount = gstAmount;
    }
    public double getMiscellaneousFee() {
        return miscellaneousFee;
    }
    public void setMiscellaneousFee(double miscellaneousFee) {
        this.miscellaneousFee = miscellaneousFee;
    }
    public double getDeliveryFee() {
        return deliveryFee;
    }
    public void setDeliveryFee(double deliveryFee) {
        this.deliveryFee = deliveryFee;
    }
    public double getTip() {
        return tip;
    }
    public void setTip(double tip) {
        this.tip = tip;
    }
    public Date getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
    public String getTransactionId() {
        return transactionId;
    }
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
    public ModeOfPayment getModeOfPayment() {
        return modeOfPayment;
    }
    public void setModeOfPayment(ModeOfPayment modeOfPayment) {
        this.modeOfPayment = modeOfPayment;
    }
}
