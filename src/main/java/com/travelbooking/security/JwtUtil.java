package com.travelbooking.security;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import io.jsonwebtoken.security.Keys;


import io.jsonwebtoken.Jwts;

@Component
public class JwtUtil {

    private final String secretKey = "mysecretKeymysecretKeymysecretKeymysecretKey"; // Should be at least 256 bits for HS256
    public String generateToken(String email)
    {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 day
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

}
