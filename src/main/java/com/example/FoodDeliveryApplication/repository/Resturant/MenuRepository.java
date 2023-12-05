package com.example.FoodDeliveryApplication.repository.Resturant;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.FoodDeliveryApplication.entities.Enums.MenuCategory;
import com.example.FoodDeliveryApplication.entities.Resturant.Menu;

@Repository
public interface MenuRepository extends CrudRepository<Menu, Integer> {
    //@Query("Select m from Menu m where m.resturantId = :resturantId")
    public List<Menu> getMenuByResturant_ResturantId(int resturantId);

    //@Query("Select m from Menu m where m.menuCategory = :menuCategory")
    public List<Menu> getMenuByMenuCategory(MenuCategory menuCategory);

    //@Query("Select m from Menu m where m.price = :price")
    public List<Menu> getMenuByPrice(double price);

    /*@Query("Select m from Menu m order by price asc")
    public List<Menu> getAllMenuItemsSortedByPriceLowToHigh(double price);

    @Query("Select m from Menu m order by price desc")
    public List<Menu> getAllMenuItemsSortedByPriceHighToLow(double price);
    */
}
