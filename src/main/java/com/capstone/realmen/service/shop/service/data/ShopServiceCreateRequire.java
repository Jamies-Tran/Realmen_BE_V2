package com.capstone.realmen.service.shop.service.data;

import com.capstone.realmen.data.dto.shop.service.ShopService;
import com.capstone.realmen.data.dto.shop.service.display.ServiceDisplay;

import java.util.List;
import lombok.Builder;
import lombok.With;

@With
@Builder
public record ShopServiceCreateRequire(
    ShopService shopService,
    List<ServiceDisplay> serviceDisplays
) {
    
}
