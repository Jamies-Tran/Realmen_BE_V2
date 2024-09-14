package com.capstone.realmen.controller.api.app.plans.daily.services;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.capstone.realmen.common.response.PageImplResponse;
import com.capstone.realmen.controller.api.app.plans.daily.services.models.DailyPlanServiceResponse;

import jakarta.validation.constraints.Min;

@RequestMapping("/mobile/daily-plan/service")
public interface IDailyPlanServiceAPI {
    @GetMapping
    PageImplResponse<DailyPlanServiceResponse> findAll(
            @RequestParam(required = false, value = "branchId", defaultValue = "") Long branchId,
            @RequestParam(required = false, value = "dailyPlanId", defaultValue = "") Long dailyPlanId,
            @RequestParam(required = false, value = "serviceAssignmentCodes", defaultValue = "") List<String> serviceAssignmentCodes,
            @RequestParam(required = false, value = "current", defaultValue = "1") @Min(1) Integer current,
            @RequestParam(required = false, value = "pageSize", defaultValue = "20") Integer pageSize);
}
