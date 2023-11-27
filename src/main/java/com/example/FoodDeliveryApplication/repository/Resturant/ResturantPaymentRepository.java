package com.example.FoodDeliveryApplication.repository.Resturant;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.FoodDeliveryApplication.entities.Resturant.ResturantPayment;

@Repository
public interface ResturantPaymentRepository extends CrudRepository<ResturantPayment, Integer> {
    
  //  @Query("Select rp from ResturantPayment rp where rp.resturantId = :resturantId")
    public List<ResturantPayment> getResturantPaymentByResturantId(int resturantId);
    
  //  @Query("Select rp from ResturantPayment rp where rp.amount = :amount")
    public List<ResturantPayment> getResturantPaymentByAmount(double amount);

    /*
    @Query("Select rp from ResturantPayment rp order by rp.price asc")
    public List<ResturantPayment> getAllResturantPaymentsOfPriceLowToHigh();

    @Query("Select rp from ResturantPayment rp order by rp.price desc")
    public List<ResturantPayment> getAllResturantPaymentsOfPriceHighToLow();
    */

   // @Query("Select rp from ResturantPayment rp where rp.transactionId = :transactionId")
    public ResturantPayment getResturantPaymentByTransactionId(String transactionId);
}
