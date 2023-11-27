package com.example.FoodDeliveryApplication.entities.Rider;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.ColumnDefault;

import com.example.FoodDeliveryApplication.entities.Enums.VehicleType;
import com.example.FoodDeliveryApplication.entities.Order.OrderCustom;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
public class Rider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int riderId;
    @NotBlank(message = "Rider cannot be created without name")
    private String name;
    @NotBlank(message = "Rider cannot be created without city")
    private String city;
    @NotBlank(message = "Rider cannot be created without address")
    private String address;
    @NotBlank(message = "Rider cannot be created without dob")
    private Date dob;
    @NotBlank(message = "Rider cannot be created without state")
    private String state;
    @NotBlank(message = "Rider cannot be created without pincode")
    private String pincode;
    @Size(min=10, max = 10)
    private String phoneNumber;
    @Lob
    @Column(length=5000000)
    private byte[] image;
    
    @NotBlank(message = "Rider cannot be created without mail")
    private String mail;
    @NotBlank(message = "Rider cannot be created without password")
    private String password;
    @Lob
    @Column(length=5000000)
    private byte[] dl;
    @NotEmpty(message = "Rider cannot be created with empty bank account info")
    private List<byte[]> bankAccountInfo;

    @NotBlank(message = "Rider cannot be created without vehicleType")
    private VehicleType vehicleType;
    @NotBlank(message = "Rider cannot be created without vehicle")
    private String vehicle;
    @NotBlank(message = "Rider cannot be created without Latitude")
    private double latittude;
    @NotBlank(message = "Rider cannot be created without Longitude")
    private double longitude;
    @ColumnDefault(value = "false")
    private boolean isAvailable;

    //foreign relations inverse side

    @OneToMany(mappedBy = "rider")
    private Set<RiderRating> riderRatings;
    @OneToMany(mappedBy = "rider")
    private Set<OrderCustom> orders;

    public Rider(){}

    public int getRiderId() {
        return riderId;
    }
    public void setRiderId(int riderId) {
        this.riderId = riderId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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
    public Date getDob() {
        return dob;
    }
    public void setDob(Date dob) {
        this.dob = dob;
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
    public String getVehicle() {
        return vehicle;
    }
    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }
    public double getLatittude() {
        return latittude;
    }
    public void setLatittude(double latittude) {
        this.latittude = latittude;
    }
    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    public boolean isAvailable() {
        return isAvailable;
    }
    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public byte[] getDl() {
        return dl;
    }

    public void setDl(byte[] dl) {
        this.dl = dl;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public List<byte[]> getBankAccountInfo() {
        return bankAccountInfo;
    }

    public void setBankAccountInfo(List<byte[]> bankAccountInfo) {
        this.bankAccountInfo = bankAccountInfo;
    }


}
