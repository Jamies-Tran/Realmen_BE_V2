package com.capstone.realmen.controller.api.admin.branch.models;

import java.time.LocalTime;
import java.util.List;

import com.capstone.realmen.controller.api.admin.account.models.AccountResponse;
import com.capstone.realmen.controller.api.admin.shop.service.models.AdminShopServiceResponse;

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
        String branchStatusName,
        List<AccountResponse> staffs,
        List<AdminShopServiceResponse> services) {

}
