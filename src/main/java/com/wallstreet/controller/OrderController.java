package com.wallstreet.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.wallstreet.dto.OrderCreateDto;
import com.wallstreet.model.Order;
import com.wallstreet.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {
    
    private final OrderService orderService;
    
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    
    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        if (orders.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(orders);
    }
    
    @GetMapping("/accounts/{accountId}/orders")
    public ResponseEntity<List<Order>> getAccountOrders(@PathVariable Long accountId) {
        List<Order> orders = orderService.getAccountOrders(accountId);
        if (orders.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(orders);
    }
    
    @PostMapping("/accounts/{accountId}/orders")
    public ResponseEntity<Order> createOrder(@PathVariable Long accountId,
        @RequestBody OrderCreateDto orderCreateDto) {
        return ResponseEntity.ok(orderService.createOrder(accountId, orderCreateDto));
    }
    
    @PutMapping("/accounts/{accountId}/orders/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long accountId,
        @PathVariable Long id,
        @RequestBody Order order) {
        Order updatedOrder = orderService.updateOrder(accountId, id, order);
        if (updatedOrder != null) {
            return ResponseEntity.ok(updatedOrder);
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/accounts/{accountId}/orders/{id}")  
    public ResponseEntity<Void> deleteOrder(@PathVariable Long accountId, @PathVariable Long id) {
        if (orderService.deleteOrder(accountId, id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
