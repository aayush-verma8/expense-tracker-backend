package com.aayush.expense_tracker.service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;


@Service

public class JwtService {
    private static final String SECRET="mysecretkeymysecretkeymysecretkey12";


    private final Key key=Keys.hmacShaKeyFor(SECRET.getBytes());

    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60))
                .signWith(key)
                .compact();

    }
    public String extractEmail(String token){
        Claims claims=Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();

    }

}
