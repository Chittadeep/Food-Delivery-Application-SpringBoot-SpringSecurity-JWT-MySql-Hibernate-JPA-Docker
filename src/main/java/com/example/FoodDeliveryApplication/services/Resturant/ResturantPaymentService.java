package com.example.FoodDeliveryApplication.services.Resturant;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.FoodDeliveryApplication.entities.Resturant.ResturantPayment;
import com.example.FoodDeliveryApplication.exceptions.EntityDoesNotExistException;
import com.example.FoodDeliveryApplication.repository.Resturant.ResturantPaymentRepository;

@Service
public class ResturantPaymentService {
    @Autowired
    private ResturantPaymentRepository resturantPaymentRepository;

    public ResturantPayment createResturantPayment(ResturantPayment resturantPayment)
    {
        resturantPaymentRepository.save(resturantPayment);
        return resturantPayment;
    }

    public List<ResturantPayment> getAllResturantPayments()
    {
        return (List<ResturantPayment>) resturantPaymentRepository.findAll();
    }

    public ResturantPayment getResturantPayment(int resturantPaymentId)
    {
        return resturantPaymentRepository.findById(resturantPaymentId).orElseThrow(()-> new EntityDoesNotExistException("No resturant payment exists with this id"));
    }

    public List<ResturantPayment> getResturantPaymentByResturantId(int resturantId)
    {
        return resturantPaymentRepository.getResturantPaymentByResturantId(resturantId);
    }

    public List<ResturantPayment> getResturantPaymentByAmount(double amount)
    {
        return resturantPaymentRepository.getResturantPaymentByAmount(amount);
    }

    /*
    public List<ResturantPayment> getAllResturantPaymentsOfPriceLowToHigh()
    {
        return resturantPaymentRepository.getAllResturantPaymentsOfPriceLowToHigh();
    }

    public List<ResturantPayment> getAllResturantPaymentsOfPriceHighToLow()
    {
        return resturantPaymentRepository.getAllResturantPaymentsOfPriceHighToLow();
    }
    */

    public ResturantPayment getResturantPaymentByTransactionId(String transactionId)
    {
        return resturantPaymentRepository.getResturantPaymentByTransactionId(transactionId);
    }

    
}
