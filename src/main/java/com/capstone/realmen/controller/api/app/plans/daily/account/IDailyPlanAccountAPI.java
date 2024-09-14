package com.capstone.realmen.controller.api.app.plans.daily.account;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.capstone.realmen.common.response.PageImplResponse;
import com.capstone.realmen.controller.api.app.plans.daily.account.models.DailyPlanAccountResponse;

import jakarta.validation.constraints.Min;

@RequestMapping("/mobile/daily-plan/account")
public interface IDailyPlanAccountAPI {
    @GetMapping
    PageImplResponse<DailyPlanAccountResponse> findAll(
            @RequestParam(required = false, value = "dailyPlanId", defaultValue = "") Long dailyPlanId,
            @RequestParam(required = false, value = "professionalTypeCodes", defaultValue = "") List<String> professionalTypeCodes,
            @RequestParam(required = false, value = "current", defaultValue = "1") @Min(1) Integer current,
            @RequestParam(required = false, value = "pageSize", defaultValue = "25") Integer pageSize);
}
