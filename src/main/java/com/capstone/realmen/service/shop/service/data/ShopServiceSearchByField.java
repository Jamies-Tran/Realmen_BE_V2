package com.capstone.realmen.service.shop.service.data;

import java.util.List;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record ShopServiceSearchByField(
        Long shopServiceId,
        List<Long> shopServiceIds) {
    public static ShopServiceSearchByField of(Long shopServiceId) {
        return ShopServiceSearchByField.builder().shopServiceId(shopServiceId).build();
    }

    public static ShopServiceSearchByField of(List<Long> shopServiceIds) {
        return ShopServiceSearchByField.builder().shopServiceIds(shopServiceIds).build();
    }
}
