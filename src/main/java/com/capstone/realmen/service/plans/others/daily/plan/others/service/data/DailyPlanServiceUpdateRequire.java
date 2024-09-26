package com.capstone.realmen.service.plans.others.daily.plan.others.service.data;

import java.util.List;

import com.capstone.realmen.service.plans.others.daily.plan.data.DailyPlanServiceUpdate;

import lombok.Builder;

@Builder
public record DailyPlanServiceUpdateRequire(
    Long dailyPlanId,
    List<DailyPlanServiceUpdate> services
) {
    public static DailyPlanServiceUpdateRequire of(Long dailyPlanId, List<DailyPlanServiceUpdate> services) {
        return DailyPlanServiceUpdateRequire.builder()
            .dailyPlanId(dailyPlanId)
            .services(services)
            .build();
    }
}
