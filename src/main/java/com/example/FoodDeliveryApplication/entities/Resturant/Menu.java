package com.example.FoodDeliveryApplication.entities.Resturant;

import java.util.List;

import com.example.FoodDeliveryApplication.entities.Enums.MenuCategory;
import com.example.FoodDeliveryApplication.entities.Order.OrderItem;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int menuId;
    @NotBlank(message = "Item name cannot be null when you are creating the menu item")
    private String itemName;
    @NotBlank(message = "Item cannot be created without description")
    private String description;
    @Lob
    @Column(length =500000)
    private List<Byte[]> images;
    @Column(nullable=false)
    private double price;
    @Enumerated(EnumType.STRING)
    private MenuCategory menuCategory;
    

    //foreign relations owning side
    @ManyToOne()
    @JoinColumn(name = "resturantId", referencedColumnName = "resturantId")
    private Resturant resturant;

    //foreign relations inverse side
    @OneToMany(mappedBy = "menu")
    private List<OrderItem> orderItems;

    public Menu(){}

    public int getMenuId() {
        return menuId;
    }
    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }
    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public List<Byte[]> getImages() {
        return images;
    }
    public void setImages(List<Byte[]> images) {
        this.images = images;
    }
    public MenuCategory getMenuCategory() {
        return menuCategory;
    }
    public void setMenuCategory(MenuCategory menuCategory) {
        this.menuCategory = menuCategory;
    }
    public Resturant getResturant() {
        return resturant;
    }

    public void setResturant(Resturant resturant) {
        this.resturant = resturant;
    }

}
