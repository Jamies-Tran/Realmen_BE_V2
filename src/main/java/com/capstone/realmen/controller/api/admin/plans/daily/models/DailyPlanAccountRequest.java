package com.capstone.realmen.controller.api.admin.plans.daily.models;

import lombok.Builder;

@Builder
public record DailyPlanAccountRequest(
        Long accountId,
        String serviceAssignmentCode,
        String shiftCode,
        String shiftName) {

}
