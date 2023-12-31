package com.example.FoodDeliveryApplication.model.response;

import java.util.List;

import com.example.FoodDeliveryApplication.entities.Enums.OrderStatus;
import com.example.FoodDeliveryApplication.entities.Order.OrderCustom;
import com.example.FoodDeliveryApplication.entities.Order.OrderItem;

public class OrderResponse {
    private int orderId;
    private int userId;
    private int resturantId;
    private int paymentId;
    private int riderId;
    private OrderStatus orderStatus;
    private List<OrderItem> orderItems;

    public OrderResponse(OrderCustom orderCustom)
    {
        this.orderId=orderCustom.getOrderId();
        this.userId=orderCustom.getUser().getUserId();
        this.resturantId=orderCustom.getResturant().getResturantId();
        if(orderCustom.getUserPayment()!=null)
        {
            this.paymentId=orderCustom.getUserPayment().getUserPaymentId();
        }
        if(orderCustom.getRider()!=null)
        {
            this.riderId=orderCustom.getRider().getRiderId();
        }
        this.orderStatus=orderCustom.getOrderStatus();
        this.orderItems=orderCustom.getOrderItems();
    }
    
    public OrderResponse(){}

    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getResturantId() {
        return resturantId;
    }
    public void setResturantId(int resturantId) {
        this.resturantId = resturantId;
    }
    public int getPaymentId() {
        return paymentId;
    }
    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }
    public int getRiderId() {
        return riderId;
    }
    public void setRiderId(int riderId) {
        this.riderId = riderId;
    }
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }
    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }
    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }    
}
