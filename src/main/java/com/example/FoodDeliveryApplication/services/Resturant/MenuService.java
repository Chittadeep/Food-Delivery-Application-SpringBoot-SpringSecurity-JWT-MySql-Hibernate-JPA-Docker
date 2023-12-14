package com.example.FoodDeliveryApplication.services.Resturant;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.FoodDeliveryApplication.entities.Enums.MenuCategory;
import com.example.FoodDeliveryApplication.entities.Resturant.Menu;
import com.example.FoodDeliveryApplication.exceptions.EntityDoesNotExistException;
import com.example.FoodDeliveryApplication.repository.Resturant.MenuRepository;

@Service
public class MenuService {
    @Autowired
    private MenuRepository menuRepository;
    
    public Menu createMenuItem(Menu menu)
    {
        menuRepository.save(menu);
        return menu;
    }

    public List<Menu> getAllMenusOutThere()
    {
        return (List<Menu>) menuRepository.findAll();
    }

    public Menu getMenuById(int menuId)
    {
        return menuRepository.findById(menuId).orElseThrow(()->new EntityDoesNotExistException("No menu item with this id exists"));
    }

    public List<Menu> getMenuByResturantId(int resturantId)
    {
        return menuRepository.getMenuByResturantId(resturantId);
    }
    
    public List<Menu> getMenuByMenuCategory(MenuCategory menuCategory)
    {
        return menuRepository.getMenuByMenuCategory(menuCategory);
    }

    public List<Menu> getMenuByPrice(double price)
    {
        return menuRepository.getMenuByPrice(price);
    }
   

    public Menu updateMenu(Menu menu)
    {
        Menu oldMenu = getMenuById(menu.getMenuId());
        oldMenu.setImages(menu.getImages());
        oldMenu.setItemName(menu.getItemName());
        oldMenu.setMenuCategory(menu.getMenuCategory());
        oldMenu.setPrice(menu.getPrice());
        oldMenu.setDescription(menu.getDescription());
        menuRepository.save(oldMenu);
        //why cant we save the new menu directly when both old menu and new menu have their primary keys equal?
        return oldMenu;
    }

    public boolean deleteMenu(int menuId)
    {
        menuRepository.deleteById(menuId);
        return true;
    }
/*
    public List<Menu> createMenusFromCSV(MultipartFile multipartFile) throws IOException
    {
        byte[] bytes = multipartFile.getBytes();
        File file = new File(UUID.randomUUID().toString());
        //Files.write(file.getPath(), bytes);
        FileOutputStream stream = new FileOutputStream(file.getPath());
        //InputStream stream = new ByteArrayInputStream(bytes);
        stream.write(bytes);
        FileReader fileReader = new FileReader(file.getPath());
        List<Menu> list = new ArrayList<Menu>();
        try(CSVReader csvReader = new CSVReaderBuilder(fileReader).withSkipLines(1).build();){
        String[] row;
        int resturantId;
        double price;
        String itemName, description;
        MenuCategory menuCategory;
        while((row = csvReader.readNext())!=null)
        {
            System.out.println("Inside the menu service createMenusFromCSV function"+row[0]);
            resturantId=Integer.parseInt(row[0]);
            itemName=row[1];
            description=row[2];
            price=Double.parseDouble(row[3]);
            menuCategory = MenuCategory.getMenuCategory(row[4]);
            Menu menu = new Menu();
            menu.setDescription(description);
            menu.setItemName(itemName);
            menu.setPrice(price);
            menu.setMenuCategory(menuCategory);
            menu.setResturantId(resturantId);
            list.add(menu);
        }
        menuRepository.saveAll(list);
        }
        
    return list;
    }
    */

}
