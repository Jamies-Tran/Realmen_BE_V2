package com.capstone.realmen.service.branch.data;

import java.util.List;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record BranchActiveRequire(
        Long branchId,
        List<BranchServiceRequire> serviceList,
        List<Long> staffIdList
) {
}
