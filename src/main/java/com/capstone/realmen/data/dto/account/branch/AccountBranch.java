package com.capstone.realmen.data.dto.account.branch;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record AccountBranch(
        Long accountId,
        Long branchId,
        String workingStatusCode,
        String workingStatusName,
        Boolean isManager) {

}
