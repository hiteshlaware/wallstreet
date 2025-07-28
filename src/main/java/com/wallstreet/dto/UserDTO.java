package com.wallstreet.dto;

import com.wallstreet.model.Account;
import java.math.BigDecimal;

/**
 * DTO for transferring user data without sensitive information like passwords
 */
public class UserDTO {
    private Long id;
    private String name;
    private String username;
    private String email;
    private String phone;
    private BigDecimal balance;
    
    // Default constructor
    public UserDTO() {
    }
    
    // Constructor from Account entity
    public UserDTO(Account account) {
        this.id = account.getId();
        this.name = account.getName();
        this.username = account.getUsername();
        this.email = account.getEmail();
        this.phone = account.getPhone();
        this.balance = account.getBalance();
    }
    
    // Getters and setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
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
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public BigDecimal getBalance() {
        return balance;
    }
    
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
