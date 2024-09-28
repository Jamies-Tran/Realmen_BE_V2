package com.capstone.realmen.controller.api.admin.plans.daily.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.capstone.realmen.common.response.ListResponse;
import com.capstone.realmen.controller.api.admin.plans.daily.models.DailyPlanServiceResponse;

@RequestMapping("/admin/daily-plan-service")
public interface IDailyPlanServiceAPI {
    @GetMapping("/{dailyPlanId}")
    ListResponse<DailyPlanServiceResponse> findByDailyPlanId(
        @PathVariable Long dailyPlanId,
        @RequestParam(required = false, value = "serviceName") String serviceName);
}
