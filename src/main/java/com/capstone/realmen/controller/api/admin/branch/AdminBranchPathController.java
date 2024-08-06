package com.capstone.realmen.controller.api.admin.branch;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.capstone.realmen.common.response.ValueResponse;
import com.capstone.realmen.controller.api.admin.branch.models.AdminBranchActiveBranchRequest;
import com.capstone.realmen.controller.api.admin.branch.models.AdminBranchResponse;
import com.capstone.realmen.controller.api.admin.branch.models.BranchServiceRequest;
import com.capstone.realmen.controller.api.admin.branch.models.IAdminBranchModelMapper;
import com.capstone.realmen.data.dto.branch.Branch;
import com.capstone.realmen.service.branch.BranchUseCaseService;
import com.capstone.realmen.service.branch.data.BranchAddServiceRequire;
import com.capstone.realmen.service.branch.data.BranchSearchByField;
import com.capstone.realmen.service.branch.data.BranchServiceRequire;

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
                        .withBranchId(branchId));
    }

    @Override
    public ValueResponse<AdminBranchResponse> findById(Long branchId) {
        Branch foundBranch = branchUseCaseService.adminFindById(BranchSearchByField.of(branchId));
        return new ValueResponse<AdminBranchResponse>(modelMapper.toModel(foundBranch));
    }

    @Override
    public void addService(Long branchId, @Valid List<BranchServiceRequest> branchServiceRequests) {
        List<BranchServiceRequire> services = branchServiceRequests.stream()
                .map(modelMapper::toDto)
                .toList();
        BranchAddServiceRequire addServiceRequire = BranchAddServiceRequire.of(branchId, services);
        branchUseCaseService.adminAddService(addServiceRequire);
    }
}
