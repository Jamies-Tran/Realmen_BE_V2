package com.capstone.realmen.service.branch.data;

import java.util.List;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record BranchAddServiceRequire(Long branchId, List<BranchServiceRequire> services) {
    public static BranchAddServiceRequire of(Long branchId,
            List<BranchServiceRequire> services) {
        return BranchAddServiceRequire.builder()
                .branchId(branchId)
                .services(services)
                .build();

    }

    public static BranchAddServiceRequire of(List<BranchServiceRequire> services) {
        return BranchAddServiceRequire.builder()
                .services(services)
                .build();

    }

}
