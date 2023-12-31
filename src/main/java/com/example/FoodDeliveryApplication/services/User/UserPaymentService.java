package com.example.FoodDeliveryApplication.services.User;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.FoodDeliveryApplication.entities.Enums.ModeOfPayment;
import com.example.FoodDeliveryApplication.entities.Enums.OrderStatus;
import com.example.FoodDeliveryApplication.entities.Order.OrderCustom;
import com.example.FoodDeliveryApplication.entities.Rider.Rider;
import com.example.FoodDeliveryApplication.entities.User.UserPayment;
import com.example.FoodDeliveryApplication.exceptions.EntityDoesNotExistException;
import com.example.FoodDeliveryApplication.exceptions.PaymentIsAlreadyPaidException;
import com.example.FoodDeliveryApplication.repository.Order.OrderRepository;
import com.example.FoodDeliveryApplication.repository.Rider.RiderRepository;
import com.example.FoodDeliveryApplication.repository.User.UserPaymentRepository;
import com.example.FoodDeliveryApplication.services.AssignRiderService;

@Service
public class UserPaymentService {
    @Autowired
    private UserPaymentRepository userPaymentRepository;
    @Autowired
    private OrderRepository orderRepository;

    public UserPayment createUserPayment(UserPayment userPayment)
    {
        userPaymentRepository.save(userPayment);
        return userPayment;
    }

    public List<UserPayment> getAllUserPayments()
    {
        return (List<UserPayment>) userPaymentRepository.findAll();
    }

    public UserPayment getUserPaymentById(int userPaymentId)
    {
        return userPaymentRepository.findById(userPaymentId).orElseThrow(()-> new RuntimeException("No user payment with such userPaymentId exists"));
    }

    public UserPayment getUserPaymentByOrderId(int orderId)
    {
        orderRepository.findById(orderId).orElseThrow(()-> new RuntimeException("No such order available"));
        return userPaymentRepository.getUserPaymentByOrderId(orderId);
    }

    public UserPayment getUserPaymentByTransactionId(String transactionId)
    {
        return userPaymentRepository.getUserPaymentByTransactionId(transactionId);
    }

    public UserPayment completeUserPayment(int userPaymentId, ModeOfPayment modeOfPayment)
    {
        UserPayment userPayment = getUserPaymentById(userPaymentId);
        if(userPayment.isPaid()) throw new RuntimeException("The payment was already completed previously");
        userPayment.setModeOfPayment(modeOfPayment);
        userPayment.setPaid(true);
        userPayment.setCompletedTimestamp(new Timestamp(System.currentTimeMillis()));
        OrderCustom order =orderRepository.findById(userPayment.getOrderId()).get();
        order.setOrderStatus(OrderStatus.PLACED);
        order.setOrderPlacedTimestamp(new Timestamp(System.currentTimeMillis()));
        orderRepository.save(order);
        return userPayment;
    }
}
