package com.capstone.realmen.service.branch.helpers;

import java.util.List;
import java.util.Objects;

import com.capstone.realmen.common.enums.EBranchStatus;
import com.capstone.realmen.common.enums.ERole;
import com.capstone.realmen.controller.handler.exceptions.InvalidRequest;
import com.capstone.realmen.data.dto.account.Account;
import com.capstone.realmen.service.branch.data.BranchAction;
import com.capstone.realmen.service.branch.data.EBranchAction;

public class BranchHelpers {
    protected List<BranchAction> getAllowableAction(String branchStatusCode) {
        EBranchStatus branchStatus = EBranchStatus.findByCode(branchStatusCode);
        switch (branchStatus) {
            case ACTIVE:
                return List.of(
                        BranchAction.of(EBranchAction.ACTIVATE, false));
            case INACTIVE:
                return List.of(
                        BranchAction.of(EBranchAction.ACTIVATE, true));
            case DELETED:
                return List.of(
                        BranchAction.of(EBranchAction.ACTIVATE, false));
            default:
                return List.of();
        }
    }

    public void verifyActive(List<Account> staffList) {
        List<Account> getManager = staffList.stream()
                .filter(account -> Objects.equals(account.roleCode(), ERole.BRANCH_MANAGER.getCode()))
                .toList();
        List<Account> getStaff = staffList.stream()
                .filter(account -> Objects.equals(account.roleCode(), ERole.OPERATOR_STAFF.getCode()))
                .toList();
        List<Account> getRec = staffList.stream()
                .filter(account -> Objects.equals(account.roleCode(), ERole.RECEPTIONIST.getCode()))
                .toList();

        if (getManager.isEmpty()) {
            throw new InvalidRequest("Thiếu quảnh lý chi nhánh");
        } else if (getManager.size() > 1) {
            throw new InvalidRequest("Một chi nhánh chỉ có tối đa một quản lý");
        }

        if (getStaff.isEmpty()) {
            throw new InvalidRequest("Thiếu nhân viên vận hành");
        }

        if (getRec.isEmpty()) {
            throw new InvalidRequest("Thiếu tiếp tân");
        }
    }
}
