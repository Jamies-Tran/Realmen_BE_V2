package com.capstone.realmen.service.plans.others.daily.plan.data;

import java.util.List;

import com.capstone.realmen.controller.api.admin.plans.daily.models.DailyPlanRequest;
import lombok.Builder;

@Builder
public record DailyPlanUpdateRequire(
        Long dailyPlanId,
        List<DailyPlanServiceUpdate> serviceUpdates,
        List<DailyPlanStaffUpdate> staffUpdates) {
    public static DailyPlanUpdateRequire of(Long dailyPlanId, List<DailyPlanServiceUpdate> serviceUpdates,
            List<DailyPlanStaffUpdate> staffUpdates) {
        return DailyPlanUpdateRequire.builder()
                .dailyPlanId(dailyPlanId)
                .serviceUpdates(serviceUpdates)
                .staffUpdates(staffUpdates)
                .build();
    }

    public static DailyPlanUpdateRequire of(Long dailyPlanId, DailyPlanRequest request) {
        return DailyPlanUpdateRequire.builder()
                .dailyPlanId(dailyPlanId)
                .staffUpdates(DailyPlanStaffUpdate.of(request.staffs()))
                .serviceUpdates(DailyPlanServiceUpdate.of(request.services()))
                .build();
    }

    public List<Long> accountIds() {
        return staffUpdates.stream()
                .map(DailyPlanStaffUpdate::accountId)
                .toList();
    }

}
