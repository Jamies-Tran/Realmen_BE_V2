package com.capstone.realmen.service.branch.data;

import com.capstone.realmen.data.dto.branch.Branch;
import com.capstone.realmen.data.dto.branch.display.BranchDisplay;

import java.util.List;
import lombok.Builder;
import lombok.With;

@With
@Builder
public record BranchCreateRequire(
        Branch branch,
        List<BranchDisplay> branchDisplays) {

}
