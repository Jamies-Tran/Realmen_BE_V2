package com.capstone.realmen.controller.api.admin.shop.service.displays.models;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.capstone.realmen.data.dto.shop.service.display.ServiceDisplay;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ServiceDisplayModelMapper {
    ServiceDisplay toDto(ServiceDisplayRequest model);
}
