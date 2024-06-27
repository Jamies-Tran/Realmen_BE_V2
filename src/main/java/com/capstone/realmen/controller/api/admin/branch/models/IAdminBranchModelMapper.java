package com.capstone.realmen.controller.api.admin.branch.models;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.capstone.realmen.data.dto.branch.Branch;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAdminBranchModelMapper {
    @Mapping(target = "open", ignore = true)
    @Mapping(target = "close", ignore = true)
    Branch toDto(AdminBranchRequest model);
}
