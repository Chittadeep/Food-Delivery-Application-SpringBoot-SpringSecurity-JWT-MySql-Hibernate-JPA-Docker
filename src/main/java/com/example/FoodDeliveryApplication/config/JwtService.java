package com.example.FoodDeliveryApplication.config;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.*;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {

    public static final String SECRET = "ABCDEFGHIJKLMNOPQRSTWUVXYZ1234567890";

    public String generateToken(String username)
    { 
        Map<String, Object> map= new HashMap<>();
        return createToken(map, username);
    }

    private String createToken(Map<String, Object> claims, String username){
        return Jwts.builder().claims(claims)
        .setSubject(username)
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis()+(10*60*1000)))
        .signWith(getKey(),SignatureAlgorithm.HS256)
        .compact();
    }


    private Key getKey()
    {
        return Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    private Claims getClaims(String token)
    {
        return Jwts.parser().setSigningKey(getKey()).build().parseClaimsJws(token).getBody();
    }
    //public boolean validateToken(String token, UserDetails userDetails)
    public String extractUserName(String token)
    {
        return extractClaim(token, Claims::getSubject);
        //return extractClaim(token, (claim, String)->claim.getSubject());
    }
    public Date extractExpiration(String token)
    {
        return extractClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token)
    {
        return extractExpiration(token).before(new Date());
    }

    public boolean validateToken(String token){return true;}

    public <T> T extractClaim(String token, Function<Claims, T> claimResolver)
    {
        Claims claims = getClaims(token);
        return claimResolver.apply(claims);
    }

    /*public static void main(String[] args) {
        JwtService jwtService = new JwtService();
        String token = jwtService.generateToken("Chittadeep");
        System.err.println(token);
    }*/
}
