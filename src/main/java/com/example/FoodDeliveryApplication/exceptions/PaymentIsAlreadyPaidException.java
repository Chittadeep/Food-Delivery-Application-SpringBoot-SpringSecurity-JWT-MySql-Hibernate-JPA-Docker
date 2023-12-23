package com.example.FoodDeliveryApplication.exceptions;

public class PaymentIsAlreadyPaidException extends RuntimeException {
    private String message;

    public PaymentIsAlreadyPaidException()
    {
        this.message = new String("This payment was already completed earlier");
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
