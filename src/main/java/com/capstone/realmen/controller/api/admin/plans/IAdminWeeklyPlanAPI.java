package com.capstone.realmen.controller.api.admin.plans;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.capstone.realmen.controller.api.admin.plans.models.AdminDuplicatePlanRequest;
import com.capstone.realmen.controller.api.admin.plans.models.AdminWeeklyPlanRequest;

import jakarta.validation.Valid;

@RequestMapping("/web/weekly-plan")
public interface IAdminWeeklyPlanAPI {
    @PostMapping
    void save(@RequestBody @Valid AdminWeeklyPlanRequest weeklyPlanRequest);

    @PostMapping("/duplicate/to-next-week")
    void duplicatePlanToNextWeek(@RequestBody @Valid AdminDuplicatePlanRequest duplicatePlanRequest);

    @PostMapping("/duplicate/to-present")
    void duplicatePlanToPresent(@RequestBody @Valid AdminDuplicatePlanRequest duplicatePlanRequest);
}
