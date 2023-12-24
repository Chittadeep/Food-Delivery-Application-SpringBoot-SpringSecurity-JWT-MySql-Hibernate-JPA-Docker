package com.example.FoodDeliveryApplication.repository.Order;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.FoodDeliveryApplication.entities.Enums.OrderStatus;
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

    public List<OrderCustom> getOrderCustomByResturant_ResturantIdAndOrderStatus(int resturantId, OrderStatus orderStatus);

    public OrderCustom getOrderCustomByUserPayment_UserPaymentId(int userPaymentId);

    public List<OrderCustom> getOrderCustomByResturant_PincodeAndOrderStatus(String resturantPincode, OrderStatus orderStatus);

    @Query("Select o from OrderCustom o where o.rider.riderId is null and (o.orderStatus = PLACED or o.orderStatus=ACCEPTED or o.orderStatus=READY_FOR_PICKUP) order by o.orderPlacedTimestamp")
    public List<OrderCustom> getOrderCustomForAssigningRider();

    
    @Query("Select o from OrderCustom o where (o.orderStatus = PLACED or o.orderStatus = INITIATED) and o.resturant.resturantId=:resturantId")
    public List<OrderCustom> getPendingOrdersForResturant(int resturantId);
    
}
