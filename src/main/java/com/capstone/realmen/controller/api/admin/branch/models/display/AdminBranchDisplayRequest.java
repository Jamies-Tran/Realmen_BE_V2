package com.capstone.realmen.controller.api.admin.branch.models.display;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record AdminBranchDisplayRequest(
        String branchDisplayContent) {

}
