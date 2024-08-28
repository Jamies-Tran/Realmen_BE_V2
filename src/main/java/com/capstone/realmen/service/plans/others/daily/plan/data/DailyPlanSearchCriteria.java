package com.capstone.realmen.service.plans.others.daily.plan.data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record DailyPlanSearchCriteria(
        List<LocalDateTime> timeRange,
        Long weeklyPlanId,
        Long accountId) {
    public static DailyPlanSearchCriteria of(Long weeklyPlanId) {
        return DailyPlanSearchCriteria.builder()
                .weeklyPlanId(weeklyPlanId)
                .build();
    }

    public static DailyPlanSearchCriteria of(List<LocalDateTime> timeRange, Long accountId) {
        return DailyPlanSearchCriteria.builder()
                .timeRange(timeRange)
                .accountId(accountId)
                .build();
    }

    public static DailyPlanSearchCriteria of(List<LocalDateTime> timeRange) {
        return DailyPlanSearchCriteria.builder()
                .timeRange(timeRange)
                .build();
    }

    public LocalDateTime timeFrom() {
        if(Objects.isNull(timeRange) || timeRange.isEmpty()) {
            return null;
        }
        return timeRange.get(0);
    }

    public LocalDateTime timeTo() {
        if(Objects.isNull(timeRange) || timeRange.isEmpty()) {
            return null;
        } else if(timeRange.size() == 1) {
            return timeRange.get(0).plusDays(7);
        }

        return timeRange.get(1);
    }

    public Boolean hasWeeklyPlanIdEmpty() {
        return Objects.isNull(weeklyPlanId);
    }

    public Boolean hasTimeRangeEmpty() {
        return Objects.isNull(timeRange) || timeRange.isEmpty();
    }

    public Boolean hasAccountIdEmpty() {
        return Objects.isNull(accountId);
    }
}
