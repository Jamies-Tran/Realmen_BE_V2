package com.capstone.realmen.service.branch.usecase.admin;

import com.capstone.realmen.service.branch.data.BranchActiveRequire;
import com.capstone.realmen.service.branch.data.BranchCreateRequire;

public interface IAdminBranchService {
    void adminCreate(BranchCreateRequire createRequire);

    void adminActive(BranchActiveRequire branchActiveRequire);
}
