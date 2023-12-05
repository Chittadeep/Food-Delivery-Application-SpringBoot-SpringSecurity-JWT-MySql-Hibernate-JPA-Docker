package com.example.FoodDeliveryApplication.models.user;

import com.example.FoodDeliveryApplication.entities.User.Address;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

public class AddressResponse {
        
    private int addressId;
    private String city;
    private String address;
    private String state;
    private String pincode;
    private int userId;

    public AddressResponse()
    {

    }

    
    public AddressResponse(Address address)
    {
        this.addressId=address.getAddressId();
        this.city = address.getCity();
        this.state = address.getState();
        this.pincode = address.getPincode();
        this.userId = address.getUserId();
    }

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
    public void setPincode(String pincode) {
        this.pincode = pincode;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }


}
