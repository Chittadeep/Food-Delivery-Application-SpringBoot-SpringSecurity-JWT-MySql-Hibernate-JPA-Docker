package com.example.FoodDeliveryApplication.services.helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.FoodDeliveryApplication.entities.Resturant.Resturant;
import com.example.FoodDeliveryApplication.entities.User.User;
import com.example.FoodDeliveryApplication.repository.Resturant.ResturantRepository;
import com.example.FoodDeliveryApplication.repository.User.UserRepository;

public abstract class ResturantHelper {

    @Autowired
    protected ResturantRepository resturantRepository;
    @Autowired
    protected UserRepository userRepository;

    protected void validateResturant(int resturantId)
    {
        UserDetails resturantDetail = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Resturant loggedInResturant = resturantRepository.getResturantByEmail(resturantDetail.getUsername());
        if(loggedInResturant==null) throw new RuntimeException("This resturant with the JWT token does not exist");
        if(loggedInResturant.getResturantId()!=resturantId) throw new RuntimeException("This resturant cannot modify other resturant details");
    }

    protected void validateResturantAndAdmin(int resturantId)
    {   
        UserDetails resturantDetail = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Resturant loggedInResturant = resturantRepository.getResturantByEmail(resturantDetail.getUsername());
        if(loggedInResturant==null)
        {
            User user = userRepository.getUserByMail(resturantDetail.getUsername());
            if(user==null) throw new RuntimeException("Neither any user resturant nor any admin is registered with the JWT token");
            if(user.isAdmin()) return;
        }
        if(loggedInResturant.getResturantId()!=resturantId) throw new RuntimeException("This resturant cannot modify other resturant details");
    }

}
