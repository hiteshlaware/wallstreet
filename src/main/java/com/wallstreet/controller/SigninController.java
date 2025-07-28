package com.wallstreet.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.wallstreet.dto.AuthResponse;
import com.wallstreet.dto.SigninRequest;
import com.wallstreet.dto.SignupRequestDto;
import com.wallstreet.dto.UserDTO;
import com.wallstreet.model.Account;
import com.wallstreet.repository.AccountRepository;
import com.wallstreet.security.JwtUtil;
import com.wallstreet.service.SigninService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class SigninController {
    
    private final SigninService signinService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final AccountRepository accountRepository;
    
    public SigninController(SigninService signinService, JwtUtil jwtUtil,
                           AuthenticationManager authenticationManager, AccountRepository accountRepository) {
        this.signinService = signinService;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.accountRepository = accountRepository;
    }
    
    // signin endpoint removed - consolidated with login endpoint
    
    /**
     * Authenticate user and generate JWT token
     * 
     * @param signinRequest containing username and password
     * @return user details and JWT token if authenticated, 401 if not
     */
    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody SigninRequest signinRequest) {
        try {
            // Try to authenticate with Spring Security
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            signinRequest.getUsername(), 
                            signinRequest.getPassword()
                    )
            );
            
            // Find the account by username
            Optional<Account> accountOpt = accountRepository.findAll().stream()
                    .filter(a -> a.getUsername().equals(signinRequest.getUsername()) || 
                                 a.getEmail().equals(signinRequest.getUsername()))
                    .findFirst();
            
            if (accountOpt.isPresent()) {
                Account account = accountOpt.get();
                
                // Generate JWT token
                UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                String jwt = jwtUtil.generateToken(userDetails.getUsername());
                
                // Create and return response with user data and token
                UserDTO userDTO = new UserDTO(account);
                AuthResponse authResponse = new AuthResponse(userDTO, jwt);
                
                return ResponseEntity.ok(authResponse);
            }
            
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Invalid username or email");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(errorResponse);
            
        } catch (BadCredentialsException e) {
            // Return 401 Unauthorized if authentication fails
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Invalid credentials");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(errorResponse);
        }
    }
    
    /**
     * Register a new user
     * @param signupRequestDto containing user registration details
     * @return created Account if successful, 400 Bad Request if validation fails
     */
    @PostMapping("/signup")
    public ResponseEntity<Account> signup(@RequestBody SignupRequestDto signupRequestDto) {
        Account createdAccount = signinService.signup(signupRequestDto);
        
        if (createdAccount != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdAccount);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
