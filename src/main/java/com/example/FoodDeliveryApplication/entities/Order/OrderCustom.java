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
    @NotNull(message="Order cannot be created without order status")
    @Column(columnDefinition = "varchar(32) default 'INITIATED'")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @CurrentTimestamp
    private Timestamp orderInitiatedTimestamp;
    private Timestamp orderPlacedTimestamp;
    private Timestamp orderAcceptedTimestamp;
    private Timestamp orderReadyForPickupTimestamp;
    private Timestamp orderOnTheWayTimestamp;
    private Timestamp orderDeliveredTimestamp;
    private Timestamp orderCancelledTimestamp;

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
    @OneToOne(cascade = CascadeType.ALL)
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

    public Timestamp getOrderInitiatedTimestamp() {
        return orderInitiatedTimestamp;
    }

    public void setOrderInitiatedTimestamp(Timestamp orderInitiatedTimestamp) {
        this.orderInitiatedTimestamp = orderInitiatedTimestamp;
    }

    public UserPayment getUserPayment() {
        return userPayment;
    }

    public void setUserPayment(UserPayment userPayment) {
        this.userPayment = userPayment;
    }

    public Timestamp getOrderAcceptedTimestamp() {
        return orderAcceptedTimestamp;
    }

    public void setOrderAcceptedTimestamp(Timestamp orderAcceptedTimestamp) {
        this.orderAcceptedTimestamp = orderAcceptedTimestamp;
    }

    public Timestamp getOrderCancelledTimestamp() {
        return orderCancelledTimestamp;
    }

    public void setOrderCancelledTimestamp(Timestamp orderCancelledTimestamp) {
        this.orderCancelledTimestamp = orderCancelledTimestamp;
    }

    public Timestamp getOrderPlacedTimestamp() {
        return orderPlacedTimestamp;
    }

    public void setOrderPlacedTimestamp(Timestamp orderPlacedTimestamp) {
        this.orderPlacedTimestamp = orderPlacedTimestamp;
    }


    public Timestamp getOrderReadyForPickupTimestamp() {
        return orderReadyForPickupTimestamp;
    }


    public void setOrderReadyForPickupTimestamp(Timestamp orderReadyForPickupTimestamp) {
        this.orderReadyForPickupTimestamp = orderReadyForPickupTimestamp;
    }


    public Timestamp getOrderOnTheWayTimestamp() {
        return orderOnTheWayTimestamp;
    }


    public void setOrderOnTheWayTimestamp(Timestamp orderOnTheWayTimestamp) {
        this.orderOnTheWayTimestamp = orderOnTheWayTimestamp;
    }
}
