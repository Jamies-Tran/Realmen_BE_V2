package com.capstone.realmen.controller.api.admin.plans.daily.models;

import lombok.Builder;

@Builder
public record DailyPlanServiceResponse(
        Long dailyPlanServiceId,
        Long dailyPlanId,
        Long weeklyPlanId,
        Long branchId,
        Long shopServiceId,
        Long branchServiceId,
        String shopServiceName,
        Long branchServicePrice,
        Long shopServicePrice,
        String categoryCode,
        String categoryName,
        Integer estimateDuration,
        String serviceAssignmentCode,
        String durationUnitCode,
        String durationUnitName) {

}
