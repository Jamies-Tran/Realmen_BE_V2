package com.capstone.realmen.controller.api.admin.branch;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.capstone.realmen.common.response.ValueResponse;
import com.capstone.realmen.controller.api.admin.branch.models.AdminBranchActiveBranchRequest;
import com.capstone.realmen.controller.api.admin.branch.models.AdminBranchResponse;
import com.capstone.realmen.controller.api.admin.branch.models.BranchServiceRequest;

import jakarta.validation.Valid;

@RequestMapping("/web/branch/{branchId}")
public interface IAdminBranchPathAPI {
    @PutMapping("/active")
    @PreAuthorize("hasRole('ROLE_SHOPOWNER')")
    void active(@PathVariable Long branchId,
            @RequestBody @Valid AdminBranchActiveBranchRequest activeBranchRequest);

    @GetMapping
    @PreAuthorize("hasAnyRole({'ROLE_SHOPOWNER', 'ROLE_BRANCHMANAGER', 'ROLE_RECEPTIONIST'})")
    ValueResponse<AdminBranchResponse> findById(@PathVariable Long branchId);

    @PutMapping("/add-service")
    @PreAuthorize("hasRole('ROLE_SHOPOWNER')")
    void addService(
            @PathVariable Long branchId,
            @RequestBody @Valid List<BranchServiceRequest> branchServiceRequests);
}
