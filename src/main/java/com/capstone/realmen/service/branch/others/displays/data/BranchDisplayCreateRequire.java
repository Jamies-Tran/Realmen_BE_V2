package com.capstone.realmen.service.branch.others.displays.data;

import java.util.List;

import com.capstone.realmen.data.dto.branch.display.BranchDisplay;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record BranchDisplayCreateRequire(
    Long branchId,
    List<BranchDisplay> branchDisplays
) {
    public List<BranchDisplay> branchDisplays() {
        return branchDisplays.stream()
            .map(display -> display.withBranchId(branchId))
            .toList();
    }
}
