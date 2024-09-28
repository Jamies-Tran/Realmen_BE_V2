package com.capstone.realmen.service.plans.others.daily.plan.data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record DailyPlanSearchCriteria(
        List<LocalDate> timeRange,
        List<String> statusCodes,
        LocalDate date,
        Long weeklyPlanId,
        Long branchId,
        Long accountId,
        Long serviceId) {
    public static DailyPlanSearchCriteria of(Long weeklyPlanId) {
        return DailyPlanSearchCriteria.builder()
                .weeklyPlanId(weeklyPlanId)
                .build();
    }

    public static DailyPlanSearchCriteria of(
            List<LocalDateTime> timeRange,
            Long branchId,
            Long accountId,
            Long serviceId) {
        return DailyPlanSearchCriteria.builder()
                .timeRange(timeRange.stream().map(LocalDateTime::toLocalDate).toList())
                .branchId(branchId)
                .accountId(accountId)
                .serviceId(serviceId)
                .build();
    }

    public static DailyPlanSearchCriteria of(List<LocalDateTime> timeRange) {
        return DailyPlanSearchCriteria.builder()
                .timeRange(timeRange.stream().map(LocalDateTime::toLocalDate).toList())
                .build();
    }

    public LocalDate timeFrom() {
        if (Objects.isNull(timeRange) || timeRange.isEmpty()) {
            return null;
        }
        return timeRange.get(0);
    }

    public LocalDate timeTo() {
        if (Objects.isNull(timeRange) || timeRange.isEmpty()) {
            return null;
        } else if (timeRange.size() == 1) {
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

    public Boolean hasDateEmpty() {
        return Objects.isNull(date);
    }

    public Boolean hasAccountIdEmpty() {
        return Objects.isNull(accountId);
    }

    public Boolean hasServiceIdEmpty() {
        return Objects.isNull(serviceId);
    }

    public Boolean hasBranchIdEmpty() {
        return Objects.isNull(branchId);
    }

    public Boolean hasStatusCodesEmpty() {
        return Objects.isNull(statusCodes) || statusCodes.isEmpty();
    }
}
