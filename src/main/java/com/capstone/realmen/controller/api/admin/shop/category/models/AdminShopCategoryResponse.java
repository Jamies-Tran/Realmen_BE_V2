package com.capstone.realmen.controller.api.admin.shop.category.models;

import java.util.List;

import com.capstone.realmen.controller.api.admin.shop.service.models.AdminShopServiceResponse;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record AdminShopCategoryResponse(
        Long shopCategoryId,
        String shopCategoryCode,
        String shopCategoryName,
        String serviceAssignmentCode,
        String serviceAssignmentName,
        List<AdminShopServiceResponse> shopServices) {

}
