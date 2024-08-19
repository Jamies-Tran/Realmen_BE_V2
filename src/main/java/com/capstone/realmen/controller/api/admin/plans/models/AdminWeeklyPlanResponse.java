package com.capstone.realmen.controller.api.admin.plans.models;

import java.util.List;

import com.capstone.realmen.controller.api.admin.plans.daily.models.DailyPlanResponse;

import lombok.Builder;

@Builder
public record AdminWeeklyPlanResponse(
    List<DailyPlanResponse> dailyPlans
) {
    
}
