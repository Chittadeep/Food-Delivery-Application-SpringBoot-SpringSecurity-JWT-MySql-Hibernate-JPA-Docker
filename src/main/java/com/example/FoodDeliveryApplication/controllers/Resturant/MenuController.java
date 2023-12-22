package com.example.FoodDeliveryApplication.controllers.Resturant;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.FoodDeliveryApplication.entities.Enums.MenuCategory;
import com.example.FoodDeliveryApplication.entities.Resturant.Menu;
import com.example.FoodDeliveryApplication.services.Resturant.MenuService;

@RestController
public class MenuController {
    @Autowired
    private MenuService menuService;

    @PostMapping(path = "/menu")
    public ResponseEntity<Menu> createMenuItem(@RequestBody Menu menu)
    {
        return new ResponseEntity<Menu>(menuService.createMenuItem(menu), HttpStatus.CREATED);
    }

    @GetMapping(path = "/admin/menu")
    public ResponseEntity<List<Menu>> getAllMenusOutThere()
    {
        return new ResponseEntity<List<Menu>>(menuService.getAllMenusOutThere(), HttpStatus.OK);
    }

    @GetMapping(path = "/menu/{id}")
    public ResponseEntity<Menu> getMenuById(@PathVariable int menuId)
    {
        return new ResponseEntity<Menu>(menuService.getMenuById(menuId), HttpStatus.OK);
    }

    @GetMapping(path = "/resturant/{resturantId}/menu")
    public ResponseEntity<List<Menu>> getMenuByResturantId(@PathVariable int resturantId)
    {
        return new ResponseEntity<List<Menu>>(menuService.getMenuByResturantId(resturantId), HttpStatus.OK);
    }

    @PutMapping(path = "/resturant/menu")
    public ResponseEntity<Menu> updateMenu(@RequestBody Menu menu)
    {
        return new ResponseEntity<Menu>(menuService.updateMenu(menu), HttpStatus.ACCEPTED);
    } 

    @GetMapping(path = "/user/menu/category/{category}")
    public ResponseEntity<List<Menu>> getMenuByMenuCategory(@PathVariable MenuCategory menuCategory)
    {
        return new ResponseEntity<List<Menu>>(menuService.getMenuByMenuCategory(menuCategory), HttpStatus.OK);
    }

    @GetMapping(path="/user/menu/price/{price}")
    public ResponseEntity<List<Menu>> getMenuByPrice(@PathVariable double price)
    {
        return new ResponseEntity<List<Menu>>(menuService.getMenuByPrice(price), HttpStatus.OK);
    }

    @DeleteMapping(path="/resturant/menu/delete/{menuId}")
    public ResponseEntity<Boolean> deleteMenu(@PathVariable int menuId)
    {
        return new ResponseEntity<Boolean>(menuService.deleteMenu(menuId), HttpStatus.OK);
    }

    
    @PostMapping(path="/menu/createFromCSV")
    public ResponseEntity<List<Menu>> createFromCSV(@RequestParam("file") MultipartFile file) throws IOException
    {
        return new ResponseEntity<List<Menu>>(menuService.createMenusFromCSV(file), HttpStatus.CREATED);
    }
    
}
