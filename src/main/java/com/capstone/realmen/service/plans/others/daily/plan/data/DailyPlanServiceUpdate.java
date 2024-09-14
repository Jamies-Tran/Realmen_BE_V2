package com.capstone.realmen.service.plans.others.daily.plan.data;

import java.util.List;

import com.capstone.realmen.controller.api.admin.plans.daily.models.DailyPlanServiceRequest;

import lombok.Builder;

@Builder
public record DailyPlanServiceUpdate(
        Long serviceId,
        String assignmentTypeCode) {
    public static List<DailyPlanServiceUpdate> of(List<DailyPlanServiceRequest> requests) {
        return requests.stream()
                .map(service -> DailyPlanServiceUpdate.builder()
                        .serviceId(service.serviceId())
                        .assignmentTypeCode(service.assignmentTypeCode())
                        .build())
                .toList();
    }
}
