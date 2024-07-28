package com.capstone.realmen.service.plans.others.daily.plan.data;

import com.capstone.realmen.common.enums.EDuplicateType;

import lombok.Builder;

@Builder
public record DailyPlanDuplicateRequire(
        Long weeklyPlanId,
        EDuplicateType duplicateType) {
     public static DailyPlanDuplicateRequire of(Long weeklyPlanId, EDuplicateType duplicateType) {
        return DailyPlanDuplicateRequire.builder()
                .weeklyPlanId(weeklyPlanId)
                .duplicateType(duplicateType)
                .build();
     }           

}
