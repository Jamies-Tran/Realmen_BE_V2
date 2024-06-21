package com.capstone.realmen.controller.api.admin.shop.category.models;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record AdminShopCategoryResponse(
        Long shopCategoryId,
        String shopCategoryCode,
        String shopCategoryName,
        String serviceAssignmentCode,
        String serviceAssignmentName) {

}
