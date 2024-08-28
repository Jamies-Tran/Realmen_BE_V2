package com.capstone.realmen.controller.api.admin.plans;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.capstone.realmen.common.response.ValueResponse;
import com.capstone.realmen.controller.api.admin.plans.models.AdminWeeklyPlanResponse;

@RequestMapping("/web/weekly-plan/{weeklyPlanId}")
public interface IAdminWeeklyPlanPathAPI {
    @PostMapping("/duplicate")
    void duplicateWeeklyPlan(@PathVariable Long weeklyPlanId);

    @PutMapping
    ValueResponse<AdminWeeklyPlanResponse> active(@PathVariable Long weeklyPlanId);

    @GetMapping
    ValueResponse<AdminWeeklyPlanResponse> findById(@PathVariable Long weeklyPlanId);
}
