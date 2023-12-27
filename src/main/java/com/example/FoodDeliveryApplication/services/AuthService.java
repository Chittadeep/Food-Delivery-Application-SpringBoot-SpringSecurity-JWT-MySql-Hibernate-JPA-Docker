package com.example.FoodDeliveryApplication.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.FoodDeliveryApplication.entities.User.User;
import com.example.FoodDeliveryApplication.entities.globals.LoginDetails;
import com.example.FoodDeliveryApplication.repository.User.UserRepository;
import com.example.FoodDeliveryApplication.repository.globals.LoginDetailsRepository;

@Component  
public class AuthService implements UserDetailsService {
    @Autowired
    private LoginDetailsRepository loginDetailsRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        LoginDetails loginDetails = loginDetailsRepository.getLoginDetailsByUserName(username);
        return org.springframework.security.core.userdetails.User.builder().username(loginDetails.getUserName()).password(loginDetails.getPassword()).authorities(loginDetails.getRole().name()).build();
    }
    
}
