package com.teracoffee.order.dto;

import com.teracoffee.order.entity.Order;
import com.teracoffee.order.entity.OrderStatus;

public record CreateOrderResponse(
        Long orderId,
        String userId,
        Long menuId,
        String menuName,
        long paymentAmount,
        OrderStatus status
) {

    public static CreateOrderResponse from(Order order) {
        return new CreateOrderResponse(
                order.getId(),
                order.getUserId(),
                order.getMenuId(),
                order.getMenuName(),
                order.getPaymentAmount(),
                order.getStatus()
        );
    }
}
