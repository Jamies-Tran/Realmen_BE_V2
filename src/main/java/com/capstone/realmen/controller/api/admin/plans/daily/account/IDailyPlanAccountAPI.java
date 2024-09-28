package com.capstone.realmen.controller.api.admin.plans.daily.account;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.capstone.realmen.common.response.ListResponse;
import com.capstone.realmen.controller.api.admin.plans.daily.models.DailyPlanAccountResponse;

@RequestMapping("/web/daily-plan-account")
public interface IDailyPlanAccountAPI {
    @GetMapping("/{dailyPlanId}")
    ListResponse<DailyPlanAccountResponse> findAll(
        @PathVariable Long dailyPlanId,
        @RequestParam(required = false, value = "staffName", defaultValue = "") String staffName,
        @RequestParam(required = false, value = "serviceAssignmentCodes", defaultValue = "") List<String> serviceAssignmentCodes
    );
}
