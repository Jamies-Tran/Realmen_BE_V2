package com.capstone.realmen.service.shop.service.others.display.data;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record ServiceDisplaySearchByField(
    Long serviceDiplayId,
    Long shopServiceId
) {
    public static ServiceDisplaySearchByField ofShopServiceId(Long shopServiceId) {
        return ServiceDisplaySearchByField.builder()
            .shopServiceId(shopServiceId)
            .build();
    }
}
