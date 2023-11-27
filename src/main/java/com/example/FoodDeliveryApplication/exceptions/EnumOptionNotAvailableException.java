package com.example.FoodDeliveryApplication.exceptions;

public class EnumOptionNotAvailableException extends RuntimeException {
    private String message;

    public EnumOptionNotAvailableException(String message)
    {
        this.message=message;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message=message;
    }
}
