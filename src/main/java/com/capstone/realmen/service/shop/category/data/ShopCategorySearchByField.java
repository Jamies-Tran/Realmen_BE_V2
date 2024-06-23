package com.capstone.realmen.service.shop.category.data;

import lombok.Builder;

@Builder
public record ShopCategorySearchByField(
    Long shopCategoryId,
    String shopCategoryCode
) {
    
}
