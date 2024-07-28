package com.capstone.realmen.controller.api.app.branch.models;

import lombok.Builder;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Builder
public record AppBranchProvinceGroupResponse(
    String province,
    List<AppBranchResponse> branches,
    Integer total
) {
    public static List<AppBranchProvinceGroupResponse> of(List<AppBranchResponse> branches) {
        Map<String, List<AppBranchResponse>> branchMap = branches.stream()
            .collect(Collectors.groupingBy(AppBranchResponse::branchProvince));
        return branchMap.keySet().stream().map(province -> {
            List<AppBranchResponse> appBranches = branchMap.get(province);
            return AppBranchProvinceGroupResponse.builder()
                .province(province)
                .branches(appBranches)
                .total(appBranches.size())
                .build();
        }).toList();
    }
}
