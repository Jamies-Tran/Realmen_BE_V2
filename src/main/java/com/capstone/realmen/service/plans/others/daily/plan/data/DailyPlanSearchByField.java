package com.capstone.realmen.service.plans.others.daily.plan.data;

import java.util.List;

import org.springframework.util.StringUtils;

import lombok.Builder;

@Builder
public record DailyPlanSearchByField(
        Long dailyPlanId,
        List<Long> weeklyPlanIds,
        String status) {
    public static DailyPlanSearchByField of(Long dailyPlanId) {
        return DailyPlanSearchByField.builder()
                .dailyPlanId(dailyPlanId)
                .build();
    }

    public static DailyPlanSearchByField ofWeeklyPlanId(Long weeklyPlanId) {
        return DailyPlanSearchByField.builder()
                .weeklyPlanIds(List.of(weeklyPlanId))
                .build();
    }

    public static DailyPlanSearchByField of(List<Long> weeklyPlanIds, String status) {
        return DailyPlanSearchByField.builder()
                .weeklyPlanIds(weeklyPlanIds)
                .status(status)
                .build();
    }

    public Boolean hasStatusEmpty() {
        return !StringUtils.hasText(status);
    }
}
