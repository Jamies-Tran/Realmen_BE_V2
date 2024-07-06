package com.capstone.realmen.service.plans.others.daily.plan.others.account.data;

import java.util.List;

import lombok.Builder;

@Builder
public record DailyPlanAccountCreateRequire(
    List<Long> dailyPlanIds,
    List<Long> accountIds
) {
}
