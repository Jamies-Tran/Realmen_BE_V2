package com.capstone.realmen.data.dto.account;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.capstone.realmen.repository.database.account.AccountEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAccountMapper {
    Account toDto(AccountEntity entity);

    AccountEntity toEntity(Account dto);
}
