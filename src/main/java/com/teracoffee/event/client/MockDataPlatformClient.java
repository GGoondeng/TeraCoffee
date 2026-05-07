package com.teracoffee.event.client;

import com.teracoffee.order.dto.OrderEventPayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MockDataPlatformClient implements DataPlatformClient {

    @Override
    public void send(OrderEventPayload payload) {
        log.info("주문 이벤트를 전송합니다. userId={}, menuId={}, paymentAmount={}",
                payload.userId(),
                payload.menuId(),
                payload.paymentAmount()
        );
    }
}
