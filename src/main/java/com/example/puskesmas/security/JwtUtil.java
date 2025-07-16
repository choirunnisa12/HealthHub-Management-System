package com.example.puskesmas.security;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
@NoArgsConstructor
@AllArgsConstructor
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secretKey; // Secret key for signing and verifying JWTs

    @Value("${jwt.expiration}")
    private long expirationTime; // JWT expiration time in milliseconds

    /**
     * Generate a JWT token for a given username and role.
     * Uses HS256 algorithm and a secret key converted to a cryptographically strong key.
     */
    public String generateToken(String username, String role) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                // Use Keys.hmacShaKeyFor to ensure the secret is a valid key for HS256
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Extract all claims from a JWT token.
     * Uses the same secret key and algorithm as token generation.
     */
    public Claims extractClaims(String token) {
        return Jwts.parser()
                // Use Keys.hmacShaKeyFor to match the signing logic
                .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    public boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    public boolean validateToken(String token, String username) {
        return (username.equals(extractUsername(token)) && !isTokenExpired(token));
    }

    public String getRoleFromToken(String token) {
        return extractClaims(token).get("role", String.class);
    }
}