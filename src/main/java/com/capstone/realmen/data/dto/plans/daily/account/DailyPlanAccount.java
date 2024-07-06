package com.capstone.realmen.data.dto.plans.daily.account;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;

@Builder
public record DailyPlanAccount(
        Long dailyPlanAccoutId,
        Long dailyPlanId,
        Long accountId) {
    public static List<DailyPlanAccount> of(List<Long> accountIds) {
        return accountIds.stream()
                .map(accountId -> DailyPlanAccount.builder()
                        .accountId(accountId).build())
                .toList();
    }

    public static List<DailyPlanAccount> of(List<Long> dailyPlanIds, List<Long> accountIds) {
        List<DailyPlanAccount> dailyPlanAccounts = new ArrayList<>();
        for (Long dailyPlanId : dailyPlanIds) {
            for (Long accountId : accountIds) {
                DailyPlanAccount dailyPlanAccount = DailyPlanAccount.builder()
                        .dailyPlanId(dailyPlanId)
                        .accountId(accountId)
                        .build();
                dailyPlanAccounts.add(dailyPlanAccount);
            }
        }
        return dailyPlanAccounts;
    }
}
