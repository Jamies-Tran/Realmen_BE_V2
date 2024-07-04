package com.capstone.realmen.data.dto.branch.status;

import com.capstone.realmen.common.enums.EBranchStatus;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record BranchStatus(
    String code,
    String name
) {
    public static BranchStatus of(EBranchStatus branchStatus) {
        return BranchStatus.builder()
            .code(branchStatus.getCode())
            .name(branchStatus.getName())
            .build();
    }
}
