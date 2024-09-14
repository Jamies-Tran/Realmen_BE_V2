package com.capstone.realmen.service.branch.others.services.data;

import java.util.List;
import java.util.Objects;

import org.springframework.util.StringUtils;

import lombok.Builder;

@Builder
public record BranchServiceSearchCriteria(
        Long branchId,
        String search,
        Long shopCategoryId,
        String assignmentTypeCode,
        List<String> statusCodes,
        List<Long> priceRange,
        List<Long> serviceIds) {
    public static BranchServiceSearchCriteria of(Long branchId, String search, String assignmentTypeCode,
            List<String> statusCodes,
            List<Long> priceRange) {
        return BranchServiceSearchCriteria.builder()
                .branchId(branchId)
                .search(search.toLowerCase())
                .assignmentTypeCode(assignmentTypeCode)
                .statusCodes(statusCodes)
                .priceRange(priceRange)
                .build();
    }

    public static BranchServiceSearchCriteria of(Long branchId, List<String> statusCodes) {
        return BranchServiceSearchCriteria.builder()
                .branchId(branchId)
                .statusCodes(statusCodes)
                .build();
    }

    public Boolean hasSearchEmpty() {
        return !StringUtils.hasText(search);
    }

    public Boolean hasStatusCodeEmpty() {
        return Objects.isNull(statusCodes) || statusCodes.isEmpty();
    }

    public Boolean hasPriceRangeEmpty() {
        return Objects.isNull(priceRange) || priceRange.isEmpty();
    }

    public Boolean hasShopCategoryIdEmpty() {
        return Objects.isNull(shopCategoryId);
    }

    public Boolean hasAssignmentTypeCodeEmpty() {
        return !StringUtils.hasText(assignmentTypeCode);
    }

    public Boolean hasServiceIdEmpty() {
        return Objects.isNull(serviceIds) || serviceIds.isEmpty();
    }

    public List<Long> priceRange() {
        if (hasPriceRangeEmpty()) {
            return List.of(0L, 0L);
        }
        return priceRange;
    }
}
