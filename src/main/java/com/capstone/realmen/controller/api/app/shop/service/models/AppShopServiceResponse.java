package com.capstone.realmen.controller.api.app.shop.service.models;

import java.util.List;

import com.capstone.realmen.controller.api.admin.shop.service.displays.models.ServiceDisplayResponse;

import lombok.Builder;

@Builder
public record AppShopServiceResponse(
    Long shopServiceId,
        Long branchId,
        String shopServiceName,
        Long shopServicePrice,
        String shopServiceThumbnail,
        Long shopCategoryId,
        String shopCategoryCode,
        String shopCategoryName,
        List<ServiceDisplayResponse> serviceDisplays
) {
    
}
