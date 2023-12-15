package com.example.FoodDeliveryApplication.entities.Order;

import com.example.FoodDeliveryApplication.entities.Resturant.Menu;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int orderItemId;
    private double price;
    @NotNull(message = "order item quantity cannot be null")
    private int quantity;

    //foreign relations owning side
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="menuId", referencedColumnName="menuId")
    private Menu menu;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="orderId", referencedColumnName="orderId")
    private OrderCustom order;

    public OrderItem() {
    }
    
    public int getOrderItemId() {
        return orderItemId;
    }
    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

   public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public OrderCustom getOrder() {
        return order;
    }

    public void setOrder(OrderCustom order) {
        this.order = order;
    }

}
