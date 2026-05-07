package com.teracoffee.event.outbox.service;

import com.teracoffee.event.client.DataPlatformClient;
import com.teracoffee.event.outbox.entity.OrderOutbox;
import com.teracoffee.event.outbox.entity.OutboxStatus;
import com.teracoffee.event.outbox.repository.OrderOutboxRepository;
import com.teracoffee.order.dto.OrderEventPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tools.jackson.databind.ObjectMapper;

@Service
@RequiredArgsConstructor
public class OrderOutboxSender {

    private final OrderOutboxRepository orderOutboxRepository;
    private final DataPlatformClient dataPlatformClient;
    private final ObjectMapper objectMapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void send(Long outboxId) {
        OrderOutbox outbox = orderOutboxRepository.findById(outboxId)
                .orElseThrow(() -> new IllegalStateException("주문 이벤트를 찾을 수 없습니다."));

        if (outbox.getStatus() == OutboxStatus.SENT) {
            return;
        }

        try {
            OrderEventPayload payload = objectMapper.readValue(outbox.getPayload(), OrderEventPayload.class);
            dataPlatformClient.send(payload);
            outbox.markSent();
        } catch (Exception e) {
            outbox.markFailed();
        }
    }
}
