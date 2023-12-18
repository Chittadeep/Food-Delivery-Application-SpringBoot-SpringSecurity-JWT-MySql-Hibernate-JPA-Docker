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

import com.example.FoodDeliveryApplication.entities.Enums.OrderStatus;
import com.example.FoodDeliveryApplication.entities.Order.OrderCustom;
import com.example.FoodDeliveryApplication.model.response.OrderResponse;
import com.example.FoodDeliveryApplication.services.order.OrderService;

import io.micrometer.core.ipc.http.HttpSender.Response;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping(path="admin/order")
    public ResponseEntity<List<OrderCustom>> getAllOrder()
    {
        return new ResponseEntity<List<OrderCustom>>(orderService.getAllOrderForAdmin(), HttpStatus.OK);
    }

    @GetMapping(path="/admin/order/{orderId}")
    public ResponseEntity<OrderCustom> getOrderCustomById(@PathVariable int orderId)
    {
        return new ResponseEntity<OrderCustom>(orderService.getOrder(orderId), HttpStatus.OK);
    }
    
    @GetMapping(path = "/resturant/{resturantId}/order")
    public ResponseEntity<List<OrderCustom>> getOrderCustomByResturantId(@PathVariable int resturantId)
    {
        return new ResponseEntity<List<OrderCustom>>(orderService.getOrderCustomByResturantId(resturantId), HttpStatus.OK);
    }
    
    @GetMapping(path = "/user/{userId}/order")
    public ResponseEntity<List<OrderResponse>> getOrderCustomByUserId(@PathVariable int userId)
    {
        return new ResponseEntity<List<OrderResponse>>(orderService.getOrderCustomByUserId(userId), HttpStatus.OK);
    }

    @PostMapping(path="/order")
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderCustom order)
    {
        return new ResponseEntity<OrderResponse>(orderService.createOrder(order), HttpStatus.CREATED);
    }

    @GetMapping(path="/order/{resturantId}/{orderStatus}")
    public ResponseEntity<List<OrderCustom>> getOrderCustomByResturantIdAndOrderStatus(@PathVariable int resturantId, @PathVariable OrderStatus orderStatus) {
        return new ResponseEntity<List<OrderCustom>>(orderService.getOrderCustomByResturantIdAndOrderStatus(resturantId, orderStatus), HttpStatus.OK);
    }
    

    //@PatchMapping(path="/Order/{id}")\
    //How to pass enums via path variable??
}
