package com.example.FoodDeliveryApplication.repository.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.FoodDeliveryApplication.entities.User.UserPayment;

@Repository
public interface UserPaymentRepository extends CrudRepository<UserPayment,Integer>{
    
    //@Query("Select up from UserPayment up where up.orderId = :orderId")
    public UserPayment getUserPaymentByOrder_OrderId(int orderId);

    //@Query("Select up from UserPayment up where up.transactionId = :transactionId")
    public UserPayment getUserPaymentByTransactionId(String transactionId);

}
