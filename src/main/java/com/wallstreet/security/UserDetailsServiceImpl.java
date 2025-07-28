package com.wallstreet.security;

import com.wallstreet.model.Account;
import com.wallstreet.repository.AccountRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AccountRepository accountRepository;

    public UserDetailsServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Find account by username or email
        Optional<Account> accountOptional = accountRepository.findAll().stream()
                .filter(account -> account.getUsername().equals(username) || account.getEmail().equals(username))
                .findFirst();
        
        Account account = accountOptional.orElseThrow(() -> 
                new UsernameNotFoundException("User not found with username or email: " + username));
        
        // Create Spring Security UserDetails from our Account entity
        return new User(
                account.getUsername(),
                account.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }
}
