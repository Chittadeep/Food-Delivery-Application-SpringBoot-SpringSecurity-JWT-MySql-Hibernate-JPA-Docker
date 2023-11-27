package com.example.FoodDeliveryApplication.entities.User;

import java.util.Set;

import org.hibernate.annotations.ColumnDefault;

import com.example.FoodDeliveryApplication.entities.Order.OrderCustom;
import com.example.FoodDeliveryApplication.entities.Rider.RiderRating;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    @NotBlank(message = "Name of an user cannot be blank")
    private String name;
    @Size(min=10, max = 10)
    private String phoneNumber;
    @Email
    private String mail;
    @Lob
    @Column(length=500000)
    private byte[] image;
    @NotBlank(message = "Password cannot be blank")
    private String password;
    @ColumnDefault(value = "true")
    private boolean valid;

    //foreign relations inverse side
    @OneToMany(mappedBy = "user")
    private Set<Address> address;

    @OneToMany(mappedBy ="user")
    private Set<OrderCustom> orders;

    @OneToMany(mappedBy = "user")
    private Set<RiderRating> riderRatings;

    public User(){}
    
    public User(String name, String phoneNumber, String mail, String password) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.mail = mail;
        this.password = password;
    }
    

    public Set<OrderCustom> getOrders() {
        return orders;
    }

    public void setOrders(Set<OrderCustom> orders) {
        this.orders = orders;
    }
    
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Set<Address> getAddress() {
        return address;
    }

    public void setAddress(Set<Address> address) {
        this.address = address;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
    
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
    
}
