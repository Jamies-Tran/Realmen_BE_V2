package com.capstone.realmen.service.branch;

import org.springframework.stereotype.Service;

import com.capstone.realmen.service.branch.data.BranchActiveRequire;
import com.capstone.realmen.service.branch.data.BranchCreateRequire;
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

    @Override
    public void adminCreate(BranchCreateRequire createRequire) {
        branchCommandService.create(createRequire);
    }

    @Override
    public void adminActive(BranchActiveRequire branchActiveRequire) {
        branchCommandService.active(branchActiveRequire);
    }

}
