package com.example.FoodDeliveryApplication.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;    

import com.example.FoodDeliveryApplication.entities.Order.OrderCustom;
import com.example.FoodDeliveryApplication.entities.Rider.Rider;
import com.example.FoodDeliveryApplication.repository.Order.OrderRepository;
import com.example.FoodDeliveryApplication.repository.Rider.RiderRepository;


public class AssignRiderService implements Runnable {
    
    private OrderRepository orderRepository;
    private RiderRepository riderRepository;

    public AssignRiderService(OrderRepository orderRepository, RiderRepository riderRepository)
    {
        this.orderRepository=orderRepository;
        this.riderRepository=riderRepository;
    }
    @Override
    public void run()
    {
        while(true){
        System.out.println("Fetching Order from database");
        List<OrderCustom> ordersWhichAreToBeAssignedARider = orderRepository.getOrderCustomForAssigningRider();
        if(null!=ordersWhichAreToBeAssignedARider && !ordersWhichAreToBeAssignedARider.isEmpty()){
        System.out.println("Order fetched from database: " + ordersWhichAreToBeAssignedARider.size());
            for(OrderCustom order: ordersWhichAreToBeAssignedARider)
        {
            List<Rider> ridersAvailableForTheOrderPincode = riderRepository.getRiderByPincodeAndIsAvailable(order.getResturant().getPincode(), true);
            if(null!=ridersAvailableForTheOrderPincode && !ridersAvailableForTheOrderPincode.isEmpty())
            {
                Rider riderWhoWillTakeTheOrder = ridersAvailableForTheOrderPincode.get(0);
                order.setRider(riderWhoWillTakeTheOrder);
                riderWhoWillTakeTheOrder.setAvailable(false);
                orderRepository.save(order);
                riderRepository.save(riderWhoWillTakeTheOrder);
            }
        }
    }
    else{
        System.out.println("No orders found. Sleeping for 30s");
    }
    try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            throw new RuntimeException("Thread to assign rider to an Order has run into an Exception");
        }
}
}
}
