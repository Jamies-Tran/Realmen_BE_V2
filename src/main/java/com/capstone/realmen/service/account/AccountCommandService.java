package com.capstone.realmen.service.account;

import org.springframework.stereotype.Service;

import com.capstone.realmen.common.request.RequestContext;
import com.capstone.realmen.data.dto.account.Account;
import com.capstone.realmen.repository.database.account.AccountRepository;
import com.capstone.realmen.service.account.data.CreateRequire;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountCommandService {
    @NonNull
    AccountRepository accountRepository;
    @NonNull
    RequestContext requestContext;

    public void createAccount(CreateRequire createRequire) {

    }

    private void createStaff(Account account) {
        
    }
}
