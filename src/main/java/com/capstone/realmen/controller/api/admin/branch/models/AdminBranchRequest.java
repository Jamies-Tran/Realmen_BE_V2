package com.capstone.realmen.controller.api.admin.branch.models;

import java.time.LocalDateTime;
import java.util.List;

import com.capstone.realmen.controller.api.admin.branch.models.display.BranchDisplayRequest;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record AdminBranchRequest(
        Long branchId,
        String branchName,
        String branchHotline,
        String branchThumbnail,
        String branchAddress,
        LocalDateTime open,
        LocalDateTime close,
        List<BranchDisplayRequest> branchDisplays) {

}
