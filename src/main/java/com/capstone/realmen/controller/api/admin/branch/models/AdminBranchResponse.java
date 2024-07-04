package com.capstone.realmen.controller.api.admin.branch.models;

import java.time.LocalTime;

public record AdminBranchResponse(
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
                String branchStatusCode,
                String branchStatusName) {

}
