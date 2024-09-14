package com.capstone.realmen.controller.api.app.plans.daily.models;

import java.time.LocalDate;
import java.util.List;

import com.capstone.realmen.controller.api.app.plans.daily.account.models.DailyPlanAccountResponse;
import com.capstone.realmen.controller.api.app.plans.daily.services.models.DailyPlanServiceResponse;

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
