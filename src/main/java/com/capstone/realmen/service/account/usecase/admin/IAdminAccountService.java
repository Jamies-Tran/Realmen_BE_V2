package com.capstone.realmen.service.account.usecase.admin;

import com.capstone.realmen.data.dto.account.Account;
import com.capstone.realmen.data.dto.account.AccountCreated;
import com.capstone.realmen.service.account.data.CreateRequire;
import com.capstone.realmen.service.account.data.SearchByField;

public interface IAdminAccountService {
    Account adminFindByPhoneOrStaffCode(SearchByField search);

    AccountCreated adminCreateAccount(CreateRequire createRequire);

    AccountCreated adminRecpetionistCreateCustomer(CreateRequire createRequire);
}
