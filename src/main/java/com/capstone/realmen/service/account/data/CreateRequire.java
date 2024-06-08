package com.capstone.realmen.service.account.data;

import com.capstone.realmen.data.dto.account.Account;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record CreateRequire(
        Account account,
        Boolean isForStaff) {

    public static CreateRequire ofStaff(Account account) {
        return CreateRequire.init()
                .withAccount(account)
                .withIsForStaff(true);
    }

    public static CreateRequire ofCustomer(Account account) {
        return CreateRequire.init()
                .withAccount(account)
                .withIsForStaff(false);
    }

    private static CreateRequire init() {
        return CreateRequire.builder().build();
    }
}
