package com.wallstreet.dto;

public class SignupRequestDto {
    private String name;
    private String username;
    private String email;
    private String password;
    private String repassword;
    private String phone;
    
    // Default constructor
    public SignupRequestDto() {}
    
    public SignupRequestDto(String name, String username, String email, String password, String repassword, String phone) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.repassword = repassword;
        this.phone = phone;
    }
    
    // Getters and Setters
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getRepassword() {
        return repassword;
    }
    
    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
