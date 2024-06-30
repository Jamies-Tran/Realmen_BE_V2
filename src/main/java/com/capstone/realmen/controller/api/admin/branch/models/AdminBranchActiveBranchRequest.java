package com.capstone.realmen.controller.api.admin.branch.models;

import java.util.List;

import lombok.Builder;

@Builder
public record AdminBranchActiveBranchRequest(
    List<Long> staffIdList,
    List<BranchServiceRequest> serviceList
) {
    
}
