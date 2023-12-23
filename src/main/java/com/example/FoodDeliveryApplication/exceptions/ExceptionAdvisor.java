package com.example.FoodDeliveryApplication.exceptions;

import org.apache.commons.lang3.text.StrBuilder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class ExceptionAdvisor {
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException exception)
    {
        return new ResponseEntity<String>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityDoesNotExistException.class)
    public ResponseEntity<String> handleEntityDoesNotExistException(EntityDoesNotExistException exception)
    {
        return new ResponseEntity<String>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EnumOptionNotAvailableException.class)
    public ResponseEntity<String> handleEnumOptionNotAvailableException(EnumOptionNotAvailableException exception)
    {
        return new ResponseEntity<String>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException exception)
    {
        return new ResponseEntity<String>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResturantAndUserAddressTooFarException.class)
    public ResponseEntity<String> handleResturantAndUserAddresTooFarException(ResturantAndUserAddressTooFarException exception)
    {
        return new ResponseEntity<String>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OrderAlreadyCancelledException.class)
    public ResponseEntity<String> handleOrderAlreadyCancelledException(OrderAlreadyCancelledException exception)
    {
        return new ResponseEntity<String>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PaymentIsAlreadyPaidException.class)
    public ResponseEntity<String> handlePaymentIsAlreadyPaidException(PaymentIsAlreadyPaidException exception)
    {
        return new ResponseEntity<String>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}