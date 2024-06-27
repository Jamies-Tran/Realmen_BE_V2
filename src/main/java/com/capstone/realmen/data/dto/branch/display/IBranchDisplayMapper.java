package com.capstone.realmen.data.dto.branch.display;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.capstone.realmen.repository.database.branch.display.BranchDisplayEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IBranchDisplayMapper {
    BranchDisplayEntity toEntity(BranchDisplay dto);
}
