package com.capstone.realmen.service.branch.others.services.data;

import java.util.List;

import com.capstone.realmen.data.dto.account.Account;
import com.capstone.realmen.service.branch.data.BranchServiceRequire;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record BranchServiceCreateRequire(
        Long branchId,
        List<BranchServiceRequire> branchServices,
        List<Account> staffs) {

    public static BranchServiceCreateRequire of(
            Long branchId,
            List<BranchServiceRequire> branchServices,
            List<Account> staffs) {
        return BranchServiceCreateRequire.builder()
                .branchId(branchId)
                .staffs(staffs)
                .branchServices(branchServices)
                .build();
    }
}
