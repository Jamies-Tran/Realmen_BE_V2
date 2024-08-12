package com.capstone.realmen.controller.api.admin.plans.daily.models;

import lombok.Builder;

@Builder
public record DailyPlanServiceResponse(
        Long dailyPlanServiceId,
        Long dailyPlanId,
        Long shopServiceId,
        String shopServiceName,
        Long shopServicePrice,
        String categoryCode,
        String categoryName,
        String shopServiceStatusCode,
        String shopServiceStatusName) {

}
