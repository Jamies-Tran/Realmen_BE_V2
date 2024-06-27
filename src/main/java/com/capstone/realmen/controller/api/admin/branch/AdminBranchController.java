package com.capstone.realmen.controller.api.admin.branch;

import org.springframework.web.bind.annotation.RestController;

import com.capstone.realmen.controller.api.admin.branch.models.AdminBranchRequest;
import com.capstone.realmen.controller.api.admin.branch.models.IAdminBranchModelMapper;
import com.capstone.realmen.service.branch.BranchUseCaseService;
import com.capstone.realmen.service.branch.data.BranchCreateRequire;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdminBranchController implements IAdminBranchAPI {
    @NonNull
    BranchUseCaseService branchUseCaseService;
    @NonNull
    IAdminBranchModelMapper modelMapper;

    @Override
    public void save(@Valid AdminBranchRequest adminBranchRequest) {
        branchUseCaseService.adminCreate(
                BranchCreateRequire.builder()
                        .branch(
                            modelMapper.toDto(adminBranchRequest)
                                .withOpen(adminBranchRequest.open().toLocalTime())
                                .withClose(adminBranchRequest.close().toLocalTime())
                        )
                        .build());
    }

}
