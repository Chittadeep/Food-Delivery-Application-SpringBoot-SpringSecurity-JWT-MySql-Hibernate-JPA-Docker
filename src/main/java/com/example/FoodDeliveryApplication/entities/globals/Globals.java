package com.example.FoodDeliveryApplication.entities.globals;

import jakarta.persistence.Entity;

@Entity
public class Globals {
    private static double gstPercentage =  10;
    private static double deliveryFeePerKm = 5;
    private static double miscellaneousFee = 0;

    public static double getGstPercentage() {
        return gstPercentage;
    }
    public static void setGstPercentage(double gstPercentage) {
        gstPercentage = gstPercentage;
    }
    public static double getDeliveryFeePerKm() {
        return deliveryFeePerKm;
    }
    public static void setDeliveryFeePerKm(double deliveryFeePerKm) {
        deliveryFeePerKm = deliveryFeePerKm;
    }
    public static double getMiscellaneousFee() {
        return miscellaneousFee;
    }
    public void setMiscellaneousFee(double miscellaneousFee) {
        miscellaneousFee = miscellaneousFee;
    }
}
