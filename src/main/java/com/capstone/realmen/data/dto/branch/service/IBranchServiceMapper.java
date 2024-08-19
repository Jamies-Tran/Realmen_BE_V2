package com.capstone.realmen.data.dto.branch.service;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.capstone.realmen.repository.database.branch.service.BranchServiceEntity;
import com.capstone.realmen.repository.database.branch.service.dao.BranchServiceDAO;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IBranchServiceMapper {
    BranchServiceEntity toEntity(BranchService dto);

    BranchServiceEntity toEntity(BranchServiceDAO dto);
}
