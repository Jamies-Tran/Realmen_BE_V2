package com.capstone.realmen.service.plans.others.daily.plan.data;

import java.util.List;

import com.capstone.realmen.controller.api.admin.plans.daily.models.DailyPlanRequest;

import lombok.Builder;

@Builder
public record DailyPlanUpdateRequire(
        Long dailyPlanId,
        List<Long> serviceIds,
        List<DailyPlanStaffUpdate> dailyPlanStaffUpdates) {
    public static DailyPlanUpdateRequire of(Long dailyPlanId, List<Long> serviceIds,
            List<DailyPlanStaffUpdate> dailyPlanStaffUpdates) {
        return DailyPlanUpdateRequire.builder()
                .dailyPlanId(dailyPlanId)
                .serviceIds(serviceIds)
                .dailyPlanStaffUpdates(dailyPlanStaffUpdates)
                .build();
    }

    public static DailyPlanUpdateRequire of(Long dailyPlanId, DailyPlanRequest request) {
        return DailyPlanUpdateRequire.builder()
                .dailyPlanId(dailyPlanId)
                .serviceIds(request.serviceIds())
                .dailyPlanStaffUpdates(DailyPlanStaffUpdate.of(request.staffs()))
                .build();
    }

}
