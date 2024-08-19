package com.capstone.realmen.service.branch.others.services.data;

import com.capstone.realmen.repository.database.account.AccountEntity;

import lombok.Builder;

@Builder
public record BranchServiceActiveRequire(
    Long branchId,
    String professionalTypeCode
) {
    public static BranchServiceActiveRequire of(Long branchId, AccountEntity account) {
        return BranchServiceActiveRequire.builder()
            .branchId(branchId)
            .professionalTypeCode(account.getProfessionalTypeCode())
            .build();
    }
}
