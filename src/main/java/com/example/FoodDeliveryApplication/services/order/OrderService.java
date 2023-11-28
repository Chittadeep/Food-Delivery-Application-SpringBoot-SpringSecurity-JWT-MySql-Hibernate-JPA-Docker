package com.example.FoodDeliveryApplication.services.order;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.FoodDeliveryApplication.entities.Enums.OrderStatus;
import com.example.FoodDeliveryApplication.entities.Order.OrderCustom;
import com.example.FoodDeliveryApplication.repository.Order.OrderRepository;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    
    public OrderCustom createOrder(OrderCustom order)
    {
        orderRepository.save(order);
        return order;
    }

    public OrderCustom updateOrderStatus(int id, OrderStatus status)
    {
        OrderCustom order = getOrder(id);
        order.setOrderStatus(status);
        orderRepository.save(order);
        return order;
    }

    public OrderCustom getOrder(int id)
    {
        return orderRepository.findById(id).orElseThrow(()->new RuntimeException("no such id available for order"));
    }

    
    public List<OrderCustom> getAllOrderForAdmin()
    {
        return (ArrayList<OrderCustom>) orderRepository.findAll();
    }

    public List<OrderCustom> getOrderCustomByUserId(int userId)
    {
        return orderRepository.getOrderCustomByUser_UserId(userId);
    }

    public List<OrderCustom> getOrderCustomByResturantId(int resturantId)
    {
        return orderRepository.getOrderCustomByResturant_ResturantId(resturantId);
    }

    public List<OrderCustom> getOrderCustomByRiderId(int riderId)
    {
        return orderRepository.getOrderCustomByRider_RiderId(riderId);
    }

}
