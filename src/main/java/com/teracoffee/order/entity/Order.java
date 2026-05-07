package com.teracoffee.order.entity;

import com.teracoffee.menu.entity.Menu;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false, length = 50)
    private String userId;

    @Column(name = "menu_id", nullable = false)
    private Long menuId;

    @Column(name = "menu_name", nullable = false, length = 50)
    private String menuName;

    @Column(name = "payment_amount", nullable = false)
    private long paymentAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private OrderStatus status;

    @Column(name = "ordered_at", nullable = false, updatable = false)
    private LocalDateTime orderedAt;

    private Order(String userId, Menu menu) {
        this.userId = userId;
        this.menuId = menu.getId();
        this.menuName = menu.getName();
        this.paymentAmount = menu.getPrice();
        this.status = OrderStatus.PAID;
    }

    public static Order paid(String userId, Menu menu) {
        return new Order(userId, menu);
    }

    @PrePersist
    void prePersist() {
        this.orderedAt = LocalDateTime.now();
    }
}
