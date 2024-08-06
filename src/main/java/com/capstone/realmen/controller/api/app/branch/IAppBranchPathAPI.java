package com.capstone.realmen.controller.api.app.branch;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.capstone.realmen.common.response.ValueResponse;
import com.capstone.realmen.controller.api.app.branch.models.AppBranchResponse;

@RequestMapping("/mobile/branch/{branchId}")
public interface IAppBranchPathAPI {
    @GetMapping
    ValueResponse<AppBranchResponse> findById(@PathVariable Long branchId);
}
