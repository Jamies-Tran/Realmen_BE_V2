package com.capstone.realmen.service.plans.data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import org.springframework.util.StringUtils;

import lombok.Builder;

@Builder
public record WeeklyPlanSearchCriteria(
    String search,
    List<LocalDateTime> timeRange
) {
    public static WeeklyPlanSearchCriteria of(String search, List<LocalDateTime> timeRange) {
        return WeeklyPlanSearchCriteria.builder()
            .search(search)
            .timeRange(timeRange)
            .build();
    }    

    public Boolean hasSearchEmpty() {
        return !StringUtils.hasText(search);
    }

    public Boolean hasTimeRangeEmpty() {
        return Objects.isNull(timeRange) || timeRange.isEmpty();
    }

    public LocalDateTime timeFrom() {
        if(!hasTimeRangeEmpty()) {
            return timeRange.get(0);
        }

        return null;
    }

    public LocalDateTime timeTo() {
        if(!hasTimeRangeEmpty()) {
            return timeRange.get(1);
        }

        return null;
    }
}
