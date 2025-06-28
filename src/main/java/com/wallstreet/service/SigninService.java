package com.walstreet.service;

import org.springframework.stereotype.Service;

import com.walstreet.dto.SigninDto;
import com.walstreet.dto.SignupDto;
import com.walstreet.model.Account;
import com.walstreet.repository.AccountRepository;

import java.util.Optional;

@Service
public class SigninService {
    
    private final AccountRepository accountRepository;
    
    public SigninService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    
    /**
     * Authenticate a user with email and password
     * @param signinDto containing email and password
     * @return Optional with Account if authentication successful, empty Optional otherwise
     */
    public Optional<Account> signin(SigninDto signinDto) {
        // In a real application, you would use proper password encoding/hashing
        // This is a simplified implementation for demonstration purposes
        return accountRepository.findAll().stream()
                .filter(account -> account.getEmail().equals(signinDto.getEmail()) 
                        && account.getPassword().equals(signinDto.getPassword()))
                .findFirst();
    }
    
    /**
     * Register a new user account
     * @param signupDto containing user registration details
     * @return created Account if successful, null if validation fails
     */
    public Account signup(SignupDto signupDto) {
        // Validate that passwords match
        if (!signupDto.getPassword().equals(signupDto.getRepassword())) {
            return null;
        }
        
        // Check if email already exists
        boolean emailExists = accountRepository.findAll().stream()
                .anyMatch(account -> account.getEmail().equals(signupDto.getEmail()));
        
        if (emailExists) {
            return null;
        }
        
        // Create new account
        Account newAccount = new Account();
        newAccount.setName(signupDto.getName());
        newAccount.setEmail(signupDto.getEmail());
        newAccount.setPassword(signupDto.getPassword());
        newAccount.setPhone(signupDto.getPhone());
        // Generate a username from email if needed
        newAccount.setUsername(signupDto.getEmail().split("@")[0]);
        
        return accountRepository.save(newAccount);
    }
}
