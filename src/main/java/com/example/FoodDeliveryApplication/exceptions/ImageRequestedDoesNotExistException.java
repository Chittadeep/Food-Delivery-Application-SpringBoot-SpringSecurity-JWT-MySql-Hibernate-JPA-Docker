package com.example.FoodDeliveryApplication.exceptions;

public class ImageRequestedDoesNotExistException extends RuntimeException {
    private String message;

    public ImageRequestedDoesNotExistException()
    {
        this.message = new String("Image requested does not exist");
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
