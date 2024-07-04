package com.capstone.realmen.controller.api.app.branch.models;

import java.time.LocalTime;

import com.capstone.realmen.data.dto.branch.distance.DistanceInKm;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record AppBranchResponse(
        Long branchId,
        String branchName,
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
        DistanceInKm distanceInKm,
        String branchStatusCode,
        String branchStatusName) {

}
