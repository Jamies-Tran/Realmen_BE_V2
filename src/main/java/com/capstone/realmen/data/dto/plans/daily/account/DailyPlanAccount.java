package com.capstone.realmen.data.dto.plans.daily.account;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;

@Builder
public record DailyPlanAccount(
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
        String accountStatusName) {
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

    public static List<DailyPlanAccount> duplicate(List<DailyPlanAccount> dailyPlanAccounts, Long newDailyPlanId) {
        return dailyPlanAccounts.stream()
                .map(dailyPlanAcc -> DailyPlanAccount.builder()
                        .accountId(dailyPlanAcc.accountId())
                        .dailyPlanId(newDailyPlanId)
                        .build())
                .toList();
    }
}
