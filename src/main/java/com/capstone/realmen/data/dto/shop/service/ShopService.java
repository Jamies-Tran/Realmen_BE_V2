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
                Long branchId,
                String shopCategoryCode,
                String shopCategoryName,
                String shopServiceName,
                String serviceAssignmentCode,
                Long shopServicePrice,
                String shopServiceThumbnail,
                List<ServiceDisplay> serviceDisplays) {

}
