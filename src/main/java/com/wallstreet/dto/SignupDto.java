package com.wallstreet.dto;

public class SignupDto {
    private String name;
    private String email;
    private String password;
    private String repassword;
    private String phone;
    
    // Default constructor
    public SignupDto() {}
    
    public SignupDto(String name, String email, String password, String repassword, String phone) {
        this.name = name;
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
