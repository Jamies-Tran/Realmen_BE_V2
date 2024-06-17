package com.capstone.realmen.service.account;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capstone.realmen.common.request.PageRequestCustom;
import com.capstone.realmen.data.dto.account.Account;
import com.capstone.realmen.data.dto.account.AccountCreated;
import com.capstone.realmen.service.account.data.AccountCreateRequire;
import com.capstone.realmen.service.account.data.AccountSearchByField;
import com.capstone.realmen.service.account.data.AccountSearchCriteria;
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
    public Account adminFindByPhoneOrStaffCode(AccountSearchByField search) {
        return accountQueryService.find(search);
    }

    @Override
    @Transactional
    public AccountCreated adminCreate(AccountCreateRequire createRequire) {
        return accountCommandService.createAccount(createRequire);
    }

    @Override
    public AccountCreated adminRecpetionistCreateCustomer(AccountCreateRequire createRequire) {
        return accountCommandService.createAccount(createRequire);
    }

    @Override
    @Transactional
    public AccountCreated appCreateAccount(AccountCreateRequire createRequire) {
        return accountCommandService.createAccount(createRequire);
    }

    @Override
    public Page<Account> appFindAll(AccountSearchCriteria searchCriteria, PageRequestCustom pageRequestCustom) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'appFindAll'");
    }

    @Override
    public Page<Account> adminFindAll(AccountSearchCriteria searchCriteria, PageRequestCustom pageRequestCustom) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'adminFindAll'");
    }

}
