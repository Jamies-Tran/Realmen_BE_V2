package com.capstone.realmen.service.branch;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capstone.realmen.common.request.PageRequestCustom;
import com.capstone.realmen.data.dto.branch.Branch;
import com.capstone.realmen.service.branch.data.BranchActiveRequire;
import com.capstone.realmen.service.branch.data.BranchAddServiceRequire;
import com.capstone.realmen.service.branch.data.BranchCreateRequire;
import com.capstone.realmen.service.branch.data.BranchSearchByField;
import com.capstone.realmen.service.branch.data.BranchSearchCriteria;
import com.capstone.realmen.service.branch.usecase.admin.IAdminBranchService;
import com.capstone.realmen.service.branch.usecase.admin.IAppBranchService;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BranchUseCaseService implements IAdminBranchService, IAppBranchService {
    @NonNull
    BranchCommandService branchCommandService;

    @NonNull
    BranchQueryService branchQueryService;

    @Override
    @Transactional
    public void adminCreate(BranchCreateRequire createRequire) {
        branchCommandService.create(createRequire);
    }

    @Override
    @Transactional
    public void adminActive(BranchActiveRequire branchActiveRequire) {
        branchCommandService.active(branchActiveRequire);
    }

    @Override
    @Transactional
    public void adminAddService(BranchAddServiceRequire addServiceRequire) {
        branchCommandService.addService(addServiceRequire);
    }

    @Override
    public Page<Branch> adminFindAll(BranchSearchCriteria searchCriteria, PageRequestCustom pageRequestCustom) {
        return branchQueryService.findAll(searchCriteria, pageRequestCustom);
    }

    @Override
    public Branch adminFindById(BranchSearchByField searchByField) {
        return branchQueryService.findById(searchByField);
    }

    @Override
    public Page<Branch> appFindAll(BranchSearchCriteria searchCriteria, PageRequestCustom pageRequestCustom) {
        return branchQueryService.findAll(searchCriteria, pageRequestCustom);
    }

    @Override
    public Branch appFindById(BranchSearchByField searchByField) {
        return branchQueryService.findById(searchByField);
    }

}
