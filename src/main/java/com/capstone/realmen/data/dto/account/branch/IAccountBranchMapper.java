package com.capstone.realmen.data.dto.account.branch;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.capstone.realmen.repository.database.account.branch.AccountBranchEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAccountBranchMapper {
    AccountBranchEntity toEntity(AccountBranch dto);

    AccountBranch toDto(AccountBranchEntity entity);
}
