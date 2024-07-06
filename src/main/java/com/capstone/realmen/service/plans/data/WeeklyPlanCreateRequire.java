package com.capstone.realmen.service.plans.data;

import java.time.LocalDateTime;

import lombok.Builder;

@Builder
public record WeeklyPlanCreateRequire(
    Long branchId,
    LocalDateTime beginAt
) {
    
}
