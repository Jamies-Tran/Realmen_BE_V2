package com.capstone.realmen.controller.api.admin.shop.service.models;

import java.util.List;

import com.capstone.realmen.controller.api.admin.shop.service.displays.models.ServiceDisplayResponse;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record AdminShopServiceResponse(
    Long shopServiceId,
    Long shopCategoryId,
    String shopServiceName,
    Long shopServicePrice,
    String shopServiceThumbnail,
    List<ServiceDisplayResponse> serviceDisplays
) {
    
}
