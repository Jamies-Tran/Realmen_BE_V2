package com.capstone.realmen.data.dto.plans.weekly;

import java.util.List;

import com.capstone.realmen.data.dto.plans.daily.DailyPlan;

import lombok.Builder;

@Builder
public record WeeklyPlan(
    Long weeklyPlanId,
    Long branchId,
    List<DailyPlan> dailyPlans
) {
    
}
