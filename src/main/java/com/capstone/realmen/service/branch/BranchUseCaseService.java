package com.capstone.realmen.service.branch;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.capstone.realmen.common.request.PageRequestCustom;
import com.capstone.realmen.data.dto.branch.Branch;
import com.capstone.realmen.service.branch.data.BranchActiveRequire;
import com.capstone.realmen.service.branch.data.BranchCreateRequire;
import com.capstone.realmen.service.branch.data.BranchSearchCriteria;
import com.capstone.realmen.service.branch.usecase.admin.IAdminBranchService;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BranchUseCaseService implements IAdminBranchService {
    @NonNull
    BranchCommandService branchCommandService;

    @NonNull
    BranchQueryService branchQueryService;

    @Override
    public void adminCreate(BranchCreateRequire createRequire) {
        branchCommandService.create(createRequire);
    }

    @Override
    public void adminActive(BranchActiveRequire branchActiveRequire) {
        branchCommandService.active(branchActiveRequire);
    }

    @Override
    public Page<Branch> adminFindAll(BranchSearchCriteria searchCriteria, PageRequestCustom pageRequestCustom) {
        return branchQueryService.findAll(searchCriteria, pageRequestCustom);
    }

}
