package com.teracoffee.point.service;

import com.teracoffee.point.dto.ChargePointRequest;
import com.teracoffee.point.dto.ChargePointResponse;
import com.teracoffee.point.entity.PointHistory;
import com.teracoffee.point.entity.PointWallet;
import com.teracoffee.point.repository.PointHistoryRepository;
import com.teracoffee.point.repository.PointWalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;

@Service
@RequiredArgsConstructor
public class PointService {

    private final PointWalletRepository pointWalletRepository;
    private final PointHistoryRepository pointHistoryRepository;

    @Transactional
    public ChargePointResponse charge(ChargePointRequest request) {
        long amount = request.amount();

        pointWalletRepository.insertIfAbsent(request.userId());

        PointWallet wallet = pointWalletRepository.findByUserIdForUpdate(request.userId())
                .orElseThrow(() -> new IllegalStateException("포인트 지갑을 찾을 수 없습니다."));

        wallet.charge(amount);
        pointHistoryRepository.save(PointHistory.charge(wallet.getUserId(), amount, wallet.getBalance()));

        return ChargePointResponse.of(wallet, amount);
    }
}
