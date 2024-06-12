package com.capstone.realmen.controller.api.app.account.models;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.capstone.realmen.data.dto.account.Account;
import com.capstone.realmen.data.dto.account.AccountCreated;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAppAccountModelMapper {
    Account toDto(AccountRequest model);

    AccountCreatedResponse toModel(AccountCreated dto);
}
