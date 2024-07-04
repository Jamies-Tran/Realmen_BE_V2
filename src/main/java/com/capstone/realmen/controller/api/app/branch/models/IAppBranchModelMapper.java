package com.capstone.realmen.controller.api.app.branch.models;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.capstone.realmen.data.dto.branch.Branch;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAppBranchModelMapper {
    AppBranchResponse toModel(Branch dto);
}
