package com.capstone.realmen.controller.api.admin.plans.daily.models;

import java.time.LocalDate;
import java.util.List;
import lombok.Builder;

@Builder
public record DailyPlanResponse(
        Long weeklyPlanId,
        Long dailyPlanId,
        LocalDate date,
        String dayInWeekCode,
        String dayInWeekName,
        String dailyPlanStatusCode,
        String dailyPlanStatusName,
        List<DailyPlanAccountResponse> dailyPlanAccounts,
        List<DailyPlanServiceResponse> dailyPlanServices) {

}
