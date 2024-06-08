package com.capstone.realmen.service.account;

import org.springframework.stereotype.Service;

import com.capstone.realmen.data.dto.account.Account;
import com.capstone.realmen.service.account.data.CreateRequire;
import com.capstone.realmen.service.account.data.SearchByField;
import com.capstone.realmen.service.account.usecase.admin.IAccountAdminService;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountUseCaseService implements IAccountAdminService {
    @NonNull
    AccountQueryService accountQueryService;

    @Override
    public Account adminFindByPhoneOrStaffCode(SearchByField search) {
        return accountQueryService.find(search);
    }

    @Override
    public void adminCreateAccount(CreateRequire createRequire) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'adminCreateAccount'");
    }

}
