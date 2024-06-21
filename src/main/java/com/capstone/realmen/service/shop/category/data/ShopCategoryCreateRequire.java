package com.capstone.realmen.service.shop.category.data;

import com.capstone.realmen.data.dto.shop.category.ShopCategory;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record ShopCategoryCreateRequire(
        ShopCategory shopCategory) {
    private static String prefix = "CAT";

    public ShopCategory of(String code) {
        return shopCategory
                .withShopCategoryCode(code);
    }

    public String shopCategoryCode(String numberPart) {
        return prefix.concat(numberPart);
    }
}
