package com.capstone.realmen.service.plans.data;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record WeeklyPlanDuplicateRequire(Long weeklyPlanId) {
    public static WeeklyPlanDuplicateRequire of(Long weeklyPlanId) {
        return WeeklyPlanDuplicateRequire.builder()
                .weeklyPlanId(weeklyPlanId)
                .build();
    }

}
