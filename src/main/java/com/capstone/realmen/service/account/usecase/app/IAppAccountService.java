package com.capstone.realmen.service.account.usecase.app;

import com.capstone.realmen.data.dto.account.AccountCreated;
import com.capstone.realmen.service.account.data.CreateRequire;

public interface IAppAccountService {
    AccountCreated appCreateAccount(CreateRequire createRequire);
}
