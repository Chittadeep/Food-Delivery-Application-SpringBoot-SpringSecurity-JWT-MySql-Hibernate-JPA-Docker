package com.example.FoodDeliveryApplication.entities.User;

import java.util.List;

import com.example.FoodDeliveryApplication.entities.Order.OrderCustom;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "addressId")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int addressId;
    @NotBlank(message = "City required for address")
    private String city;
    @NotBlank(message = "Address required for address")
    private String address;
    @NotBlank(message = "State required for addresss")
    private String state;
    @NotBlank(message = "Pin Code required for address")
    private String pincode;

    // //foreign relations owning side
    // @ManyToOne(fetch=FetchType.EAGER)
    // @JoinColumn(name="userId", referencedColumnName = "userId")
    //@JsonBackReference
    @NotNull
    private int userId;

    // //foriegn relations inverse side
    // @OneToMany(mappedBy = "address")
    // private List<OrderCustom> orders;

    // public List<OrderCustom> getOrders() {
    //     return orders;
    // }

    // public void setOrders(List<OrderCustom> orders) {
    //     this.orders = orders;
    // }
    public Address(){}

    public int getAddressId() {
        return addressId;
    }
    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getPincode() {
        return pincode;
    }
    public void setPincode(String pinCode) {
        this.pincode = pinCode;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
}
