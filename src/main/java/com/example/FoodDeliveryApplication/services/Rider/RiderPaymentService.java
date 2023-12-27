package com.example.FoodDeliveryApplication.services.Rider;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.FoodDeliveryApplication.entities.Rider.RiderPayment;
import com.example.FoodDeliveryApplication.exceptions.EntityDoesNotExistException;
import com.example.FoodDeliveryApplication.repository.Rider.RiderPaymentRepository;

@Service
public class RiderPaymentService {
    @Autowired
    private RiderPaymentRepository riderPaymentRepository;

    public RiderPayment createRiderPayment(RiderPayment riderPayment)
    {
        riderPaymentRepository.save(riderPayment);
        return riderPayment;
    }

    public List<RiderPayment> getAllRiderPayments()
    {
        return (List<RiderPayment>) riderPaymentRepository.findAll();
    }
    
    public RiderPayment getRiderPaymentById(int riderPaymentId)
    {
        return riderPaymentRepository.findById(riderPaymentId).orElseThrow(()-> new RuntimeException("Rider Payment with this id does not exist"));
    }

    public List<RiderPayment> getRiderPaymentByRiderId(int riderId)
    {
        return riderPaymentRepository.getRiderPaymentByRiderId(riderId);
    }

    public RiderPayment getRiderPaymentByTransactionId(String transactionId)
    {
        return riderPaymentRepository.getRiderPaymentByTransactionId(transactionId);
    }

}
