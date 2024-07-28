package com.capstone.realmen.controller.api.admin.plans.models;

import java.time.LocalDateTime;

import lombok.Builder;

@Builder
public record AdminWeeklyPlanRequest(
    String dailyPlanCreateTypeCode,
    LocalDateTime pickupDate
) {
    
}
