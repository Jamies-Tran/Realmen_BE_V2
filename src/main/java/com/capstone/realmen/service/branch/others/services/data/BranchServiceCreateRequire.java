package com.capstone.realmen.service.branch.others.services.data;

import java.util.List;

import com.capstone.realmen.data.dto.branch.service.BranchService;
import com.capstone.realmen.service.branch.data.BranchServiceRequire;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record BranchServiceCreateRequire(List<BranchService> branchServices) {
    
    public static BranchServiceCreateRequire of(Long branchId,
            List<BranchServiceRequire> serviceList) {
                return BranchServiceCreateRequire.builder()
                    .branchServices(serviceList.stream()
                        .map(service -> BranchService.builder()
                            .branchId(branchId)
                            .shopServiceId(service.shopServiceId())
                            .branchServicePrice(service.price())
                            .build())
                            .toList())
                    .build();
    }
}
