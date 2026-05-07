package com.teracoffee.order.service;

import com.teracoffee.event.outbox.service.OrderEventService;
import com.teracoffee.menu.entity.Menu;
import com.teracoffee.menu.repository.MenuRepository;
import com.teracoffee.order.dto.CreateOrderRequest;
import com.teracoffee.order.dto.CreateOrderResponse;
import com.teracoffee.order.entity.Order;
import com.teracoffee.order.repository.OrderRepository;
import com.teracoffee.point.entity.PointHistory;
import com.teracoffee.point.entity.PointWallet;
import com.teracoffee.point.repository.PointHistoryRepository;
import com.teracoffee.point.repository.PointWalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final MenuRepository menuRepository;
    private final PointWalletRepository pointWalletRepository;
    private final PointHistoryRepository pointHistoryRepository;
    private final OrderRepository orderRepository;
    private final OrderEventService orderEventService;

    @Transactional
    public CreateOrderResponse createOrder(CreateOrderRequest request) {
        Menu menu = menuRepository.findByIdAndActiveTrue(request.menuId())
                .orElseThrow(() -> new IllegalArgumentException("주문 가능한 메뉴를 찾을 수 없습니다."));

        PointWallet wallet = pointWalletRepository.findByUserIdForUpdate(request.userId())
                .orElseThrow(() -> new IllegalStateException("포인트 지갑을 찾을 수 없습니다."));

        wallet.use(menu.getPrice());
        pointHistoryRepository.save(PointHistory.use(wallet.getUserId(), menu.getPrice(), wallet.getBalance()));

        Order order = orderRepository.save(Order.paid(request.userId(), menu));
        orderEventService.saveAndSendAfterCommit(order);

        return CreateOrderResponse.from(order);
    }

}
