package com.capstone.realmen.data.dto.account;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.capstone.realmen.data.dao.account.AccountDAO;
import com.capstone.realmen.repository.database.account.AccountEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAccountMapper {
    AccountEntity toEntity(Account dto);

    Account toDto(AccountDAO dao);

    Account toDto(AccountEntity entity);

}
