package com.wallstreet.dto;

/**
 * DTO for signin request containing username and password
 */
public class SigninRequest {
    private String username;
    private String password;
    
    // Default constructor
    public SigninRequest() {
    }
    
    public SigninRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    // Getters and setters
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
}
