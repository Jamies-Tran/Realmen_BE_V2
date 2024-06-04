package com.capstone.realmen.usecase.account.admin;

import com.capstone.realmen.data.dto.account.Account;

public interface IAccountAdminService {
    Account findByPhoneOrStaffCode(String phoneOrStaffCode);
}
