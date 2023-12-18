package com.example.FoodDeliveryApplication.entities.Resturant;

import java.util.Set;
import java.util.List;
import org.hibernate.annotations.ColumnDefault;

import com.example.FoodDeliveryApplication.entities.Enums.ResturantType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Resturant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int resturantId;
    @NotBlank(message = "Name of a resturant cannot be null")
    private String resturantName;
    @NotNull(message = "Latitude of a resturant cannot be null")
    private double latitude;
    @NotNull(message = "Longitude of a resturant cannot be null")
    private double longitude;
    @NotBlank(message = "City of a resturant cannot be null")
    private String city;
    @NotBlank(message = "Address of a resturant cannot be null")
    private String address;
    @NotBlank(message = "Pincode of a resturant cannot be null")
    private String pincode;
    //@NotBlank(message = "Phone number of a resturant cannot be null")
    @Column(unique=true)
    private String phoneNumber;
    //@NotBlank(message = "Website of a resturant cannot be null")
    //validation for website
    private String website;
    private List<byte[]> pictures;
    @Lob
    @Column(length = 500000)
    private byte[] bankDetails;
    @NotBlank(message = "GST of a resturant cannot be null")
    private String gst;
    @NotBlank(message = "FSSAI status of a resturant cannot be null")
    private String fssaiStatus;     
    //@NotBlank(message = "Resturant Type of a resturant cannot be null")
    @Enumerated(value=EnumType.STRING)
    private ResturantType resturantType;
    @Email(message = "The email address is invalid")
    @Column(unique = true)
    private String email;
    @NotBlank(message = "Password for a resturant cannot be null")
    private String password;
    @ColumnDefault(value = "false")
    private boolean approved;

    //foreign relations inverse side
    @OneToMany
    @JoinColumn(name = "resturantId", referencedColumnName = "resturantId")
    private Set<Menu> menu;
    @OneToMany(mappedBy = "resturant")
    private Set<ResturantRating> resturantRatings;
    @OneToMany(mappedBy="resturant")
    private Set<ResturantPayment> resturantPayments;

    public Resturant() {}
    
    public int getResturantId() {
        return resturantId;
    }
    public void setResturantId(int resturantId) {
        this.resturantId = resturantId;
    }
    public String getName() {
        return resturantName;
    }
    public void setName(String resturantName) {
        this.resturantName = resturantName;
    }
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
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public boolean isApproved() {
        return approved;
    }
    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public ResturantType getResturantType() {
        return resturantType;
    }

    public void setResturantType(ResturantType resturantType) {
        this.resturantType = resturantType;
    }

    public byte[] getBankDetails() {
        return bankDetails;
    }

    public void setBankDetails(byte[] bankDetails) {
        this.bankDetails = bankDetails;
    }

    public Set<Menu> getMenu() {
        return menu;
    }

    public void setMenu(Set<Menu> menu) {
        this.menu = menu;
    }

    public Set<ResturantRating> getResturantRatings() {
        return resturantRatings;
    }

    public void setResturantRatings(Set<ResturantRating> resturantRatings) {
        this.resturantRatings = resturantRatings;
    }

    public Set<ResturantPayment> getResturantPayments() {
        return resturantPayments;
    }

    public void setResturantPayments(Set<ResturantPayment> resturantPayments) {
        this.resturantPayments = resturantPayments;
    }

}
