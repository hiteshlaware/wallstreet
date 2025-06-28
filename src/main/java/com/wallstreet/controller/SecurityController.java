package com.walstreet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.walstreet.model.Security;
import com.walstreet.service.SecurityService;

import java.util.List;

@RestController
@RequestMapping("/api/securities")
public class SecurityController {
    
    private final SecurityService securityService;
    
    @Autowired
    public SecurityController(SecurityService securityService) {
        this.securityService = securityService;
    }
    
    @GetMapping
    public List<Security> getAllSecurities() {
        return securityService.getAllSecurities();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Security> getSecurityById(@PathVariable Long id) {
        return securityService.getSecurityById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public Security createSecurity(@RequestBody Security security) {
        return securityService.createSecurity(security);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Security> updateSecurity(@PathVariable Long id, @RequestBody Security security) {
        Security updatedSecurity = securityService.updateSecurity(id, security);
        if (updatedSecurity != null) {
            return ResponseEntity.ok(updatedSecurity);
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSecurity(@PathVariable Long id) {
        if (securityService.deleteSecurity(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
