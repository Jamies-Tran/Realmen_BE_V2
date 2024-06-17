package com.capstone.realmen.controller.api.admin.account;

import org.springframework.web.bind.annotation.RestController;

import com.capstone.realmen.common.response.ValueResponse;
import com.capstone.realmen.controller.api.admin.account.models.AccountCreatedResponse;
import com.capstone.realmen.controller.api.admin.account.models.AccountRequest;
import com.capstone.realmen.controller.api.admin.account.models.IAdminAccountModelMapper;
import com.capstone.realmen.data.dto.account.Account;
import com.capstone.realmen.service.account.AccountUseCaseService;
import com.capstone.realmen.service.account.data.AccountCreateRequire;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdminAccountController implements IAdminAccountAPI {
    @NonNull
    AccountUseCaseService accountUseCaseService;
    @NonNull
    IAdminAccountModelMapper modelMapper;

    @Override
    public ValueResponse<AccountCreatedResponse> createStaff(@Valid AccountRequest request) {
        Account account = modelMapper.toDto(request);
        return new ValueResponse<AccountCreatedResponse>(
                modelMapper.toModel(
                        accountUseCaseService
                                .adminCreate(AccountCreateRequire.ofStaff(account))));
    }

}
