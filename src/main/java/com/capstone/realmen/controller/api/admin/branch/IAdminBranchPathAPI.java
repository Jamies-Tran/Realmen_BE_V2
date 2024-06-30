package com.capstone.realmen.controller.api.admin.branch;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.capstone.realmen.controller.api.admin.branch.models.AdminBranchActiveBranchRequest;

import jakarta.validation.Valid;

@RequestMapping("/web/branch/{branchId}")
public interface IAdminBranchPathAPI {
    @PutMapping("/active")
    void active(@PathVariable Long branchId,
            @RequestBody @Valid AdminBranchActiveBranchRequest activeBranchRequest);
}
