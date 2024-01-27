package com.example.FoodDeliveryApplication.controllers.Order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.FoodDeliveryApplication.entities.Enums.OrderStatus;
import com.example.FoodDeliveryApplication.entities.Order.OrderCustom;
import com.example.FoodDeliveryApplication.model.response.OrderResponse;
import com.example.FoodDeliveryApplication.services.order.OrderService;


@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping(path="admin/order")
    public ResponseEntity<List<OrderResponse>> getAllOrder()
    {
        return new ResponseEntity<List<OrderResponse>>(orderService.getAllOrderForAdmin(), HttpStatus.OK);
    }

    @GetMapping(path="/admin/order/{orderId}")
    public ResponseEntity<OrderResponse> getOrderResponseById(@PathVariable int orderId)
    {
        return new ResponseEntity<OrderResponse>(new OrderResponse(orderService.getOrder(orderId)), HttpStatus.OK);
    }
    
    @GetMapping(path = "/resturant/{resturantId}/order")
    public ResponseEntity<List<OrderResponse>> getOrderCustomByResturantId(@PathVariable int resturantId)
    {
        return new ResponseEntity<List<OrderResponse>>(orderService.getOrderCustomByResturantId(resturantId), HttpStatus.OK);
    }
    
    @GetMapping(path = "/user/{userId}/order")
    public ResponseEntity<List<OrderResponse>> getOrderCustomByUserId(@PathVariable int userId)
    {
        return new ResponseEntity<List<OrderResponse>>(orderService.getOrderCustomByUserId(userId), HttpStatus.OK);
    }

    @GetMapping(path="/rider/{riderId}/order")
    public ResponseEntity<List<OrderResponse>> getOrderCustomByRiderId(@PathVariable int riderId)
    {
        return new ResponseEntity<List<OrderResponse>>(orderService.getOrderCustomByRiderId(riderId), HttpStatus.OK);
    }

    @PostMapping(path="/order")
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderCustom order)
    {
        return new ResponseEntity<OrderResponse>(orderService.createOrder(order), HttpStatus.CREATED);
    }

    @GetMapping(path="/order/{resturantId}/{orderStatus}")
    public ResponseEntity<List<OrderResponse>> getOrderCustomByResturantIdAndOrderStatus(@PathVariable int resturantId, @PathVariable OrderStatus orderStatus) {
        return new ResponseEntity<List<OrderResponse>>(orderService.getOrderCustomByResturantIdAndOrderStatus(resturantId, orderStatus), HttpStatus.OK);
    }

    @GetMapping(path="/order/{resturantId}/checkPendingOrders")
    public ResponseEntity<List<OrderResponse>> getOrderCustomPendingByResturantId(@PathVariable int resturantId) {
        return new ResponseEntity<List<OrderResponse>>(orderService.getOrderCustomPendingByResturantId(resturantId), HttpStatus.OK);
    }
    
    @PatchMapping(path="/order/{orderId}/{orderStatus}")
    public ResponseEntity<OrderResponse> updateOrderCustomByOrderIdAndOrderStatus(@PathVariable int orderId, @PathVariable OrderStatus orderStatus)
    {
        return new ResponseEntity<OrderResponse>(orderService.updateOrderStatus(orderId, orderStatus), HttpStatus.OK);
    }
    
    @GetMapping(path="/order/{riderId}/getOrderForRiderToPickUp")
    public ResponseEntity<List<OrderResponse>> getOrderForRiderToPickUp(@PathVariable int riderId) {
        return new ResponseEntity<List<OrderResponse>>(orderService.getOrderCustomForRiderToPickUp(riderId), HttpStatus.OK);
    }
    
    @GetMapping(path="/order/{riderId}/getOrderForRider/{orderStatus}")
    public ResponseEntity<List<OrderResponse>> getOrderCustomByRiderIdAndOrderStatus(@PathVariable int riderId, @PathVariable OrderStatus orderStatus) {
        return new ResponseEntity<List<OrderResponse>>(orderService.getOrderCustomByRiderIdAndOrderStatus(riderId, orderStatus), HttpStatus.OK);
    }
}
