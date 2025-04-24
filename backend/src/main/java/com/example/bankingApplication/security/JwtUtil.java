package com.example.bankingApplication.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import java.nio.charset.StandardCharsets;
import javax.crypto.SecretKey;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

	  	@Value("${jwt.secret}")
	    private String jwtSecret;
	    @Value("${jwt.expiration}")
	    private int jwtExpirationMs;
	    private SecretKey key;
	    
	    public SecretKey getKey() {
	        return key;
	    }
	    
	    @PostConstruct
	    public void init() {
	    	if (jwtSecret == null) {
	            throw new IllegalStateException("JWT secret is not initialized!");
	        }
	        this.key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
	    }
	    
	    
	    public String generateToken(String username) {
	        return Jwts.builder()
	                .setSubject(username)
	                .setIssuedAt(new Date())
	                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
	                .signWith(getKey(), SignatureAlgorithm.HS256)
	                .compact();
	    }
	    
	    public String getUsernameFromToken(String token) {
	        return Jwts.parserBuilder()
	                .setSigningKey(key).build()
	                .parseClaimsJws(token)
	                .getBody()
	                .getSubject();
	    }
	    
	    
	    public boolean validateJwtToken(String token) {
	        try {
	            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
	            return true;
	        } catch (SecurityException e) {
	            System.out.println("Invalid JWT signature: " + e.getMessage());
	        } catch (MalformedJwtException e) {
	            System.out.println("Invalid JWT token: " + e.getMessage());
	        } catch (ExpiredJwtException e) {
	            System.out.println("JWT token is expired: " + e.getMessage());
	        } catch (UnsupportedJwtException e) {
	            System.out.println("JWT token is unsupported: " + e.getMessage());
	        } catch (IllegalArgumentException e) {
	            System.out.println("JWT claims string is empty: " + e.getMessage());
	        }
	        return false;
	    }
}
