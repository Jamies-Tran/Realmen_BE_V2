package com.capstone.realmen.controller.api.admin.branch.models.display;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.capstone.realmen.data.dto.branch.display.BranchDisplay;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAdminBranchDisplayModelMapper {
    BranchDisplay toDto(AdminBranchDisplayRequest model);
}
