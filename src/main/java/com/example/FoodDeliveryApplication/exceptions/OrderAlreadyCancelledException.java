package com.example.FoodDeliveryApplication.exceptions;

public class OrderAlreadyCancelledException extends RuntimeException {
    private String message;

    public OrderAlreadyCancelledException()
    {
        this.message = new String("The order whose status you are trying to update was already cancelled");
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
