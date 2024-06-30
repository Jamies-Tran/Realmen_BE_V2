package com.capstone.realmen.data.dto.branch.service;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record BranchService(
    Long branchServiceId,
    Long branchId,
    Long shopServiceId,
    Long branchServicePrice
) {
    
}
