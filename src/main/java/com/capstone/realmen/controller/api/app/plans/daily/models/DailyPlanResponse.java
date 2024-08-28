package com.capstone.realmen.controller.api.app.plans.daily.models;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;

@Builder
public record DailyPlanResponse(
                Long weeklyPlanId,
                Long dailyPlanId,
                LocalDateTime date,
                String dailyPlanStatusCode,
                String dailyPlanStatusName,
                List<DailyPlanAccountResponse> dailyPlanAccounts,
                List<DailyPlanServiceResponse> dailyPlanServices) {

}
