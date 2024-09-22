package com.capstone.realmen.controller.api.app.branch;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.capstone.realmen.common.response.PageImplResponse;
import com.capstone.realmen.controller.api.app.branch.models.AppBranchProvinceGroupResponse;
import com.capstone.realmen.controller.api.app.branch.models.AppBranchResponse;

@RequestMapping("/mobile/branch")
public interface IAppBranchAPI {
    @GetMapping
    PageImplResponse<AppBranchResponse> findAll(
            @RequestParam(required = false, value = "search", defaultValue = "") String search,
            @RequestParam(required = false, value = "latitude", defaultValue = "0.0") Double latitude,
            @RequestParam(required = false, value = "longitude", defaultValue = "0.0") Double longitude,
            @RequestParam(required = false, value = "serviceIds",defaultValue = "") List<Long> serviceIds,
            @RequestParam(required = false, value = "branchStatusCodes", defaultValue = "") List<String> branchStatusCodes,
            @RequestParam(required = false, value = "current", defaultValue = "1") Integer current,
            @RequestParam(required = false, value = "pageSize", defaultValue = "20") Integer pageSize);

    @GetMapping("/group-by-province")
    PageImplResponse<AppBranchProvinceGroupResponse> findAllGroupByProvince(
            @RequestParam(required = false, value = "search", defaultValue = "") String search,
            @RequestParam(required = false, value = "latitude", defaultValue = "0.0") Double latitude,
            @RequestParam(required = false, value = "longitude", defaultValue = "0.0") Double longitude,
            @RequestParam(required = false, value = "branchStatusCodes", defaultValue = "") List<String> branchStatusCodes,
            @RequestParam(required = false, value = "current", defaultValue = "1") Integer current,
            @RequestParam(required = false, value = "pageSize", defaultValue = "20") Integer pageSize);
}
