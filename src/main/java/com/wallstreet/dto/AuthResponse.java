package com.wallstreet.dto;

/**
 * DTO for authentication response containing user details and JWT token
 */
public class AuthResponse {
    private UserDTO user;
    private String token;
    
    // Default constructor
    public AuthResponse() {
    }
    
    // Constructor with all fields
    public AuthResponse(UserDTO user, String token) {
        this.user = user;
        this.token = token;
    }
    
    // Getters and setters
    public UserDTO getUser() {
        return user;
    }
    
    public void setUser(UserDTO user) {
        this.user = user;
    }
    
    public String getToken() {
        return token;
    }
    
    public void setToken(String token) {
        this.token = token;
    }
}
