package com.capstone.realmen.data.dto.branch.service;

import java.util.List;

import com.capstone.realmen.data.dto.shop.service.display.ServiceDisplay;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record BranchService(
                Long branchServiceId,
                Long branchId,
                Long shopServiceId,
                String shopServiceName,
                Long branchServicePrice,
                Long shopServicePrice,
                String shopServiceThumbnail,
                String serviceAssignmentCode,
                String serviceAssignmentName,
                String shopCategoryCode,
                String shopCategoryName,
                Integer estimateDuration,
                String durationUnitCode,
                String durationUnitName,
                String branchServiceStatusCode,
                String branchServiceStatusName,
                List<ServiceDisplay> serviceDisplays) {

}
