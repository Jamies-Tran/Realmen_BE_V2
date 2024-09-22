package com.capstone.realmen.service.plans.others.daily.plan.others.service.data;

import java.util.List;
import java.util.Objects;

import lombok.Builder;

@Builder
public record DailyPlanServiceSearchCriteria(
        Long dailyPlanId,
        Long branchId,
        List<Long> dailyPlanServiceIds,
        List<String> serviceAssignmentCodes,
        List<Long> serviceIds) {
    public static DailyPlanServiceSearchCriteria ofDailyPlanServiceIds(List<Long> dailyPlanServiceIds, Long branchId, Long dailyPlanId) {
        return DailyPlanServiceSearchCriteria.builder()
                .dailyPlanServiceIds(dailyPlanServiceIds)
                .branchId(branchId)
                .dailyPlanId(dailyPlanId)
                .build();
    }

    public static DailyPlanServiceSearchCriteria of(List<String> serviceAssignmentCodes, Long branchId, Long dailyPlanId) {
        return DailyPlanServiceSearchCriteria.builder()
                .serviceAssignmentCodes(serviceAssignmentCodes)
                .branchId(branchId)
                .dailyPlanId(dailyPlanId)
                .build();
    }

    public Boolean hasBranchIdEmpty() {
        return Objects.isNull(branchId);
    }

    public Boolean hasServiceIdEmpty() {
        return Objects.isNull(serviceIds) || serviceIds.isEmpty();
    }

    public Boolean hasDailyPlanServiceIdsEmpty() {
        return Objects.isNull(dailyPlanServiceIds) || dailyPlanServiceIds.isEmpty();
    }

    public Boolean hasDailyPlanIdEmpty() {
        return Objects.isNull(dailyPlanId);
    }

    public Boolean hasServiceAssignmentCodeEmpty() {
        return Objects.isNull(serviceAssignmentCodes) || serviceAssignmentCodes.isEmpty();
    }
}
