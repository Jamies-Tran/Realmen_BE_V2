package com.capstone.realmen.service.plans.others.daily.plan.data;

import java.util.List;

import com.capstone.realmen.controller.api.admin.plans.daily.models.DailyPlanAccountRequest;
import lombok.Builder;

@Builder
public record DailyPlanStaffUpdate(
        Long accountId,
        String serviceAssignmentCode,
        String shiftCode,
        String shiftName) {
    public static List<DailyPlanStaffUpdate> of(List<DailyPlanAccountRequest> request) {
        return request.stream()
                .map(account -> DailyPlanStaffUpdate.builder()
                        .accountId(account.accountId())
                        .serviceAssignmentCode(account.serviceAssignmentCode())
                        .shiftCode(account.shiftCode())
                        .shiftName(account.shiftName())
                        .build())
                .toList();
    }
}
