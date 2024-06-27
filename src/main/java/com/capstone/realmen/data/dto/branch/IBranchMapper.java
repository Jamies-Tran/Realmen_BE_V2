package com.capstone.realmen.data.dto.branch;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.capstone.realmen.repository.database.branch.BranchEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IBranchMapper {
    BranchEntity toEntity(Branch dto);
}
