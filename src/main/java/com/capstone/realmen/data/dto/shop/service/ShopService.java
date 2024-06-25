package com.capstone.realmen.data.dto.shop.service;

import java.util.List;

import com.capstone.realmen.data.dto.shop.service.display.ServiceDisplay;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record ShopService(
    Long shopServiceId,
    Long shopCategoryId,
    String shopCategoryCode,
    String shopCategoryName,
    String shopServiceName,
    Long shopServicePrice,
    String shopServiceThumbnail,
    List<ServiceDisplay> serviceDisplays
) {
    
}
