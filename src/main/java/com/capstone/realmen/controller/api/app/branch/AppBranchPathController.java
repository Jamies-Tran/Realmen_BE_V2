package com.capstone.realmen.controller.api.app.branch;

import org.springframework.web.bind.annotation.RestController;

import com.capstone.realmen.common.response.ValueResponse;
import com.capstone.realmen.controller.api.app.branch.models.AppBranchResponse;
import com.capstone.realmen.controller.api.app.branch.models.IAppBranchModelMapper;
import com.capstone.realmen.data.dto.branch.Branch;
import com.capstone.realmen.service.branch.BranchUseCaseService;
import com.capstone.realmen.service.branch.data.BranchSearchByField;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AppBranchPathController implements IAppBranchPathAPI{
    @NonNull
    BranchUseCaseService branchUseCaseService;

    @NonNull
    IAppBranchModelMapper modelMapper;

    @Override
    public ValueResponse<AppBranchResponse> findById(Long branchId) {
        Branch foundBranch = branchUseCaseService.appFindById(BranchSearchByField.of(branchId));
        return new ValueResponse<AppBranchResponse>(modelMapper.toModel(foundBranch));
    }
}
