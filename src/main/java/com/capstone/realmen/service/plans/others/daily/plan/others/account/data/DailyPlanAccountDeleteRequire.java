package com.capstone.realmen.service.plans.others.daily.plan.others.account.data;

import lombok.Builder;

@Builder
public record DailyPlanAccountDeleteRequire(Long dailyPlanId) {
    public static DailyPlanAccountDeleteRequire of(Long dailyPlanIds) {
        return DailyPlanAccountDeleteRequire.builder()
            .dailyPlanId(dailyPlanIds)
            .build();
    }

}
