package com.example.FoodDeliveryApplication.entities.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

@Entity
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

    //foreign relations owning side
    @ManyToOne
    @JoinColumn(name="userId", referencedColumnName = "userId")
    private User user;


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
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}
