package com.walstreet.dto;

import com.walstreet.model.OrderType;

public class OrderCreateDto {
    private Long accountId;
    private String symbol;
    private OrderType orderType;
    private TargetType targetType;
    private Double target;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public TargetType getTargetType() {
        return targetType;
    }

    public void setTargetType(TargetType targetType) {
        this.targetType = targetType;
    }

    public Double getTarget() {
        return target;
    }

    public void setTarget(Double target) {
        this.target = target;
    }

    @Override
    public String toString() {
        return "OrderCreateDto [accountId=" + accountId + ", symbol=" + symbol + ", orderType=" + orderType + ", targetType=" + targetType + ", target=" + target + "]";
    }
}
