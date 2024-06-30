package com.capstone.realmen.controller.api.admin.branch;

import org.springframework.web.bind.annotation.RestController;

import com.capstone.realmen.controller.api.admin.branch.models.AdminBranchActiveBranchRequest;
import com.capstone.realmen.controller.api.admin.branch.models.IAdminBranchModelMapper;
import com.capstone.realmen.service.branch.BranchUseCaseService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdminBranchPathController implements IAdminBranchPathAPI {
    @NonNull
    BranchUseCaseService branchUseCaseService;

    @NonNull
    IAdminBranchModelMapper modelMapper;

    @Override
    public void active(Long branchId, @Valid AdminBranchActiveBranchRequest activeBranchRequest) {
        
        branchUseCaseService.adminActive(
            modelMapper.toDto(activeBranchRequest)
                .withBranchId(branchId)
        );
    }
}
