package com.example.FoodDeliveryApplication.entities.Rider;

import java.util.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.ColumnDefault;

import com.example.FoodDeliveryApplication.entities.Enums.VehicleType;
import com.example.FoodDeliveryApplication.entities.Order.OrderCustom;
import com.example.FoodDeliveryApplication.model.request.RiderRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
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
    @NotNull(message = "Rider cannot be created without dob")
    @Past(message = "The birthdate of the rider should be in the past")
    @JsonFormat(shape=Shape.STRING, pattern="dd-MM-yyyy")
    private Date dob;
    @NotBlank(message = "Rider cannot be created without state")
    private String state;
    @NotBlank(message = "Rider cannot be created without pincode")
    private String pincode;
    @Size(min=10, max = 10, message = "phone number should be of 10 digits")
    @Column(unique=true)
    private String phoneNumber;
    @Lob
    @Column(length=50000000)
    private byte[] image;
    
    @NotNull(message = "Rider cannot be created without mail")
    @Email(message = "The email address is invalid")
    @Column(unique = true)
    private String mail;
    @Lob
    @Column(length=50000000)
    private byte[] dl;
    // @NotEmpty(message = "Rider cannot be created with empty bank account info")
    // private List<byte[]> bankAccountInfo;

    @NotNull(message = "Rider cannot be created without vehicleType")
    @Enumerated(value = EnumType.STRING)
    private VehicleType vehicleType;
    @NotBlank(message = "Rider cannot be created without vehicle")
    private String vehicle;
    @NotNull(message = "Rider cannot be created without Latitude")
    private double latitude;
    @NotNull(message = "Rider cannot be created without Longitude")
    private double longitude;
    @ColumnDefault(value = "true")
    private boolean isAvailable;

    //foreign relations inverse side

    @OneToMany(mappedBy = "rider")
    private Set<RiderRating> riderRatings;
    @OneToMany(mappedBy = "rider")
    private Set<OrderCustom> orders;

    public Rider(){}

    public Rider(RiderRequest riderRequest)
    {
        this.name = riderRequest.getName();
        this.city = riderRequest.getCity();
        this.address = riderRequest.getAddress();
        this.dob = riderRequest.getDob();
        this.state = riderRequest.getState();
        this.pincode = riderRequest.getPincode();
        this.phoneNumber = riderRequest.getPhoneNumber();
        this.mail = riderRequest.getMail();
        this.vehicleType = riderRequest.getVehicleType();
        this.latitude = riderRequest.getLatitude();
        this.longitude = riderRequest.getLongitude();
    }

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
    public String getVehicle() {
        return vehicle;
    }
    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }
    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(double latittude) {
        this.latitude = latittude;
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

    // public List<byte[]> getBankAccountInfo() {
    //     return bankAccountInfo;
    // }

    // public void setBankAccountInfo(List<byte[]> bankAccountInfo) {
    //     this.bankAccountInfo = bankAccountInfo;
    // }

}
