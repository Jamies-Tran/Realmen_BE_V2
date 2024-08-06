package com.capstone.realmen.service.branch.data;

import lombok.Builder;

@Builder
public record BranchSearchByField(Long branchId) {
    public static BranchSearchByField of(Long branchId) {
        return BranchSearchByField.builder()
                .branchId(branchId)
                .build();
    }
}
