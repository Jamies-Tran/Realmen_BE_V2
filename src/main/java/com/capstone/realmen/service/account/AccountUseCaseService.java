package com.capstone.realmen.service.account;

import org.springframework.stereotype.Service;

import com.capstone.realmen.data.dto.account.Account;
import com.capstone.realmen.service.account.data.SearchByFieldPhoneOrStaffCode;
import com.capstone.realmen.usecase.account.admin.IAccountAdminService;

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
    public Account findByPhoneOrStaffCode(String phoneOrStaffCode) {
        return accountQueryService
                .find(SearchByFieldPhoneOrStaffCode.of(phoneOrStaffCode));
    }

}
