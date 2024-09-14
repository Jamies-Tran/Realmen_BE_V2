package com.capstone.realmen.service.plans.data;

import java.time.LocalDate;
import com.capstone.realmen.controller.api.admin.plans.models.AdminWeeklyPlanRequest;

import lombok.Builder;

@Builder
public record WeeklyPlanCreateRequire(
    LocalDate pickUpDate
) {
    public static WeeklyPlanCreateRequire of(AdminWeeklyPlanRequest request) {
        return WeeklyPlanCreateRequire.builder()
            .pickUpDate(request.pickupDate())
            .build();
    }
}
