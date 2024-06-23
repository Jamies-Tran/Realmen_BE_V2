package com.capstone.realmen.service.shop.service.others.display.data;

import java.util.List;

import com.capstone.realmen.data.dto.shop.service.display.ServiceDisplay;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record ServiceDisplayCreateRequire(
    Long shopServiceId,
    List<ServiceDisplay> serviceDisplays
) {
    public List<ServiceDisplay> serviceDisplays() {
        return serviceDisplays.stream()
            .map(serviceDisplay -> serviceDisplay.withShopServiceId(shopServiceId))
            .toList();
    }
}
