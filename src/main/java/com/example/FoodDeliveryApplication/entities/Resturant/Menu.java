package com.example.FoodDeliveryApplication.entities.Resturant;

import java.util.List;

import com.example.FoodDeliveryApplication.entities.Enums.MenuCategory;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int menuId;
    @NotNull(message = "Need the resturant id for creating menu item")
    private int resturantId;
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
    
    public Menu(){}

    public int getMenuId() {
        return menuId;
    }
    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }
    public int getResturantId() {
        return resturantId;
    }
    public void setResturantId(int resturantId) {
        this.resturantId = resturantId;
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
}
