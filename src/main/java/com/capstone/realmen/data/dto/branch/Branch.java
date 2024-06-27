package com.capstone.realmen.data.dto.branch;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import com.capstone.realmen.data.dto.branch.display.BranchDisplay;
import com.capstone.realmen.data.dto.shop.service.ShopService;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record Branch(
        Long branchId,
        String branchName,
        String branchHotline,
        String branchManagerCode,
        String branchManagerName,
        String branchThumbnail,
        String branchAddress,
        String branchStreet,
        String branchWard,
        String branchDistrict,
        String branchProvince,
        Double latitude,
        Double longitude,
        LocalTime open,
        LocalTime close,
        List<BranchDisplay> branchDisplays,
        List<ShopService> shopServices,
        Double distance,
        List<Long> comboIds,
        Integer requireStaffs,
        Integer maxStaffs,
        Integer currentStaffs,
        Boolean isAddableStaff,
        String branchStatusCode,
        String branchStatusName,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

}
