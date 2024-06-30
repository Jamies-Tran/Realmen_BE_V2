package com.capstone.realmen.service.account.others.branch.data;

import java.util.List;
import java.util.Objects;

import com.capstone.realmen.common.enums.ERole;
import com.capstone.realmen.data.dto.account.Account;
import com.capstone.realmen.data.dto.account.branch.AccountBranch;

import lombok.Builder;

@Builder
public record AccountBranchCreateRequire(
        Long branchId,
        List<AccountBranch> accountBranches) {
    public static AccountBranchCreateRequire of(Long branchId, List<Account> staffList) {
        return AccountBranchCreateRequire.builder()
                .branchId(branchId)
                .accountBranches(staffList.stream()
                        .map(account -> AccountBranch.builder()
                                .branchId(branchId)
                                .accountId(account.accountId())
                                .isManager(Objects.equals(account.roleCode(), ERole.BRANCH_MANAGER.getCode()))
                                .build())
                        .toList())
                .build();
    }
}
