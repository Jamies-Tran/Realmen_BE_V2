package com.capstone.realmen.data.dto.branch.service;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.capstone.realmen.data.dao.branch.service.BranchServiceDAO;
import com.capstone.realmen.repository.database.branch.service.BranchServiceEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IBranchServiceMapper {
    BranchServiceEntity toEntity(BranchService dto);

    BranchServiceEntity toEntity(BranchServiceDAO dao);

    BranchService toDto(BranchServiceDAO dao);
}
