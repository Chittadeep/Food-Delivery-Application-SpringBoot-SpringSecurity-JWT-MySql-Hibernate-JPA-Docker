package com.example.FoodDeliveryApplication.entities.Enums;

import com.example.FoodDeliveryApplication.exceptions.EnumOptionNotAvailableException;

public enum MenuCategory {
    SPICY, NON_SPICY, BENGALI, NORTH_INDIAN, SOUTH_INDIAN;

    public static MenuCategory getMenuCategory(String menuCategory) throws EnumOptionNotAvailableException
    //Can EnumConstantNotPresentException be thrown
    {
        if(menuCategory.equals("SPICY")) return SPICY;
        else if(menuCategory.equals("NON_SPICY")) return NON_SPICY;
        else if(menuCategory.equals("BENGALI")) return BENGALI;
        else if(menuCategory.equals("NORTH_INDIAN")) return NORTH_INDIAN;
        else if(menuCategory.equals("SOUTH_INDIAN")) return SOUTH_INDIAN;
        else throw new RuntimeException("No such menu Category Available");
        
    }
}
