package com.example.FoodDeliveryApplication.controllers.Resturant;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.FoodDeliveryApplication.entities.Resturant.ResturantPayment;
import com.example.FoodDeliveryApplication.services.Resturant.ResturantPaymentService;

public class ResturantPaymentController {
    @Autowired
    private ResturantPaymentService resturantPaymentService;

    @GetMapping(path="/resturantPayment")
    public ResponseEntity<List<ResturantPayment>> getAllResturantPayments()
    {
        return new ResponseEntity<List<ResturantPayment>>(resturantPaymentService.getAllResturantPayments(), HttpStatus.OK);
    }

    @PostMapping(path = "/resturantPayment")
    public ResponseEntity<ResturantPayment> createResturantPayment(@RequestBody ResturantPayment resturantPayment)
    {
        return new ResponseEntity<ResturantPayment>(resturantPaymentService.createResturantPayment(resturantPayment), HttpStatus.CREATED);
    }

    @GetMapping(path = "/resturantPayment/{resturantPaymentId}")
    public ResponseEntity<ResturantPayment> getResturantPayment(@PathVariable int resturantPaymentId)
    {
        return new ResponseEntity<ResturantPayment>(resturantPaymentService.getResturantPayment(resturantPaymentId), HttpStatus.OK);
    }

    @GetMapping(path = "/resturantPayment/resturantId/{resturantId}")
    public ResponseEntity<List<ResturantPayment>> getResturantPaymentBYResturantId(@PathVariable int resturantId)
    {
        return new ResponseEntity<List<ResturantPayment>>(resturantPaymentService.getResturantPaymentByResturantId(resturantId), HttpStatus.OK);
    }

    @GetMapping(path = "/resturantPayment/amount/{amount}")
    public ResponseEntity<List<ResturantPayment>> getResturantPaymentByAmount(@PathVariable double amount)
    {
        return new ResponseEntity<List<ResturantPayment>>(resturantPaymentService.getResturantPaymentByAmount(amount), HttpStatus.OK);
    }

    @GetMapping(path = "/resturantPayment/transactionId/{transactionId}")
    public ResponseEntity<ResturantPayment> getResturantPaymentByTransactionId(@PathVariable String transactionId)
    {
        return new ResponseEntity<ResturantPayment>(resturantPaymentService.getResturantPaymentByTransactionId(transactionId), HttpStatus.OK);
    }
}
