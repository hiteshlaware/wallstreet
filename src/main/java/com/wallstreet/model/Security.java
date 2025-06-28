package com.walstreet.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import java.math.BigDecimal;

@Entity
public class Security {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Enumerated(EnumType.STRING)
    private SecurityType securityType;
    
    private BigDecimal price;
    
    @Column(length = 8)
    private String symbol;
    
    // Default constructor required by JPA
    public Security() {}
    
    public Security(SecurityType securityType, BigDecimal price, String symbol) {
        this.securityType = securityType;
        this.price = price;
        this.symbol = symbol;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public SecurityType getSecurityType() {
        return securityType;
    }
    
    public void setSecurityType(SecurityType securityType) {
        this.securityType = securityType;
    }
    
    public BigDecimal getPrice() {
        return price;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    public String getSymbol() {
        return symbol;
    }
    
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
