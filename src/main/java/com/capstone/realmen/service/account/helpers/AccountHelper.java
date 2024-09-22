package com.capstone.realmen.service.account.helpers;

import java.util.Objects;

import org.springframework.util.StringUtils;

import com.capstone.realmen.common.enums.ERole;
import com.capstone.realmen.controller.handler.exceptions.InvalidRequest;
import com.capstone.realmen.data.dto.account.Account;

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

    protected void validateCreateStaff(Account account) {
        switch (ERole.findByCode(account.roleCode()).get()) {
            case OPERATOR_STAFF:
                if(!StringUtils.hasText(account.professionalTypeCode())) {
                    throw new InvalidRequest("Thông tin nhân viên vận hành không hợp lệ");
                }
                break;
            case BRANCH_MANAGER, RECEPTIONIST:
                break;
        
            default:
                throw new InvalidRequest("Yêu cầu tạo tài khoản không hợp lệ");
        }
    }

}
