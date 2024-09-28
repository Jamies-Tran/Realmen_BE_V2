package com.capstone.realmen.controller.api.admin.plans.daily;

import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.capstone.realmen.common.response.ListResponse;
import com.capstone.realmen.controller.api.admin.plans.daily.models.DailyPlanResponse;

@RequestMapping("/web/daily-plan")
public interface IDailyPlanAPI {
    @GetMapping
    ListResponse<DailyPlanResponse> findByDate(
        @RequestParam @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime date
    );

}
