package com.example.FoodDeliveryApplication.repository.Rider;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.FoodDeliveryApplication.entities.Rider.RiderPayment;

@Repository
public interface RiderPaymentRepository extends CrudRepository<RiderPayment, Integer> {
    //@Query("Select rp from RiderPayment rp where rp.riderId = :riderId")
    public List<RiderPayment> getRiderPaymentByRiderId(int riderId);

    //@Query("Select rp from RiderPayment rp where rp.transactionId = :transactionId")
    public RiderPayment getRiderPaymentByTransactionId(String transactionId);
}

