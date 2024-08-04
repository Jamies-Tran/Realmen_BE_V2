package com.capstone.realmen.service.plans.others.daily.plan.data;

import com.capstone.realmen.common.enums.EDuplicateType;

import lombok.Builder;

@Builder
public record DailyPlanDuplicateRequire(
        Long newWeeklyPlanId,
        Long oldWeeklyPlanId,
        EDuplicateType duplicateType) {
     public static DailyPlanDuplicateRequire of(Long oldWeeklyPlanId, Long newWeeklyPlan, EDuplicateType duplicateType) {
        return DailyPlanDuplicateRequire.builder()
                .oldWeeklyPlanId(oldWeeklyPlanId)
                .newWeeklyPlanId(newWeeklyPlan)
                .duplicateType(duplicateType)
                .build();
     }           

}
