package com.capstone.realmen.service.plans.others.daily.plan.others.service.data;

import lombok.Builder;
import java.util.List;

@Builder
public record DailyPlanServiceSearchByField(
        Long dailyPlanId,
        Long dailyPlanServiceId,
        List<Long> dailyPlanIds) {
    public static DailyPlanServiceSearchByField of(Long dailyPlanId) {
        return DailyPlanServiceSearchByField.builder()
                .dailyPlanId(dailyPlanId)
                .build();
    }

    public static DailyPlanServiceSearchByField of(List<Long> dailyPlanIds) {
        return DailyPlanServiceSearchByField.builder()
                .dailyPlanIds(dailyPlanIds)
                .build();
    }

    public static DailyPlanServiceSearchByField ofDailyPlanServiceId(Long dailyPlanServiceId) {
        return DailyPlanServiceSearchByField.builder()
                .dailyPlanServiceId(dailyPlanServiceId)
                .build();
    }
}
