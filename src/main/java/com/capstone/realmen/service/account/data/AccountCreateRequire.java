package com.capstone.realmen.service.account.data;

import com.capstone.realmen.data.dto.account.Account;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record AccountCreateRequire(
        Account account,
        Boolean isForStaff,
        // receptionist create customer account
        Boolean isCreatedByRecept) {

    public static AccountCreateRequire ofStaff(Account account) {
        return AccountCreateRequire.init()
                .withAccount(account)
                .withIsForStaff(true);
    }

    public static AccountCreateRequire ofCustomer(Account account) {
        return AccountCreateRequire.init()
                .withAccount(account)
                .withIsForStaff(false)
                .withIsCreatedByRecept(false);
    }

    public static AccountCreateRequire ofReceptionist(Account account) {
        return AccountCreateRequire.init()
                .withAccount(account)
                .withIsForStaff(false)
                .withIsCreatedByRecept(true);
    }

    private static AccountCreateRequire init() {
        return AccountCreateRequire.builder().build();
    }
}
