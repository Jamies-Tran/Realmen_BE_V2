package com.capstone.realmen.service.plans.others.daily.plan.data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import com.capstone.realmen.common.enums.EDailyPlanCreateType;
import com.capstone.realmen.data.dto.plans.daily.DailyPlan;
import lombok.Builder;

@Builder
public record DailyPlanCreateRequire(
        DailyPlan dailyPlan,
        Long weeklyPlanId,
        List<Long> accountIds,
        List<Long> serviceIds, 
        EDailyPlanCreateType dailyPlanCreateType,
        LocalDateTime pickUpDate) {
    public LocalDateTime pickUpDate() {
        return Objects.requireNonNullElse(pickUpDate, LocalDateTime.now());
    }
}
