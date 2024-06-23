package com.capstone.realmen.data.dto.shop.service.display;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.capstone.realmen.repository.database.shop.service.display.ServiceDisplayEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ServiceDisplayMapper {
    ServiceDisplayEntity toEntity(ServiceDisplay dto);
}
