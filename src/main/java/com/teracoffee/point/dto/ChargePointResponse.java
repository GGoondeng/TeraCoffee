package com.teracoffee.point.dto;

import com.teracoffee.point.entity.PointWallet;

public record ChargePointResponse(
        String userId,
        long chargedAmount,
        long balance
) {

    public static ChargePointResponse of(PointWallet wallet, long chargedAmount) {
        return new ChargePointResponse(
                wallet.getUserId(),
                chargedAmount,
                wallet.getBalance()
        );
    }
}
