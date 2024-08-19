package com.capstone.realmen.data.dto.plans.weekly;

import java.util.List;

import com.capstone.realmen.common.enums.EWeeklyPlanStatus;
import com.capstone.realmen.data.dto.plans.daily.DailyPlan;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record WeeklyPlan(
    Long weeklyPlanId,
    Long branchId,
    String weeklyPlanStatusCode,
    String weeklyPlanStatusName,
    List<DailyPlan> dailyPlans
) {
    public static WeeklyPlan duplicate(WeeklyPlan weeklyPlan) {
        return WeeklyPlan.builder()
            .branchId(weeklyPlan.branchId())
            .weeklyPlanStatusCode(EWeeklyPlanStatus.DRAFT.getCode())
            .weeklyPlanStatusName(EWeeklyPlanStatus.DRAFT.getName())
            .build();
    }
}
