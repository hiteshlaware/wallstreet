package com.wallstreet.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.Base64;
import java.util.Date;

/**
 * Simple JWT utility class that provides token generation and validation.
 * This implementation avoids using external JWT libraries to prevent dependency issues.
 */
@Component
public class JwtUtil {

    @Value("${jwt.secret:defaultsecretkey}")
    private String secret;
    
    @Value("${jwt.expiration:86400000}") // Default to 24 hours (in milliseconds)
    private long jwtExpiration;

    /**
     * Generate a simple JWT token for a given username
     * 
     * @param username the username to create token for
     * @return JWT token string
     */
    public String generateToken(String username) {
        // Create a simple token with header, payload and signature
        
        // Create simple header
        String header = Base64.getEncoder().encodeToString("{\"alg\":\"HS256\",\"typ\":\"JWT\"}".getBytes());
        
        // Create payload with username and expiration
        long now = System.currentTimeMillis();
        long expiration = now + jwtExpiration;
        
        String payload = Base64.getEncoder().encodeToString(
            String.format("{\"%s\":\"%s\",\"%s\":%d,\"%s\":%d}", 
                "sub", username, 
                "iat", now/1000, 
                "exp", expiration/1000)
            .getBytes());
        
        // Create signature (simplified)
        String content = header + "." + payload;
        String signature = Base64.getEncoder().encodeToString(
            (content + secret).getBytes());
        
        return content + "." + signature;
    }

    /**
     * Extract username from token
     * 
     * @param token JWT token
     * @return username
     */
    public String extractUsername(String token) {
        try {
            // Get payload part (the second part of JWT token)
            String[] parts = token.split("\\.");
            if (parts.length != 3) {
                return null;
            }
            
            // Decode payload
            String payload = new String(Base64.getDecoder().decode(parts[1]));
            
            // Extract subject claim (username)
            int startIndex = payload.indexOf("\"sub\":\"") + 7;
            int endIndex = payload.indexOf("\"", startIndex);
            
            if (startIndex < 7 || endIndex < 0) {
                return null;
            }
            
            return payload.substring(startIndex, endIndex);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Validate token for a specific user
     * 
     * @param token JWT token
     * @param username username to validate against
     * @return true if valid, false otherwise
     */
    public Boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return extractedUsername != null && extractedUsername.equals(username) && !isTokenExpired(token);
    }
    
    /**
     * Validate token for a user details object
     * 
     * @param token JWT token
     * @param userDetails user details object
     * @return true if valid, false otherwise
     */
    public Boolean validateToken(String token, Object userDetails) {
        String username = userDetails.toString();
        // Try to extract username using reflection to avoid direct Spring Security dependency
        try {
            java.lang.reflect.Method method = userDetails.getClass().getMethod("getUsername");
            Object result = method.invoke(userDetails);
            if (result != null) {
                username = result.toString();
            }
        } catch (Exception e) {
            // Fallback to toString if getUsername method doesn't exist
        }
        return validateToken(token, username);
    }
    
    /**
     * Check if token is expired
     * 
     * @param token JWT token
     * @return true if expired, false otherwise
     */
    private Boolean isTokenExpired(String token) {
        try {
            // Get payload part
            String[] parts = token.split("\\.");
            if (parts.length != 3) {
                return true;
            }
            
            // Decode payload
            String payload = new String(Base64.getDecoder().decode(parts[1]));
            
            // Extract expiration claim
            int startIndex = payload.indexOf("\"exp\":") + 6;
            int endIndex = payload.indexOf("}", startIndex);
            
            if (startIndex < 6 || endIndex < 0) {
                return true;
            }
            
            String expStr = payload.substring(startIndex, endIndex);
            expStr = expStr.split(",")[0].trim();
            long exp = Long.parseLong(expStr) * 1000; // Convert seconds to milliseconds
            
            return exp < System.currentTimeMillis();
        } catch (Exception e) {
            return true;
        }
    }
}
