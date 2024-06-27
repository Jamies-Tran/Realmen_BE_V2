package com.capstone.realmen.controller.api.admin.branch;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.capstone.realmen.controller.api.admin.branch.models.AdminBranchRequest;

import jakarta.validation.Valid;

@RequestMapping("/web/branch")
public interface IAdminBranchAPI {
    @PostMapping
    @PreAuthorize("hasRole('ROLE_SHOPOWNER')")
    void save(@RequestBody @Valid AdminBranchRequest adminBranchRequest);
}
