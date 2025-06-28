package com.wallstreet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wallstreet.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    // You can add custom query methods here if needed
}
