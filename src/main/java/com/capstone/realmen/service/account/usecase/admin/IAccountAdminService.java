package com.capstone.realmen.service.account.usecase.admin;

import com.capstone.realmen.data.dto.account.Account;
import com.capstone.realmen.service.account.data.CreateRequire;
import com.capstone.realmen.service.account.data.SearchByField;

public interface IAccountAdminService {
    Account adminFindByPhoneOrStaffCode(SearchByField search);

    void adminCreateAccount(CreateRequire createRequire);
}
