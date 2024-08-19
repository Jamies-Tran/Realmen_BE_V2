package com.capstone.realmen.service.account.others.branch.data;

import lombok.Builder;

@Builder
public record AccountBranchSearchByField(Long branchId) {
    public static AccountBranchSearchByField of(Long branchId) {
        return AccountBranchSearchByField.builder()
            .branchId(branchId)
            .build();
    }
    
}
