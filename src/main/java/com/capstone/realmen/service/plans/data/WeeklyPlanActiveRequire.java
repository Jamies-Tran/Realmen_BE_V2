package com.capstone.realmen.service.plans.data;

import lombok.Builder;

@Builder
public record WeeklyPlanActiveRequire(Long weeklyPlanId) {
    public static WeeklyPlanActiveRequire of(Long weeklyPlanId) {
        return WeeklyPlanActiveRequire.builder()
            .weeklyPlanId(weeklyPlanId)
            .build();
    }
    
}
