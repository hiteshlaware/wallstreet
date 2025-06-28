package com.walstreet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walstreet.model.Security;
import com.walstreet.repository.SecurityRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SecurityService {
    
    private final SecurityRepository securityRepository;
    
    @Autowired
    public SecurityService(SecurityRepository securityRepository) {
        this.securityRepository = securityRepository;
    }
    
    public List<Security> getAllSecurities() {
        return securityRepository.findAll();
    }
    
    public Optional<Security> getSecurityById(Long id) {
        return securityRepository.findById(id);
    }
    
    public Security createSecurity(Security security) {
        return securityRepository.save(security);
    }
    
    public Security updateSecurity(Long id, Security security) {
        if (securityRepository.existsById(id)) {
            security.setId(id);
            return securityRepository.save(security);
        }
        return null;
    }
    
    public boolean deleteSecurity(Long id) {
        if (securityRepository.existsById(id)) {
            securityRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<Security> findBySymbol(String symbol) {
        return securityRepository.findBySymbol(symbol);
    }
}
