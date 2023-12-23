package com.example.FoodDeliveryApplication.exceptions;

public class ResturantAndUserAddressTooFarException extends RuntimeException {
    private String message;

    public ResturantAndUserAddressTooFarException()
    {
        this.message=new String("The address from which the user has ordered is too far away from the resturant");
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
