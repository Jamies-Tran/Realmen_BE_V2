package com.capstone.realmen.service.plans.data;

import lombok.Builder;

@Builder
public record WeeklyPlanSearchByField(
    Long weeklyPlanId
) {
    public static WeeklyPlanSearchByField of(Long weeklyPlanId) {
        return WeeklyPlanSearchByField.builder()
            .weeklyPlanId(weeklyPlanId)
            .build();
    }
    
}
