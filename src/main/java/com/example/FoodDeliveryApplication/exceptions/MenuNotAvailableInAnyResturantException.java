package com.example.FoodDeliveryApplication.exceptions;

public class MenuNotAvailableInAnyResturantException extends RuntimeException {
    private String message;

    public MenuNotAvailableInAnyResturantException()
    {
        this.message = new String("The menu ids you have put in order items dosen't exist anywhere");
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
