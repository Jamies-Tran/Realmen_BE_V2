package com.capstone.realmen.controller.api.admin.plans.daily.models;

import lombok.Builder;

@Builder
public record DailyPlanAccountResponse(
        Long dailyPlanAccoutId,
        Long dailyPlanId,
        Long accountId,
        String fullName,
        String phone,
        String genderCode,
        String genderName,
        String professionalTypeCode,
        String professionalTypeName,
        String thumbnail,
        String accountStatusCode,
        String accountStatusName,
        String shiftCode,
        String shiftName) {

}
