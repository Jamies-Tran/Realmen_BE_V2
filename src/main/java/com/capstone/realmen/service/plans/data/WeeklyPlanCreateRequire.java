package com.capstone.realmen.service.plans.data;

import java.time.LocalDateTime;
import com.capstone.realmen.controller.api.admin.plans.models.AdminWeeklyPlanRequest;

import lombok.Builder;

@Builder
public record WeeklyPlanCreateRequire(
    String dailyPlanCreateTypeCode,
    LocalDateTime pickUpDate
) {
    public static WeeklyPlanCreateRequire of(AdminWeeklyPlanRequest request) {
        return WeeklyPlanCreateRequire.builder()
            .dailyPlanCreateTypeCode(request.dailyPlanCreateTypeCode())
            .pickUpDate(request.pickupDate())
            .build();
    }
}
