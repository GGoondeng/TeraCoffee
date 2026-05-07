package com.teracoffee.event.client;

import com.teracoffee.order.dto.OrderEventPayload;

public interface DataPlatformClient {

    void send(OrderEventPayload payload);
}
