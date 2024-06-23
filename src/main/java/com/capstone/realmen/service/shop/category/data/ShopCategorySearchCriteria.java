package com.capstone.realmen.service.shop.category.data;

import java.util.Objects;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record ShopCategorySearchCriteria(
    String search,
    Integer serviceNumber
) {
    public ShopCategorySearchCriteria toLowerCase() {
        return this.withSearch(search.toLowerCase());
    }

    public Boolean hasSearchEmpty() {
        return Objects.isNull(search) || search.isEmpty();
    }
    
}
