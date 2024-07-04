package com.capstone.realmen.controller.api.collection.professional.models;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.capstone.realmen.data.dto.account.professional.Professional;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IProfessionalModelMapper {
    ProfessionalResponse toModel(Professional toDto);
}
