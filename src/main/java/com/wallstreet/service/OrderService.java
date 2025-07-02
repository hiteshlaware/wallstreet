package com.wallstreet.service;

import org.springframework.stereotype.Service;

import com.wallstreet.dto.OrderCreateDto;
import com.wallstreet.dto.TargetType;
import com.wallstreet.model.Account;
import com.wallstreet.model.Order;
import com.wallstreet.model.Security;
import com.wallstreet.repository.OrderRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    
    private final OrderRepository orderRepository;
    private final SecurityService securityService;
    private final AccountService accountService;
    
    public OrderService(OrderRepository orderRepository,
     SecurityService securityService,
     AccountService accountService) {
        this.orderRepository = orderRepository;
        this.securityService = securityService;
        this.accountService = accountService;
    }
    
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getAccountOrders(Long accountId) {
        Optional<Account> account = accountService.getAccountById(accountId);
        if (account.isEmpty()) {
            throw new IllegalArgumentException("Account not found for id: " + accountId);
        }
        return orderRepository.findByAccountId(accountId);
    }

    public Order createOrder(Long accountId, OrderCreateDto orderCreateDto) {
        System.out.println("accountId: " + accountId);
        System.out.println("OrderCreateDto: " + orderCreateDto.toString());
        if (!accountId.equals(orderCreateDto.getAccountId())) {
            throw new IllegalArgumentException("Account id does not match");
        }

        Security security = securityService.findBySymbol(orderCreateDto.getSymbol())
            .orElseThrow(() -> new IllegalArgumentException("Security not found for symbol: " + orderCreateDto.getSymbol()));

        Account account = accountService.getAccountById(orderCreateDto.getAccountId())
            .orElseThrow(() -> new IllegalArgumentException("Account not found for id: " + orderCreateDto.getAccountId()));

        Order order = new Order();
        order.setSecurity(security);
        order.setAccount(account);
        order.setOrderType(orderCreateDto.getOrderType());
        order.setPrice(security.getPrice());

        // Calculate quantity and amount based on TargetType
        if (orderCreateDto.getTargetType() == TargetType.SHARES) {
            order.setQuantity(orderCreateDto.getTarget());
            order.setAmount(security.getPrice().multiply(BigDecimal.valueOf(orderCreateDto.getTarget())));
        } else { // TargetType.Dollars
            BigDecimal dollars = BigDecimal.valueOf(orderCreateDto.getTarget());
            order.setAmount(dollars);
            order.setQuantity(dollars.divide(security.getPrice(), java.math.RoundingMode.HALF_UP).doubleValue());
        }

        return orderRepository.save(order);
    }

    public Order updateOrder(Long accountId, Long id, Order order) {
        Optional<Account> account = accountService.getAccountById(accountId);
        if (account.isEmpty()) {
            throw new IllegalArgumentException("Account not found for id: " + accountId);
        }
        if (orderRepository.existsById(id)) {
            order.setId(id);
            return orderRepository.save(order);
        }
        return null;
    }
    
    public boolean deleteOrder(Long accountId, Long id) {
        Optional<Account> account = accountService.getAccountById(accountId);
        if (account.isEmpty()) {
            throw new IllegalArgumentException("Account not found for id: " + accountId);
        }
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
