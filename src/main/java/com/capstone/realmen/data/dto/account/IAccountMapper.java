package com.capstone.realmen.data.dto.account;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.capstone.realmen.data.dao.account.AccountDAO;
import com.capstone.realmen.repository.database.account.AccountEntity;

@Mapper(
    componentModel = "spring", 
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAccountMapper {
    @Mapping(target = "password", ignore = true)
    AccountEntity toEntity(Account dto);

    Account toDto(AccountDAO dao);

    Account toDto(AccountEntity entity);

}
