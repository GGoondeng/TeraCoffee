package com.teracoffee.point.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ChargePointRequest(
        @NotBlank(message = "사용자 식별값은 필수입니다.")
        String userId,

        @NotNull(message = "충전 금액은 필수입니다.")
        @Positive(message = "충전 금액은 1 이상이어야 합니다.")
        Long amount
) {
}
