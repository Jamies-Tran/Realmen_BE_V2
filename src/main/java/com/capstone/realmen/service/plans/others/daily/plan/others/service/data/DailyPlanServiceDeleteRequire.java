package com.capstone.realmen.service.plans.others.daily.plan.others.service.data;

import lombok.Builder;

@Builder
public record DailyPlanServiceDeleteRequire(Long dailyPlanId) {
    public static DailyPlanServiceDeleteRequire of(Long dailyPlanId) {
        return DailyPlanServiceDeleteRequire.builder()
            .dailyPlanId(dailyPlanId)
            .build();
    }
    
}
