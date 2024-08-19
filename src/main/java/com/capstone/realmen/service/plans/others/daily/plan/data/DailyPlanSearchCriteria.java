package com.capstone.realmen.service.plans.others.daily.plan.data;

import java.util.Objects;

import lombok.Builder;

@Builder
public record DailyPlanSearchCriteria(
        Long weeklyPlanId) {
    public static DailyPlanSearchCriteria of(Long weeklyPlanId) {
        return DailyPlanSearchCriteria.builder()
                .weeklyPlanId(weeklyPlanId)
                .build();
    }

    public Boolean hasWeeklyPlanEmpty() {
        return Objects.isNull(weeklyPlanId);
    }

}
