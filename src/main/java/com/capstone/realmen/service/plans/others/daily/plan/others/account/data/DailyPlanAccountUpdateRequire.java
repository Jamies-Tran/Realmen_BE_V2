package com.capstone.realmen.service.plans.others.daily.plan.others.account.data;

import java.util.List;

import com.capstone.realmen.service.plans.others.daily.plan.data.DailyPlanStaffUpdate;

import lombok.Builder;

@Builder
public record DailyPlanAccountUpdateRequire(Long dailyPlanId, List<DailyPlanStaffUpdate> dailyPlanStaffUpdates) {
    public static DailyPlanAccountUpdateRequire of(Long dailyPlanId, List<DailyPlanStaffUpdate> dailyPlanStaffUpdates) {
        return DailyPlanAccountUpdateRequire.builder()
                .dailyPlanId(dailyPlanId)
                .dailyPlanStaffUpdates(dailyPlanStaffUpdates)
                .build();
    }

    public List<Long> staffIds() {
        return dailyPlanStaffUpdates.stream()
                .map(DailyPlanStaffUpdate::accountId)
                .toList();
    }
}
