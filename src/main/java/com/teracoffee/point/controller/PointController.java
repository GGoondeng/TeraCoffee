package com.teracoffee.point.controller;

import com.teracoffee.point.dto.ChargePointRequest;
import com.teracoffee.point.dto.ChargePointResponse;
import com.teracoffee.point.service.PointService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/points")
public class PointController {

    private final PointService pointService;

    @PostMapping("/charges")
    public ChargePointResponse charge(@Valid @RequestBody ChargePointRequest request) {
        return pointService.charge(request);
    }
}
