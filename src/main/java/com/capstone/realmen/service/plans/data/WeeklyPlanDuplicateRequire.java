package com.capstone.realmen.service.plans.data;



import com.capstone.realmen.common.enums.EDuplicateType;
import com.capstone.realmen.controller.api.admin.plans.models.AdminDuplicatePlanRequest;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record WeeklyPlanDuplicateRequire(Long weeklyPlanId, EDuplicateType duplicateType) {
    public static WeeklyPlanDuplicateRequire of(AdminDuplicatePlanRequest request) {
        return WeeklyPlanDuplicateRequire.builder()
            .weeklyPlanId(request.weeklyPlanId())
            .build();
    }

    public WeeklyPlanDuplicateRequire nextWeek() {
        return this.withDuplicateType(EDuplicateType.TO_NEXT_WEEK);
    }

    public WeeklyPlanDuplicateRequire present() {
        return this.withDuplicateType(EDuplicateType.TO_PRESENT);
    }
}
