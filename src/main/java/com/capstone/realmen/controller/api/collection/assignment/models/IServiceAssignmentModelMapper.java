package com.capstone.realmen.controller.api.collection.assignment.models;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.capstone.realmen.data.dto.shop.category.assignment.ServiceAssignment;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IServiceAssignmentModelMapper {
    ServiceAssignmentResponse toModel(ServiceAssignment dto);
}
