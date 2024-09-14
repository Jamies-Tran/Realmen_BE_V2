package com.capstone.realmen.controller.api.admin.shop.service.models;

import java.util.List;

import com.capstone.realmen.controller.api.admin.shop.service.displays.models.ServiceDisplayRequest;

import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.With;

@With
@Builder
public record AdminShopServiceRequest(
        Long shopCategoryId,
        @Pattern(regexp = "^[^!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?~`]+$", message = "Tên dịch vụ không hợp lệ") 
        String shopServiceName,
        Long shopServicePrice,
        String shopServiceThumbnail,
        Integer estimateDuration,
        String durationUnitCode,
        String durationUnitName,
        List<ServiceDisplayRequest> serviceDisplays) {

}
