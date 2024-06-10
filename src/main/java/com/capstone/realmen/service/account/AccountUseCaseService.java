package com.capstone.realmen.service.account;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capstone.realmen.data.dto.account.Account;
import com.capstone.realmen.data.dto.account.AccountCreated;
import com.capstone.realmen.service.account.data.CreateRequire;
import com.capstone.realmen.service.account.data.SearchByField;
import com.capstone.realmen.service.account.usecase.admin.IAdminAccountService;
import com.capstone.realmen.service.account.usecase.app.IAppAccountService;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountUseCaseService implements IAdminAccountService, IAppAccountService {
    @NonNull
    AccountQueryService accountQueryService;
    @NonNull
    AccountCommandService accountCommandService;

    @Override
    public Account adminFindByPhoneOrStaffCode(SearchByField search) {
        return accountQueryService.find(search);
    }

    @Override
    @Transactional
    public AccountCreated adminCreateAccount(CreateRequire createRequire) {
        return accountCommandService.createAccount(createRequire);
    }

    @Override
    public AccountCreated adminRecpetionistCreateCustomer(CreateRequire createRequire) {
        return accountCommandService.createAccount(createRequire);
    }

    @Override
    @Transactional
    public AccountCreated appCreateAccount(CreateRequire createRequire) {
        return accountCommandService.createAccount(createRequire);
    }

}
