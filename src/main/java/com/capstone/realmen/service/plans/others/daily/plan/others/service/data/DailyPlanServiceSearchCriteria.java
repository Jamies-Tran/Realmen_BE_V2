package com.capstone.realmen.service.plans.others.daily.plan.others.service.data;

import java.util.List;
import java.util.Objects;

import lombok.Builder;

@Builder
public record DailyPlanServiceSearchCriteria(
        Long dailyPlanServiceId,
        Long dailyPlanId,
        Long branchId,
        List<String> serviceAssignmentCodes,
        List<Long> serviceIds) {
    public static DailyPlanServiceSearchCriteria of(Long dailyPlanServiceId, Long branchId, List<Long> serviceIds) {
        return DailyPlanServiceSearchCriteria.builder()
                .dailyPlanServiceId(dailyPlanServiceId)
                .branchId(branchId)
                .serviceIds(serviceIds)
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

    public Boolean hasDailyPlanServiceIdEmpty() {
        return Objects.isNull(dailyPlanServiceId);
    }

    public Boolean hasDailyPlanIdEmpty() {
        return Objects.isNull(dailyPlanId);
    }

    public Boolean hasServiceAssignmentCodeEmpty() {
        return Objects.isNull(serviceAssignmentCodes) || serviceAssignmentCodes.isEmpty();
    }
}
