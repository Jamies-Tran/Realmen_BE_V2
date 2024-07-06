package com.capstone.realmen.data.dto.plans.daily.service;

import java.util.List;

import lombok.Builder;

@Builder
public record DailyPlanService(
    Long dailyPlanServiceId,
    Long dailyPlanId,
    Long shopServiceId
) {
    public List<DailyPlanService> of(List<Long> shopServiceIds) {
        return shopServiceIds.stream()
            .map(shopServiceId -> DailyPlanService.builder()
                .shopServiceId(shopServiceId)
                .build())
            .toList();
    }
    
}
