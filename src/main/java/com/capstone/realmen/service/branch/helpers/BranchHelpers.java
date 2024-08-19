package com.capstone.realmen.service.branch.helpers;

import java.util.List;

import com.capstone.realmen.common.enums.EBranchStatus;
import com.capstone.realmen.service.branch.data.BranchAction;
import com.capstone.realmen.service.branch.data.EBranchAction;

public class BranchHelpers {
    public List<BranchAction> getAllowableAction(String branchStatusCode) {
        EBranchStatus branchStatus = EBranchStatus.findByCode(branchStatusCode);
        switch (branchStatus) {
            case ACTIVE:
                return List.of(
                    BranchAction.of(EBranchAction.ACTIVATE, false)
                );
            case INACTIVE:
                return List.of(
                    BranchAction.of(EBranchAction.ACTIVATE, true)
                );
            case DELETED:
                return List.of(
                    BranchAction.of(EBranchAction.ACTIVATE, false)
                );
            default:
                return List.of();
        }
    }
}
