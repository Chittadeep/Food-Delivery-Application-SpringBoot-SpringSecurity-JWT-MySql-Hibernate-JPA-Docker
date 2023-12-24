package com.example.FoodDeliveryApplication.exceptions;

public class AddressIsNotOfUserException extends RuntimeException {
    private String message;

    public AddressIsNotOfUserException()
    {
        this.message=new String("The user does not have the address provided");
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
