package com.capstone.realmen.service.account.usecase.admin;

import org.springframework.data.domain.Page;

import com.capstone.realmen.common.request.PageRequestCustom;
import com.capstone.realmen.data.dto.account.Account;
import com.capstone.realmen.data.dto.account.AccountCreated;
import com.capstone.realmen.service.account.data.AccountCreateRequire;
import com.capstone.realmen.service.account.data.AccountSearchByField;
import com.capstone.realmen.service.account.data.AccountSearchCriteria;

public interface IAdminAccountService {
    AccountCreated adminCreate(AccountCreateRequire createRequire);

    AccountCreated adminRecpetionistCreateCustomer(AccountCreateRequire createRequire);

    Account adminFindByPhoneOrStaffCode(AccountSearchByField search);

    Page<Account> adminFindAll(AccountSearchCriteria searchCriteria, PageRequestCustom pageRequestCustom);
    
}
