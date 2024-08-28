package com.capstone.realmen.controller.api.app.plans.daily;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.capstone.realmen.common.response.ValueResponse;
import com.capstone.realmen.controller.api.app.plans.daily.models.DailyPlanResponse;

@RequestMapping("/app/daily-plan/{dailyPlanId}")
public interface IDailyPlanPathAPI {
    @GetMapping
    ValueResponse<DailyPlanResponse> findById(@PathVariable Long dailyPlanId);
}
