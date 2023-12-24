package com.example.FoodDeliveryApplication.exceptions;

public class ItemDoesNotBelongToTheMenuOfTheResturantException extends RuntimeException{
    private String message;


    public ItemDoesNotBelongToTheMenuOfTheResturantException()
    {
        this.message=new String("The item you have requested for does not exist in the menu of the resturant you are ordering from");
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}