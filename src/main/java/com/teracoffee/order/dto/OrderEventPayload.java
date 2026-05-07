package com.teracoffee.order.dto;

import com.teracoffee.order.entity.Order;

public record OrderEventPayload(
        String userId,
        Long menuId,
        long paymentAmount
) {
    public static OrderEventPayload from(Order order) {
        return new OrderEventPayload(
                order.getUserId(),
                order.getMenuId(),
                order.getPaymentAmount()
        );
    }
}
