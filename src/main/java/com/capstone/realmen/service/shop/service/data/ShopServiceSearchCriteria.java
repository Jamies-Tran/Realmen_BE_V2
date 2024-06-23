package com.capstone.realmen.service.shop.service.data;

import java.util.List;
import java.util.Objects;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record ShopServiceSearchCriteria(
        String search,
        List<Long> shopServicePriceRange) {
    public List<Long> shopServicePriceRange() {
        return shopServicePriceRange.stream().sorted().toList();
    }

    public static ShopServiceSearchCriteria ofDefault() {
        return ShopServiceSearchCriteria.builder()
                .search("")
                .shopServicePriceRange(List.of())
                .build();
    }

    public ShopServiceSearchCriteria toLowerCase() {
        return this.withSearch(search.toLowerCase());
    }

    public Boolean hasSearchEmpty() {
        return Objects.isNull(search) || search.isBlank();
    }

    public Boolean hasShopServicePriceRangeEmpty() {
        return Objects.isNull(shopServicePriceRange) || shopServicePriceRange.isEmpty();
    }

    public Long priceFrom() {
        return this.shopServicePriceRange().get(0);
    }

    public Long priceTo() {
        return this.shopServicePriceRange().get(1);
    }
}
