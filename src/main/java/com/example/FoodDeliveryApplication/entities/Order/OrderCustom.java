package com.example.FoodDeliveryApplication.entities.Order;

import java.sql.Timestamp;

import com.example.FoodDeliveryApplication.entities.Enums.OrderStatus;
import com.example.FoodDeliveryApplication.entities.Rider.Rider;
import com.example.FoodDeliveryApplication.entities.User.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

@Entity
public class OrderCustom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;
    @NotBlank(message = "Order cannot be created without resturantId")
    private int resturantId;
    private OrderStatus orderStatus;
    @NotBlank(message = "Order cannot be created without addressId")
    private int addressId;
    private Timestamp orderPlacedTimeStamp;
    private Timestamp orderDeliveredTimestamp;

    //forign relations owning side
    @ManyToOne
    @JoinColumn(name="userId", referencedColumnName = "userId")
    private User user;
    @ManyToOne
    @JoinColumn(name="riderId", referencedColumnName="riderId")
    private Rider rider;

    public OrderCustom(){}

    
    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    public int getResturantId() {
        return resturantId;
    }
    public void setResturantId(int resturantId) {
        this.resturantId = resturantId;
    }
    
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }
    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
    public int getAddressId() {
        return addressId;
    }
    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }
    public Timestamp getOrderPlacedTimeStamp() {
        return orderPlacedTimeStamp;
    }
    public void setOrderPlacedTimeStamp(Timestamp orderPlacedTimeStamp) {
        this.orderPlacedTimeStamp = orderPlacedTimeStamp;
    }
    public Timestamp getOrderDeliveredTimestamp() {
        return orderDeliveredTimestamp;
    }
    public void setOrderDeliveredTimestamp(Timestamp orderDeliveredTimestamp) {
        this.orderDeliveredTimestamp = orderDeliveredTimestamp;
    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Rider getRider() {
        return rider;
    }

    public void setRider(Rider rider) {
        this.rider = rider;
    }
}
