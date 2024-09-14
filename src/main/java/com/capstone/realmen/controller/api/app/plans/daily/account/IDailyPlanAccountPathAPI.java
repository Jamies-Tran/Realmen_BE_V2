package com.capstone.realmen.controller.api.app.plans.daily.account;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.capstone.realmen.common.response.ValueResponse;
import com.capstone.realmen.controller.api.app.plans.daily.account.models.DailyPlanAccountResponse;

@RequestMapping("/mobile/daily-plan/account/{dailyPlanAccountId}")
public interface IDailyPlanAccountPathAPI {
    @GetMapping
    ValueResponse<DailyPlanAccountResponse> findById(Long dailyPlanAccountId);
}
