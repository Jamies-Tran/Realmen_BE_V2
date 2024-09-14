package com.capstone.realmen.controller.api.admin.shop.service.models;

import java.util.List;

import com.capstone.realmen.controller.api.admin.shop.service.displays.models.ServiceDisplayResponse;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record AdminShopServiceResponse(
        Long shopServiceId,
        Long branchId,
        String shopServiceName,
        Long shopServicePrice,
        Long branchServicePrice,
        String shopServiceThumbnail,
        Integer estimateDuration,
        String durationUnitCode,
        String durationUnitName,
        Long shopCategoryId,
        String shopCategoryCode,
        String shopCategoryName,
        String serviceAssignmentCode,
        String serviceAssignmentName,
        List<ServiceDisplayResponse> serviceDisplays) {

}
