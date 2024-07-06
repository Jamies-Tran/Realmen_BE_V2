package com.capstone.realmen.data.dto.plans.daily;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

import com.capstone.realmen.data.dto.plans.daily.account.DailyPlanAccount;
import com.capstone.realmen.data.dto.plans.daily.service.DailyPlanService;

@Builder
public record DailyPlan(
        Long weeklyPlanId,
        Long dailyPlanId,
        LocalDateTime date,
        List<DailyPlanAccount> dailyPlanAccounts,
        List<DailyPlanService> dailyPlanServices) {
    public List<Long> accountIds() {
        return dailyPlanAccounts.stream()
                .map(DailyPlanAccount::accountId)
                .toList();
    }

    public List<Long> shopServiceIds() {
        return dailyPlanServices.stream()
                .map(DailyPlanService::shopServiceId)
                .toList();
    }

}
