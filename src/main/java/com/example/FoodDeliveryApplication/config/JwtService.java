package com.example.FoodDeliveryApplication.config;

import java.util.Map;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;

@Component
public class JwtService {
    public String createToken(Map<String, Object> claims, String username){ return null;}
    private Claims getClaims(String token){return null;}
    private boolean isTokenExpired(String token) {return true;}
    //public boolean validateToken(String token, UserDetails userDetails)
}
