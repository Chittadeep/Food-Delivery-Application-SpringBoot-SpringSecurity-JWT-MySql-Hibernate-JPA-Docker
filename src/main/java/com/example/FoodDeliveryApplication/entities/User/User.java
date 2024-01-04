package com.example.FoodDeliveryApplication.entities.User;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.ColumnDefault;

import com.example.FoodDeliveryApplication.entities.Order.OrderCustom;
import com.example.FoodDeliveryApplication.entities.Resturant.ResturantRating;
import com.example.FoodDeliveryApplication.entities.Rider.RiderRating;
import com.example.FoodDeliveryApplication.model.request.UserRequest;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userId")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    @NotBlank(message = "Name of an user cannot be blank")
    private String name;
    @Size(min=10, max = 10, message = "phone number should be of 10 digits")
    @Column(unique = true)
    private String phoneNumber;
    @NotBlank(message = "Email of an user cannot be blank")
    @Email(message = "The email address is invalid")
    @Column(unique=true)
    private String mail;
    @Lob
    @Column(length=50000000)
    private byte[] image;
    @ColumnDefault(value = "true")
    private boolean valid;
    @ColumnDefault(value = "false")
    private boolean isAdmin;


    //foreign relations inverse side
    // @JsonIgnore
    @OneToMany
    //@JsonManagedReference
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private Set<Address> address;

    @JsonIgnore
    @OneToMany(mappedBy ="user")
    private Set<OrderCustom> orders;

    @OneToMany(mappedBy = "user")
    private Set<RiderRating> riderRatings;

    @OneToMany(mappedBy = "user")
    private Set<ResturantRating> resturantRatings;

    public User(){}
    
    public User(UserRequest userRequest)
    {
        this.name=userRequest.getName();
        this.phoneNumber = userRequest.getPhoneNumber();
        this.mail = userRequest.getMail();
    }

    public User(String name, String phoneNumber, String mail) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.mail = mail;
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


    public Set<Address> getAddress() {
        return address;
    }

    public void setAddress(Set<Address> address) {
        this.address = address;
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
    
    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

}
