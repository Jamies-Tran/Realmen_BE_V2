package com.capstone.realmen.service.branch.data;

import lombok.Builder;

@Builder
public record BranchAction(
        String actionCode,
        Boolean isAllow) {
    public static BranchAction of(EBranchAction action, Boolean isAllow) {
        return BranchAction.builder()
                .actionCode(action.getCode())
                .isAllow(isAllow)
                .build();
    }

    public static BranchAction ofEmpty() {
        return BranchAction.builder()
                .actionCode("")
                .isAllow(false)
                .build();
    }
}
