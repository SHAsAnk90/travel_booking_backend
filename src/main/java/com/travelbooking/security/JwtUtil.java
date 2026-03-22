package com.travelbooking.security;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.security.Key;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.util.HashMap;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetails;



@Component
public class JwtUtil {

    private final String secretKey = "mysecretKeymysecretKeymysecretKeymysecretKey"; // Should be at least 256 bits for HS256
    public String generateToken(UserDetails userDetails)
    {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", ((CustomUserDetails) userDetails)
                  .getUser()
                  .getRole()
                  .name());   
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 day
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }
    public String extractUsername(String token)
    {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    public boolean isTokenValid(String token, String email)
    {
        String username = extractUsername(token);
        return (username.equals(email));
    }
    private Key getSigningKey()
    {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

}

