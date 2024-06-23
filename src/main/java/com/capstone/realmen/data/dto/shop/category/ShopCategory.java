package com.capstone.realmen.data.dto.shop.category;

import java.util.List;

import com.capstone.realmen.data.dto.shop.service.ShopService;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record ShopCategory(
                Long shopCategoryId,
                String shopCategoryCode,
                String shopCategoryName,
                String serviceAssignmentCode,
                String serviceAssignmentName,
                List<ShopService> shopServices) {

}
