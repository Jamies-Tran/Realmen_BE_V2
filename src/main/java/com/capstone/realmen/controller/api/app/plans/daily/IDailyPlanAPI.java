package com.capstone.realmen.controller.api.app.plans.daily;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.capstone.realmen.common.response.ListResponse;
import com.capstone.realmen.controller.api.app.plans.daily.models.DailyPlanResponse;

@RequestMapping("/mobile/daily-plan")
public interface IDailyPlanAPI {
    @GetMapping
    @PreAuthorize("hasAnyRole({'ROLE_CUSTOMER', 'ROLE_OPERATOR_STAFF'})")
    ListResponse<DailyPlanResponse> findAll(
            @RequestParam(required = false, value = "timeRange", defaultValue = "") @DateTimeFormat(iso = ISO.DATE_TIME) List<LocalDateTime> timeRange,
            @RequestParam(required = false, value = "branchId", defaultValue = "") Long branchId,
            @RequestParam(required = false, value = "serviceId", defaultValue = "") Long serviceId,
            @RequestParam(required = false, value = "accountId", defaultValue = "") Long accountId);

    @GetMapping("/staff")
    @PreAuthorize("hasRole('ROLE_OPERATOR_STAFF')")
    ListResponse<DailyPlanResponse> findAllForStaff(
            @RequestParam(required = false, value = "timeRange", defaultValue = "") @DateTimeFormat(iso = ISO.DATE_TIME) List<LocalDateTime> timeRange);
}
