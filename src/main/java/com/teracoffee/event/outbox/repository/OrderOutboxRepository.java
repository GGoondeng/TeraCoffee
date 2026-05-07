package com.teracoffee.event.outbox.repository;

import com.teracoffee.event.outbox.entity.OrderOutbox;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderOutboxRepository extends JpaRepository<OrderOutbox, Long> {
}