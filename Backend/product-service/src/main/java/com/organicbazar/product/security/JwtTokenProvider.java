package com.organicbazar.product.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret:${JWT_SECRET}}")
    private String jwtSecret;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public List<GrantedAuthority> getAuthoritiesFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        Object rolesObj = claims.get("role"); // or "roles" if array
        List<GrantedAuthority> authorities = new ArrayList<>();

        if (rolesObj instanceof List<?> list) {
            for (Object role : list) {
                authorities.add(new SimpleGrantedAuthority("ROLE_" + role.toString()));
            }
        } else if (rolesObj instanceof String roleString) {
            for (String role : roleString.split(",")) {
                authorities.add(new SimpleGrantedAuthority("ROLE_" + role.trim()));
            }
        }

        return authorities;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token);
            return true;
        } catch (JwtException ex) {
            ex.printStackTrace(); // Optional: for debugging
            return false;
        }
    }
}
