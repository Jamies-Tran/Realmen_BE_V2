package com.capstone.realmen.service.shop.service.data;

import java.util.List;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record ShopServiceSearchByField(
    Long shopServiceId,
    List<Long> shopServiceIds
) {
    
}
