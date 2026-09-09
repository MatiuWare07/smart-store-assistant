package com.mateo.smart_store_assistant.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

/**
 * Utility class for JWT token generation and validation.
 * Handles creation, parsing, and expiration checking of JWT tokens.
 *
 * @author Mateo Fitipaldi
 * @version 1.0
 */
@Component
public class JwtUtil {

    /**
     * Secret key used to sign JWT tokens, injected from application properties.
     */
    @Value("${jwt.secret}")
    private String secret;

    /**
     * Token expiration time in milliseconds, injected from application properties.
     */
    @Value("${jwt.expiration}")
    private long expiration;

    /**
     * Builds a cryptographic signing key from the configured secret.
     *
     * @return SecretKey instance for signing/verifying tokens
     */
    private SecretKey getSigningKey(){
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    /**
     * Generates a JWT token for the given username.
     *
     * @param username the authenticated user's username
     * @return signed JWT token string
     */
    public String generateToken(String username){
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey())
                .compact();
    }

    /**
     * Extracts the username from a JWT token.
     *
     * @param token the JWT token string
     * @return username stored in the token's subject claim
     */
    public String extractUsername(String token){
        return extractClaims(token).getSubject();
    }

    /**
     * Validates a JWT token against the given username.
     *
     * @param token    the JWT token string
     * @param username the expected username
     * @return true if the token is valid and not expired, false otherwise
     */
    public boolean validateToken(String token, String username){
        return extractUsername(token).equals(username) && !isTokenExpired(token);
    }

    /**
     * Checks whether a JWT token has expired.
     *
     * @param token the JWT token string
     * @return true if the token is expired, false otherwise
     */
    private boolean isTokenExpired(String token){
        return extractClaims(token).getExpiration().before(new Date());
    }

    /**
     * Parses and returns all claims from a JWT token.
     *
     * @param token the JWT token string
     * @return Claims object containing all token data
     */
    private Claims extractClaims(String token){
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}