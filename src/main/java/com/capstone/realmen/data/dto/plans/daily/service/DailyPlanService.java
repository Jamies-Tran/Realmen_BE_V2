package com.capstone.realmen.data.dto.plans.daily.service;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;

@Builder
public record DailyPlanService(
        Long dailyPlanServiceId,
        Long dailyPlanId,
        Long shopServiceId,
        String shopServiceName,
        Long shopServicePrice,
        String categoryCode,
        String categoryName,
        String shopServiceStatusCode,
        String shopServiceStatusName) {
    public List<DailyPlanService> of(List<Long> shopServiceIds) {
        return shopServiceIds.stream()
                .map(shopServiceId -> DailyPlanService.builder()
                        .shopServiceId(shopServiceId)
                        .build())
                .toList();
    }

    public static List<DailyPlanService> of(List<Long> dailyPlanIds, List<Long> shopServiceIds) {
        List<DailyPlanService> dailyPlanServices = new ArrayList<>();
        for (Long dailyPlanId : dailyPlanIds) {
            for (Long shopServiceId : shopServiceIds) {
                DailyPlanService dailyPlanService = DailyPlanService.builder()
                        .dailyPlanId(dailyPlanId)
                        .shopServiceId(shopServiceId)
                        .build();
                dailyPlanServices.add(dailyPlanService);
            }
        }
        return dailyPlanServices;
    }

    public static List<DailyPlanService> duplicate(List<DailyPlanService> dailyPlanServices, Long dailyPlanId) {
        return dailyPlanServices.stream()
                .map(dailyPlanSer -> DailyPlanService.builder()
                        .shopServiceId(dailyPlanSer.shopServiceId())
                        .dailyPlanId(dailyPlanId)
                        .build())
                .toList();
    }

}
