package com.example.FoodDeliveryApplication.repository.Order;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.FoodDeliveryApplication.entities.Order.OrderCustom;

@Repository
public interface OrderRepository extends CrudRepository<OrderCustom, Integer> {
    
    //@Query("Select o from OrderCustom o where o.userId = :userId")
    public List<OrderCustom> getOrderCustomByUser_UserId(int userId);

    //@Query("Select o from OrderCustom o where o.resturantId = :resturantId")
    public List<OrderCustom> getOrderCustomByResturant_ResturantId(int resturantId);

    //@Query("Select o from OrderCustom o where o.riderId = :riderId")
    public List<OrderCustom> getOrderCustomByRider_RiderId(int riderId);

    public List<OrderCustom> getOrderCustomByAddress_AddressId(int addressId);
}
