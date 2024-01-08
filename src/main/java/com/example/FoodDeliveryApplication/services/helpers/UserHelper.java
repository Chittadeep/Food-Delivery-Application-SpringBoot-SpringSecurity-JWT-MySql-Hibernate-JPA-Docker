package com.example.FoodDeliveryApplication.services.helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.FoodDeliveryApplication.entities.User.User;
import com.example.FoodDeliveryApplication.repository.User.UserRepository;

public abstract class UserHelper {
    @Autowired
    private UserRepository userRepository;

    //for endpoints which can be triggered by any admin but only user having the userId and its related JWTToke
    protected void validateUserAndAdmin(int userId)
    {
        UserDetails userDetail = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User loggedInUser = userRepository.getUserByMail(userDetail.getUsername());
        if(loggedInUser==null) throw new RuntimeException("any user with the JWT token does not exist");
        if(loggedInUser.isAdmin()) return;
        if(loggedInUser.getUserId()!=userId) throw new RuntimeException("This user cannot access/update other users details");
        
    }

    protected void validateUser(int userId)
    {
        UserDetails userDetail = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User loggedInUser = userRepository.getUserByMail(userDetail.getUsername());
        if(loggedInUser==null) throw new RuntimeException("any user with the JWT token does not exist");
        if(loggedInUser.getUserId()!=userId) throw new RuntimeException("This user cannot access/update other users details");
        
    }
}
