package com.wallstreet.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.wallstreet.dto.SigninDto;
import com.wallstreet.dto.SignupDto;
import com.wallstreet.model.Account;
import com.wallstreet.service.SigninService;

@RestController
@RequestMapping("/api/auth")
public class SigninController {
    
    private final SigninService signinService;
    
    public SigninController(SigninService signinService) {
        this.signinService = signinService;
    }
    
    /**
     * Authenticate a user
     * @param signinDto containing email and password
     * @return Account if authentication successful, 401 Unauthorized otherwise
     */
    @PostMapping("/signin")
    public ResponseEntity<Account> signin(@RequestBody SigninDto signinDto) {
        return signinService.signin(signinDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }
    
    /**
     * Register a new user
     * @param signupDto containing user registration details
     * @return created Account if successful, 400 Bad Request if validation fails
     */
    @PostMapping("/signup")
    public ResponseEntity<Account> signup(@RequestBody SignupDto signupDto) {
        Account createdAccount = signinService.signup(signupDto);
        
        if (createdAccount != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdAccount);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
