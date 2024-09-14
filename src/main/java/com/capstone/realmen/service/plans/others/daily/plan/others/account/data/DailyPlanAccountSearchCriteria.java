package com.capstone.realmen.service.plans.others.daily.plan.others.account.data;

import java.util.List;
import java.util.Objects;

import lombok.Builder;

@Builder
public record DailyPlanAccountSearchCriteria(
    Long dailyPlanId,
    List<String> professinalTypeCodes
) {
    public static DailyPlanAccountSearchCriteria of(Long dailyPlanId, List<String> professionalTypeCodes) {
        return DailyPlanAccountSearchCriteria.builder()
            .dailyPlanId(dailyPlanId)
            .professinalTypeCodes(professionalTypeCodes)
            .build();
    }

    public Boolean hasDailyPlanIdEmpty() {
        return Objects.isNull(dailyPlanId);
    }

    public Boolean hasProfessionalTypeCodeEmpty() {
        return Objects.isNull(professinalTypeCodes) || professinalTypeCodes.isEmpty();
    }
}
