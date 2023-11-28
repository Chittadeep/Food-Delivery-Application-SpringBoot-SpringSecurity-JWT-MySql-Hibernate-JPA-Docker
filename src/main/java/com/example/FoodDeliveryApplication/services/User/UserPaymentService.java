package com.example.FoodDeliveryApplication.services.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.FoodDeliveryApplication.entities.User.UserPayment;
import com.example.FoodDeliveryApplication.exceptions.EntityDoesNotExistException;
import com.example.FoodDeliveryApplication.repository.User.UserPaymentRepository;

@Service
public class UserPaymentService {
    @Autowired
    private UserPaymentRepository userPaymentRepository;

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
        return userPaymentRepository.findById(userPaymentId).orElseThrow(()-> new EntityDoesNotExistException("No user payment with such userPaymentId exists"));
    }

    public UserPayment getUserPaymentByOrderId(int orderId)
    {
        return userPaymentRepository.getUserPaymentByOrder_OrderId(orderId);
    }

    public UserPayment getUserPaymentByTransactionId(String transactionId)
    {
        return userPaymentRepository.getUserPaymentByTransactionId(transactionId);
    }

    
}
