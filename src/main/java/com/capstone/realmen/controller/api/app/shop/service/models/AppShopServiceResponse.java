package com.capstone.realmen.controller.api.app.shop.service.models;

import java.util.List;

import com.capstone.realmen.controller.api.admin.shop.service.displays.models.ServiceDisplayResponse;

import lombok.Builder;

@Builder
public record AppShopServiceResponse(
                Long branchServiceId,
                Long shopServiceId,
                Long branchId,
                Long dailyPlanId,
                String shopServiceName,
                Long branchServicePrice,
                String shopServiceThumbnail,
                Integer estimateDuration,
                String durationUnitCode,
                String durationUnitName,
                String shopCategoryCode,
                String shopCategoryName,
                List<ServiceDisplayResponse> serviceDisplays) {

}
