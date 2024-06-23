package com.capstone.realmen.service.shop.service.data;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record ShopServiceSearchByField(
    Long shopServiceId
) {
    
}
