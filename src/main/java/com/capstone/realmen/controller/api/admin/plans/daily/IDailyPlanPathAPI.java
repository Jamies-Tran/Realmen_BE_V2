package com.capstone.realmen.controller.api.admin.plans.daily;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.capstone.realmen.common.response.ValueResponse;
import com.capstone.realmen.controller.api.admin.plans.daily.models.DailyPlanRequest;
import com.capstone.realmen.controller.api.admin.plans.daily.models.DailyPlanResponse;

@RequestMapping("/web/daily-plan/{dailyPlanId}")
public interface IDailyPlanPathAPI {
    @PutMapping
    @PreAuthorize("hasRole('ROLE_BRANCHMANAGER')")
    ValueResponse<DailyPlanResponse> update(@PathVariable Long dailyPlanId, @RequestBody DailyPlanRequest request);

    @GetMapping
    @PreAuthorize("hasRole('ROLE_BRANCHMANAGER')")
    ValueResponse<DailyPlanResponse> findById(@PathVariable Long dailyPlanId);
}
