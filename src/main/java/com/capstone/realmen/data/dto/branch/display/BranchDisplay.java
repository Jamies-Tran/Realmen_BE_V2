package com.capstone.realmen.data.dto.branch.display;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record BranchDisplay(
    Long branchDisplayId,
    Long branchId,
    String branchDisplayContent
) {
    
}
