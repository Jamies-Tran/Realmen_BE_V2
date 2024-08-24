package com.capstone.realmen.service.account.helpers;

import java.util.Objects;

import com.capstone.realmen.common.enums.ERole;
import com.capstone.realmen.data.dto.account.Account;
import com.capstone.realmen.repository.database.account.AccountEntity;

public class AccountHelper {
    protected String generateStaffCode(Account account) {

        String role = account.roleCode();
        Long branchId = account.branchId();
        String phone = account.phone();
        String prefix;
        if (Objects.equals(role, ERole.OPERATOR_STAFF.getCode())) {
            String professional = account.professionalTypeCode();
            prefix = role.substring(0, 1).toUpperCase()
                    .concat(professional.substring(0, 1).toUpperCase());
        } else {
            prefix = role.substring(0, 1);
        }
        return "%s%s%s"
                .formatted(prefix, phone, branchId.toString());
    }

}
