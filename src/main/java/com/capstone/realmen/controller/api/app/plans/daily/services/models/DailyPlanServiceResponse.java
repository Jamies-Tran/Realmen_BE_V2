package com.capstone.realmen.controller.api.app.plans.daily.services.models;

import lombok.Builder;

@Builder
public record DailyPlanServiceResponse(
                Long dailyPlanServiceId,
                Long dailyPlanId,
                Long weeklyPlanId,
                Long branchId,
                Long shopServiceId,
                String shopServiceName,
                Long branchServicePrice,
                String serviceAssignmentCode,
                String serviceAssignmentName,
                String categoryCode,
                String categoryName,
                Integer estimateDuration,
                String durationUnitCode,
                String durationUnitName) {

}
