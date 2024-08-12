package com.capstone.realmen.controller.api.admin.branch;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.capstone.realmen.common.response.PageImplResponse;
import com.capstone.realmen.controller.api.admin.branch.models.AdminBranchRequest;
import com.capstone.realmen.controller.api.admin.branch.models.AdminBranchResponse;
import jakarta.validation.Valid;

@RequestMapping("/web/branch")
public interface IAdminBranchAPI {
    @PostMapping
    @PreAuthorize("hasRole('ROLE_SHOPOWNER')")
    void save(@RequestBody @Valid AdminBranchRequest adminBranchRequest);

    @GetMapping
    @PreAuthorize("hasAnyRole({'ROLE_SHOPOWNER', 'ROLE_BRANCHMANAGER', 'ROLE_RECPETIONIST'})")
    PageImplResponse<AdminBranchResponse> findAll(
            @RequestParam(required = false, value = "search", defaultValue = "") String search,
            @RequestParam(required = false, value = "branchStatusCodes", defaultValue = "") List<String> branchStatusCodes,
            @RequestParam(required = false, value = "current", defaultValue = "1") Integer current,
            @RequestParam(required = false, value = "pageSize", defaultValue = "20") Integer pageSize);
}
