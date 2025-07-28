package com.wallstreet.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.wallstreet.dto.SignupRequestDto;
import com.wallstreet.model.Account;
import com.wallstreet.repository.AccountRepository;



@Service
public class SigninService {
    
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    
    public SigninService(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    // signin method removed - consolidated with authentication in SigninController
    
    /**
     * Register a new user account
     * @param signupRequestDto containing user registration details
     * @return created Account if successful, null if validation fails
     */
    public Account signup(SignupRequestDto signupRequestDto) {
        // Validate that passwords match
        if (!signupRequestDto.getPassword().equals(signupRequestDto.getRepassword())) {
            return null;
        }
        
        // Check if email already exists
        boolean emailExists = accountRepository.findAll().stream()
                .anyMatch(account -> account.getEmail().equals(signupRequestDto.getEmail()));
        
        if (emailExists) {
            return null;
        }
        
        // Check if username already exists
        boolean usernameExists = accountRepository.findAll().stream()
                .anyMatch(account -> account.getUsername().equals(signupRequestDto.getUsername()));
        
        if (usernameExists) {
            return null;
        }
        
        // Create new account
        Account newAccount = new Account();
        newAccount.setName(signupRequestDto.getName());
        newAccount.setEmail(signupRequestDto.getEmail());
        newAccount.setPassword(passwordEncoder.encode(signupRequestDto.getPassword())); // Encode password
        newAccount.setPhone(signupRequestDto.getPhone());
        // Use provided username
        newAccount.setUsername(signupRequestDto.getUsername());
        
        return accountRepository.save(newAccount);
    }
}
