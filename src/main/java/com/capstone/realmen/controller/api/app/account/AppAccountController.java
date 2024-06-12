package com.capstone.realmen.controller.api.app.account;

import org.springframework.web.bind.annotation.RestController;

import com.capstone.realmen.common.response.ValueResponse;
import com.capstone.realmen.controller.api.app.account.models.AccountCreatedResponse;
import com.capstone.realmen.controller.api.app.account.models.AccountRequest;
import com.capstone.realmen.controller.api.app.account.models.IAppAccountModelMapper;
import com.capstone.realmen.data.dto.account.Account;
import com.capstone.realmen.service.account.AccountUseCaseService;
import com.capstone.realmen.service.account.data.CreateRequire;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AppAccountController implements IAppAccountAPI {

    @NonNull
    AccountUseCaseService accountUseCaseService;
    @NonNull
    IAppAccountModelMapper modelMapper;

    @Override
    public ValueResponse<AccountCreatedResponse> createCustomer(@Valid AccountRequest request) {
        Account account = modelMapper.toDto(request);
        return new ValueResponse<AccountCreatedResponse>(
                modelMapper.toModel(accountUseCaseService
                        .appCreateAccount(CreateRequire.ofCustomer(account))));
    }

}
