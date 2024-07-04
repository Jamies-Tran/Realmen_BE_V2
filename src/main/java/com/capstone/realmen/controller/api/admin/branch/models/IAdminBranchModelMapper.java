package com.capstone.realmen.controller.api.admin.branch.models;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.capstone.realmen.data.dto.branch.Branch;
import com.capstone.realmen.service.branch.data.BranchActiveRequire;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAdminBranchModelMapper {
    @Mapping(target = "open", ignore = true)
    @Mapping(target = "close", ignore = true)
    @Mapping(target = "branchDisplays", ignore = true)
    Branch toDto(AdminBranchRequest model);

    BranchActiveRequire toDto(AdminBranchActiveBranchRequest model);

    AdminBranchResponse toModel(Branch dto);
}
