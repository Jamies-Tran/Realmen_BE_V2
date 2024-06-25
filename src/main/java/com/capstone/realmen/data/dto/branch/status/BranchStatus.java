package com.capstone.realmen.data.dto.branch.status;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record BranchStatus(
    String code,
    String name
) {
    
}
