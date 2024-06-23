package com.capstone.realmen.controller.api.admin.shop.service.displays.models;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record ServiceDisplayRequest(
    String serviceDisplayContent
) {
    
}
