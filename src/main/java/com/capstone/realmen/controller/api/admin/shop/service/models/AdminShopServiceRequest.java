package com.capstone.realmen.controller.api.admin.shop.service.models;

import java.util.List;

import com.capstone.realmen.controller.api.admin.shop.service.displays.models.ServiceDisplayRequest;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record AdminShopServiceRequest(
    Long shopCategoryId,
    String shopServiceName,
    Long shopServicePrice,
    String shopServiceThumbnail,
    List<ServiceDisplayRequest> serviceDisplays
) {
    
}
