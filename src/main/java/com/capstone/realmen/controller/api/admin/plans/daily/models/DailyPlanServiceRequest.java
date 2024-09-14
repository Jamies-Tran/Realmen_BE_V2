package com.capstone.realmen.controller.api.admin.plans.daily.models;

import lombok.Builder;

@Builder
public record DailyPlanServiceRequest(
    Long serviceId,
    String assignmentTypeCode
) {
    
}
