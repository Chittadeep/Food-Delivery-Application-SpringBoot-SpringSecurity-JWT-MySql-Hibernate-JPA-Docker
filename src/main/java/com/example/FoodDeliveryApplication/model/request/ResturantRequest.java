package com.example.FoodDeliveryApplication.model.request;

import com.example.FoodDeliveryApplication.entities.Enums.ResturantType;

public class ResturantRequest {
    
    private String resturantName;
    private String phoneNumber;
    private String mail;
    private String password;
    private double latitude;
    private double longitude;
    private String city;
    private String address;
    private String pincode;
    private String website;
    private String gst;
    private String fssaiStatus;
    private ResturantType resturantType;
    private String email;


    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
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
    public String getPincode() {
        return pincode;
    }
    public void setPincode(String pincode) {
        this.pincode = pincode;
    }
    public String getWebsite() {
        return website;
    }
    public void setWebsite(String website) {
        this.website = website;
    }
    public String getGst() {
        return gst;
    }
    public void setGst(String gst) {
        this.gst = gst;
    }
    public String getFssaiStatus() {
        return fssaiStatus;
    }
    public void setFssaiStatus(String fssaiStatus) {
        this.fssaiStatus = fssaiStatus;
    }
    public ResturantType getResturantType() {
        return resturantType;
    }
    public void setResturantType(ResturantType resturantType) {
        this.resturantType = resturantType;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getResturantName() {
        return resturantName;
    }
    public void setResturantName(String resturantName) {
        this.resturantName = resturantName;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }


}
