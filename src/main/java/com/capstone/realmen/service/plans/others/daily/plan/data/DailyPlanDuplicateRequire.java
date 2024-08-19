package com.capstone.realmen.service.plans.others.daily.plan.data;


import lombok.Builder;

@Builder
public record DailyPlanDuplicateRequire(
        Long newWeeklyPlanId) {
     public static DailyPlanDuplicateRequire of(Long oldWeeklyPlanId, Long newWeeklyPlan) {
        return DailyPlanDuplicateRequire.builder()
                .newWeeklyPlanId(newWeeklyPlan)
                .build();
     }           

}
