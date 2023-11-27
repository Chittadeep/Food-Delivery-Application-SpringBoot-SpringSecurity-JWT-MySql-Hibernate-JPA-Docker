package com.example.FoodDeliveryApplication.controllers.Rider;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.FoodDeliveryApplication.entities.Rider.RiderPayment;
import com.example.FoodDeliveryApplication.services.Rider.RiderPaymentService;

public class RiderPaymentController {
    @Autowired
    RiderPaymentService riderPaymentService;
    @PostMapping(path="/rider")
    public ResponseEntity<RiderPayment> createRiderPayment(RiderPayment riderPayment)
    {
        return new ResponseEntity<RiderPayment>(riderPaymentService.createRiderPayment(riderPayment), HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/rider")
    public ResponseEntity<List<RiderPayment>> getAllRiderPayments()
    {
        return new ResponseEntity<List<RiderPayment>>(riderPaymentService.getAllRiderPayments(), HttpStatus.OK);
    }

    @GetMapping(path = "/rider/{riderPaymentId}")
    public ResponseEntity<RiderPayment> getRiderPaymentById(@PathVariable int riderPaymentId)
    {
        return new ResponseEntity<RiderPayment>(riderPaymentService.getRiderPaymentById(riderPaymentId), HttpStatus.OK);
    }

    @GetMapping(path = "/rider/riderId/{riderId}")
    public ResponseEntity<List<RiderPayment>> getRiderPaymentByRiderId(@PathVariable int riderId)
    {
        return new ResponseEntity<List<RiderPayment>>(riderPaymentService.getRiderPaymentByRiderId(riderId), HttpStatus.OK);
    }

    @GetMapping(path = "/rider/transactionId/{transactionId}")
    public ResponseEntity<RiderPayment> getRiderPaymentByTransactionId(@PathVariable String transactionId)
    {
        return new ResponseEntity<RiderPayment>(riderPaymentService.getRiderPaymentByTransactionId(transactionId), HttpStatus.OK);
    }
}
