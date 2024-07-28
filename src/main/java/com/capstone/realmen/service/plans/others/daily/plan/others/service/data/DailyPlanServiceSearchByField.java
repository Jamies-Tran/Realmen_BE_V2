package com.capstone.realmen.service.plans.others.daily.plan.others.service.data;

import lombok.Builder;
import java.util.List;

@Builder
public record DailyPlanServiceSearchByField(Long dailyPlanId, List<Long> dailyPlanIds) {
    public static DailyPlanServiceSearchByField of(Long dailyPlanId) {
        return DailyPlanServiceSearchByField.builder()
                .dailyPlanId(dailyPlanId)
                .build();
    }
}
