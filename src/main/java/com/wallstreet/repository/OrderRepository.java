package com.wallstreet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wallstreet.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByAccountId(Long accountId);
}
