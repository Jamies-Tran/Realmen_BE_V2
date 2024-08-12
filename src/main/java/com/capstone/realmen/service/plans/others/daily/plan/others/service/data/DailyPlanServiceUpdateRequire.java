package com.capstone.realmen.service.plans.others.daily.plan.others.service.data;

import java.util.List;

import lombok.Builder;

@Builder
public record DailyPlanServiceUpdateRequire(
    Long dailyPlanId,
    List<Long> serviceIds
) {
    public static DailyPlanServiceUpdateRequire of(Long dailyPlanId, List<Long> serviceIds) {
        return DailyPlanServiceUpdateRequire.builder()
            .dailyPlanId(dailyPlanId)
            .serviceIds(serviceIds)
            .build();
    }
}
