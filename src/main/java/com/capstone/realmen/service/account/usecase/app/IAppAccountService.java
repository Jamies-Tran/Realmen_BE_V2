package com.capstone.realmen.service.account.usecase.app;

import org.springframework.data.domain.Page;

import com.capstone.realmen.common.request.PageRequestCustom;
import com.capstone.realmen.data.dto.account.Account;
import com.capstone.realmen.data.dto.account.AccountCreated;
import com.capstone.realmen.service.account.data.AccountCreateRequire;
import com.capstone.realmen.service.account.data.AccountSearchCriteria;

public interface IAppAccountService {
    Page<Account> appFindAll(AccountSearchCriteria searchCriteria, PageRequestCustom pageRequestCustom);

    AccountCreated appCreateAccount(AccountCreateRequire createRequire);
}
