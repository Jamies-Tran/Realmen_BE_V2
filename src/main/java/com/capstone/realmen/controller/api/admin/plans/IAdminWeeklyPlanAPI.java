package com.capstone.realmen.controller.api.admin.plans;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.capstone.realmen.common.response.PageImplResponse;
import com.capstone.realmen.controller.api.admin.plans.models.AdminWeeklyPlanRequest;
import com.capstone.realmen.controller.api.admin.plans.models.AdminWeeklyPlanResponse;

import jakarta.validation.Valid;

@RequestMapping("/web/weekly-plan")
public interface IAdminWeeklyPlanAPI {
    @PostMapping
    void save(@RequestBody @Valid AdminWeeklyPlanRequest weeklyPlanRequest);

    @GetMapping
    PageImplResponse<AdminWeeklyPlanResponse> findAll(
            @RequestParam(required = false, value = "search", defaultValue = "") String search,
            @RequestParam(required = false, value = "timeRange", defaultValue = "") @DateTimeFormat(iso = ISO.DATE_TIME) List<LocalDateTime> timeRange,
            @RequestParam(required = false, value = "sorter", defaultValue = "createdBy_desc") String sorter,
            @RequestParam(required = false, value = "current", defaultValue = "1") Integer current,
            @RequestParam(required = false, value = "pageSize", defaultValue = "25") Integer pageSize);
}
