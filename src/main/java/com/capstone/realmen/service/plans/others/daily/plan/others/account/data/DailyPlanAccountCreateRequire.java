package com.capstone.realmen.service.plans.others.daily.plan.others.account.data;

import java.util.List;

import com.capstone.realmen.data.dto.account.Account;
import com.capstone.realmen.data.dto.plans.daily.account.DailyPlanAccount;

import lombok.Builder;

@Builder
public record DailyPlanAccountCreateRequire(
        List<DailyPlanAccount> dailyPlanAccounts,
        List<Long> dailyPlanIds,
        List<Account> accounts) {
}
