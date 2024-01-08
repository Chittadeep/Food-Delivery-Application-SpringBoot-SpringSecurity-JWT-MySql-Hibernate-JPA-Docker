package com.example.FoodDeliveryApplication.controllers.User;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.FoodDeliveryApplication.entities.Enums.ModeOfPayment;
import com.example.FoodDeliveryApplication.entities.User.UserPayment;
import com.example.FoodDeliveryApplication.services.User.UserPaymentService;

@RestController
public class UserPaymentController {
    @Autowired
    private UserPaymentService userPaymentService;

    @GetMapping(path = "/allUserPayments")
    public ResponseEntity<List<UserPayment>> getAllUserPayments()
    {
        return new ResponseEntity<List<UserPayment>>(userPaymentService.getAllUserPayments(), HttpStatus.OK);
    }

    @GetMapping(path="/userPayment/getUserPaymentById/{userPaymentId}")
    public ResponseEntity<UserPayment> getUserPaymentById(@PathVariable int userPaymentId)
    {
        return new ResponseEntity<UserPayment>(userPaymentService.getUserPaymentById(userPaymentId), HttpStatus.OK);
    }

    @GetMapping(path="/userPayment/getUserPaymentByOrderId/{orderId}")
    public ResponseEntity<UserPayment> getUserPaymentByOrderId(@PathVariable int orderId)
    {
        return new ResponseEntity<UserPayment>(userPaymentService.getUserPaymentByOrderId(orderId), HttpStatus.OK);
    }

    @GetMapping(path="/userPayment/getUserPaymentByTransactionId/{transactionId}")
    public ResponseEntity<UserPayment> getUserPaymentByTransactionId(@PathVariable String transactionId)
    {
        return new ResponseEntity<UserPayment>(userPaymentService.getUserPaymentByTransactionId(transactionId), HttpStatus.OK);
    }

    @PatchMapping(path="/userPayment")
    public ResponseEntity<UserPayment> completeUserPayment(@RequestParam int paymentId, @RequestParam ModeOfPayment modeOfPayment)
    {
        return new ResponseEntity<UserPayment>(userPaymentService.completeUserPayment(paymentId, modeOfPayment), HttpStatus.OK);
    }
}
