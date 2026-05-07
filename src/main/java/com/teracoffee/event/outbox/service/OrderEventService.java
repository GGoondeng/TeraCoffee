package com.teracoffee.event.outbox.service;

import com.teracoffee.event.outbox.entity.OrderOutbox;
import com.teracoffee.event.outbox.repository.OrderOutboxRepository;
import com.teracoffee.order.dto.OrderEventPayload;
import com.teracoffee.order.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;


@Service
@RequiredArgsConstructor
public class OrderEventService {

    private final OrderOutboxRepository orderOutboxRepository;
    private final OrderOutboxSender orderOutboxSender;
    private final ObjectMapper objectMapper;

    public void saveAndSendAfterCommit(Order order) {
        OrderEventPayload payload = OrderEventPayload.from(order);
        OrderOutbox outbox = orderOutboxRepository.save(
                OrderOutbox.pending(order.getId(), toJson(payload))
        );

        if (TransactionSynchronizationManager.isSynchronizationActive()) {
            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
                @Override
                public void afterCommit() {
                    orderOutboxSender.send(outbox.getId());
                }
            });
            return;
        }
        orderOutboxSender.send(outbox.getId());
    }

    private String toJson(OrderEventPayload payload) {
        try {
            return objectMapper.writeValueAsString(payload);
        } catch (JacksonException e) {
            throw new IllegalStateException("주문 이벤트 payload 변환에 실패했습니다.", e);
        }
    }
}
