package com.example.FoodDeliveryApplication.controllers.Order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.FoodDeliveryApplication.entities.Order.OrderCustom;
import com.example.FoodDeliveryApplication.services.order.OrderService;

@RestController
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping(path="/Order")
    public ResponseEntity<List<OrderCustom>> getAllOrder()
    {
        return new ResponseEntity<List<OrderCustom>>(orderService.getAllOrderForAdmin(), HttpStatus.OK);
    }

    @GetMapping(path = "/Resturant/{resturantId}/Order")
    public ResponseEntity<List<OrderCustom>> getOrderCustomByResturantId(@PathVariable int resturantId)
    {
        return new ResponseEntity<List<OrderCustom>>(orderService.getOrderCustomByResturantId(resturantId), HttpStatus.OK);
    }
    
    @GetMapping(path = "/User/{userId}/Order")
    public ResponseEntity<List<OrderCustom>> getOrderCustomByUserId(@PathVariable int userId)
    {
        return new ResponseEntity<List<OrderCustom>>(orderService.getOrderCustomByUserId(userId), HttpStatus.OK);
    }

    @PostMapping(path="/Order")
    public ResponseEntity<OrderCustom> createOrder(@RequestBody OrderCustom order)
    {
        return new ResponseEntity<OrderCustom>(orderService.createOrder(order), HttpStatus.CREATED);
    }

    //@PatchMapping(path="/Order/{id}")\
    //How to pass enums via path variable??
}
