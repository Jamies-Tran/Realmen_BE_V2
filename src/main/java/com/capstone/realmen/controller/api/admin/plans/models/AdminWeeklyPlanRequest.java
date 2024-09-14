package com.capstone.realmen.controller.api.admin.plans.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import lombok.Builder;

@Builder
public record AdminWeeklyPlanRequest(
    LocalDateTime pickupDateReq,
    LocalDate pickupDate
) {
    public LocalDate pickupDate() {
        return Objects.requireNonNullElse(pickupDate, pickupDateReq.toLocalDate());
    }
}
