package com.capstone.realmen.service.branch.data;

import java.util.List;

import lombok.Builder;

@Builder
public record BranchAddStaffRequire(
    Long branchId,
    List<Long> staffIds
) {
    public static BranchAddStaffRequire of(Long branchId, List<Long> staffIds) {
        return BranchAddStaffRequire.builder()
            .branchId(branchId)
            .staffIds(staffIds)
            .build();
    }
}
