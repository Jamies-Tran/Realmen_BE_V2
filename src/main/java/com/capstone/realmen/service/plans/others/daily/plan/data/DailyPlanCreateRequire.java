package com.capstone.realmen.service.plans.others.daily.plan.data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import lombok.Builder;

@Builder
public record DailyPlanCreateRequire(
        Long weeklyPlanId,
        List<Long> accountIds,
        List<Long> serviceIds, 
        LocalDateTime pickUpDate) {
    public LocalDateTime pickUpDate() {
        return Objects.requireNonNullElse(pickUpDate, LocalDateTime.now());
    }
}
