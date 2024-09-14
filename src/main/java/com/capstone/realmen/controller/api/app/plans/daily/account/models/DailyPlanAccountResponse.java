package com.capstone.realmen.controller.api.app.plans.daily.account.models;

import java.util.List;

import com.capstone.realmen.data.dto.plans.daily.DailyPlanShiftHour;

import lombok.Builder;

@Builder
public record DailyPlanAccountResponse(
        Long dailyPlanAccountId,
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
        String shiftName,
        List<DailyPlanShiftHour> workingSlots) {

}
