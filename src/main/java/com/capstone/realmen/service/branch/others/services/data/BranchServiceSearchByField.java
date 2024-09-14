package com.capstone.realmen.service.branch.others.services.data;

import java.util.List;

import lombok.Builder;

@Builder
public record BranchServiceSearchByField(
        Long branchId, 
        Long serviceId,
        List<Long> serviceIds) {
    public static BranchServiceSearchByField of(Long branchId, Long serviceId) {
        return BranchServiceSearchByField.builder()
            .branchId(branchId)
            .serviceId(serviceId)
            .build();
    }

    public static BranchServiceSearchByField of(Long branchId, List<Long> serviceIds) {
        return BranchServiceSearchByField.builder()
            .branchId(branchId)
            .serviceIds(serviceIds)
            .build();
    }
    
}
