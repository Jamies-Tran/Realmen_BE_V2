package com.capstone.realmen.service.plans.others.daily.plan.others.account.data;

import lombok.Builder;
import java.util.List;

@Builder
public record DailyPlanAccountSearchByField(
    Long dailyPlanId, 
    Long dailyPlanAccountId,
    List<Long> dailyPlanIds) {

    public static DailyPlanAccountSearchByField ofDailyPlanAccountId(Long dailyPlanAccountId) {
        return DailyPlanAccountSearchByField.builder()
            .dailyPlanAccountId(dailyPlanAccountId)
            .build();
    }
    
    public static DailyPlanAccountSearchByField of(Long dailyPlanId) {
        return DailyPlanAccountSearchByField.builder()
                .dailyPlanId(dailyPlanId)
                .build();
    }

    public static DailyPlanAccountSearchByField of(List<Long> dailyPlanIds) {
        return DailyPlanAccountSearchByField.builder()
                .dailyPlanIds(dailyPlanIds)
                .build();
    }
}
