package com.capstone.realmen.service.plans.others.daily.plan.data;

import lombok.Builder;

@Builder
public record DailyPlanActiveRequire(
    Long weeklyPlanId
) {
    public static DailyPlanActiveRequire of(Long weeklyPlanId) {
        return DailyPlanActiveRequire.builder()
            .weeklyPlanId(weeklyPlanId)
            .build();
    }
}
