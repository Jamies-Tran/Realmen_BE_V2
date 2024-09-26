package com.capstone.realmen.data.dto.plans.daily.service;

import java.util.ArrayList;
import java.util.List;

import com.capstone.realmen.data.dto.branch.service.BranchService;

import lombok.Builder;

@Builder
public record DailyPlanService(
        Long dailyPlanServiceId,
        Long dailyPlanId,
        Long weeklyPlanId,
        Long branchId,
        Long branchServiceId,
        String shopServiceName,
        Long branchServicePrice,
        Long shopServicePrice,
        String categoryCode,
        String categoryName,
        String serviceAssignmentCode,
        String serviceAssignmentName,
        Integer estimateDuration,
        String durationUnitCode,
        String durationUnitName,
        String shopServiceStatusCode,
        String shopServiceStatusName) {
    public List<DailyPlanService> of(List<Long> branchServiceIds) {
        return branchServiceIds.stream()
                .map(branchServiceId -> DailyPlanService.builder()
                        .branchServiceId(branchServiceId)
                        .build())
                .toList();
    }

    public static List<DailyPlanService> of(List<Long> dailyPlanIds, List<BranchService> branchServices) {
        List<DailyPlanService> dailyPlanServices = new ArrayList<>();
        for (Long dailyPlanId : dailyPlanIds) {
            for (BranchService brs : branchServices) {
                DailyPlanService dailyPlanService = DailyPlanService.builder()
                        .dailyPlanId(dailyPlanId)
                        .branchServiceId(brs.branchServiceId())
                        .estimateDuration(brs.estimateDuration())
                        .durationUnitCode(brs.durationUnitCode())
                        .durationUnitName(brs.durationUnitName())
                        .build();
                dailyPlanServices.add(dailyPlanService);
            }
        }
        return dailyPlanServices;
    }

    public static List<DailyPlanService> duplicate(List<DailyPlanService> dailyPlanServices, Long dailyPlanId) {
        return dailyPlanServices.stream()
                .map(dailyPlanSer -> DailyPlanService.builder()
                        .branchServiceId(dailyPlanSer.branchServiceId())
                        .dailyPlanId(dailyPlanId)
                        .build())
                .toList();
    }

}
