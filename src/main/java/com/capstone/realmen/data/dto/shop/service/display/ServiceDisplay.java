package com.capstone.realmen.data.dto.shop.service.display;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record ServiceDisplay(
    Long shopServiceDisplayId,
    Long shopServiceId,
    String serviceDisplayContent
) {
    
}
