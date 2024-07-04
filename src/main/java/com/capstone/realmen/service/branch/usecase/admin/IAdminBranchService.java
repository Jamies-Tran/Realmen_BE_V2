package com.capstone.realmen.service.branch.usecase.admin;

import org.springframework.data.domain.Page;

import com.capstone.realmen.common.request.PageRequestCustom;
import com.capstone.realmen.data.dto.branch.Branch;
import com.capstone.realmen.service.branch.data.BranchActiveRequire;
import com.capstone.realmen.service.branch.data.BranchCreateRequire;
import com.capstone.realmen.service.branch.data.BranchSearchCriteria;

public interface IAdminBranchService {
    void adminCreate(BranchCreateRequire createRequire);

    void adminActive(BranchActiveRequire branchActiveRequire);

    Page<Branch> adminFindAll(BranchSearchCriteria searchCriteria, PageRequestCustom pageRequestCustom);
}
