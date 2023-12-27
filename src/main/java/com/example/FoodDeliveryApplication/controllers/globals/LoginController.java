package com.example.FoodDeliveryApplication.controllers.globals;

import org.springframework.web.bind.annotation.RestController;

import com.example.FoodDeliveryApplication.config.JwtService;
import com.example.FoodDeliveryApplication.entities.globals.LoginDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

    @PostMapping(path="/login")
    public ResponseEntity<String> login(@RequestBody LoginDetails loginDetails) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDetails.getUserName(), loginDetails.getPassword()));
        if(authentication.isAuthenticated())
            return ResponseEntity.ok().body(jwtService.generateToken(loginDetails.getUserName()));
        else
            return ResponseEntity.status(401).body("Invalid credentials");
    }
    
}
