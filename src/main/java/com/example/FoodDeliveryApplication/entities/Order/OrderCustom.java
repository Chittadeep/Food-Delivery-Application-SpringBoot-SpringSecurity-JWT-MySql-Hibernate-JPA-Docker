package com.example.FoodDeliveryApplication.entities.Order;

import java.sql.Timestamp;

import java.util.List;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CurrentTimestamp;

import com.example.FoodDeliveryApplication.entities.Enums.OrderStatus;
import com.example.FoodDeliveryApplication.entities.Resturant.Resturant;
import com.example.FoodDeliveryApplication.entities.Rider.Rider;
import com.example.FoodDeliveryApplication.entities.User.Address;
import com.example.FoodDeliveryApplication.entities.User.User;
import com.example.FoodDeliveryApplication.entities.User.UserPayment;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class OrderCustom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;
    @Enumerated(EnumType.STRING)
    @ColumnDefault(value = "INITIATED")
    private OrderStatus orderStatus;
    @CurrentTimestamp
    private Timestamp orderInitiated;
    private Timestamp orderPlacedTimeStamp;
    private Timestamp orderDeliveredTimestamp;

    //foreign relations owning side
    @NotNull(message = "Order cannot be created without user Id")
    @ManyToOne
    @JoinColumn(name="userId", referencedColumnName = "userId")
    private User user;
    @ManyToOne
    @JoinColumn(name="riderId", referencedColumnName="riderId")
    private Rider rider;
    @NotNull(message = "Order cannot be created without resturantId")
    @ManyToOne
    @JoinColumn(name = "resturantId", referencedColumnName = "resturantId")
    private Resturant resturant;
    
    @ManyToOne
    @JoinColumn(name = "addressId", referencedColumnName = "addressId")
    private Address address;

    //foreign relations inverse side
    @OneToMany(mappedBy="order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;
    @OneToOne
    @JoinColumn(name = "orderId", referencedColumnName = "orderId")
    private UserPayment userPayment;
    

    public OrderCustom(){}

    
    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }
    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
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

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

        public Resturant getResturant() {
        return resturant;
    }


    public void setResturant(Resturant resturant) {
        this.resturant = resturant;
    }


    public Address getAddress() {
        return address;
    }


    public void setAddress(Address address) {
        this.address = address;
    }

    public Timestamp getOrderInitiated() {
        return orderInitiated;
    }

    public void setOrderInitiated(Timestamp orderInitiated) {
        this.orderInitiated = orderInitiated;
    }
}
