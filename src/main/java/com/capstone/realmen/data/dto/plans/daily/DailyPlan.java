package com.capstone.realmen.data.dto.plans.daily;

import lombok.Builder;
import lombok.With;

import java.time.LocalDate;
import java.util.List;
import com.capstone.realmen.data.dto.plans.daily.account.DailyPlanAccount;
import com.capstone.realmen.data.dto.plans.daily.service.DailyPlanService;

@With
@Builder
public record DailyPlan(
        Long weeklyPlanId,
        Long dailyPlanId,
        LocalDate date,
        String dayInWeekCode,
        String dayInWeekName,
        String dailyPlanStatusCode,
        String dailyPlanStatusName,
        List<DailyPlanAccount> dailyPlanAccounts,
        List<DailyPlanService> dailyPlanServices) {
    public List<Long> accountIds() {
        return dailyPlanAccounts.stream()
                .map(DailyPlanAccount::accountId)
                .toList();
    }

    public List<Long> branchServiceIds() {
        return dailyPlanServices.stream()
                .map(DailyPlanService::branchServiceId)
                .toList();
    }

    public static DailyPlan duplicate(DailyPlan dailyPlan) {
        return DailyPlan.builder()
                .weeklyPlanId(dailyPlan.weeklyPlanId())
                .date(dailyPlan.date())
                .dailyPlanStatusCode(dailyPlan.dailyPlanStatusCode())
                .dailyPlanStatusName(dailyPlan.dailyPlanStatusName())
                .build();
    }
}
