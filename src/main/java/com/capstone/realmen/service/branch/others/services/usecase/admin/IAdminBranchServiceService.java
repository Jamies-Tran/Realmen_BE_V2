package com.capstone.realmen.service.branch.others.services.usecase.admin;

import org.springframework.data.domain.Page;

import com.capstone.realmen.common.request.PageRequestCustom;
import com.capstone.realmen.data.dto.branch.service.BranchService;
import com.capstone.realmen.service.branch.others.services.data.BranchServiceSearchByField;
import com.capstone.realmen.service.branch.others.services.data.BranchServiceSearchCriteria;

public interface IAdminBranchServiceService {
    Page<BranchService> adminFindAll(BranchServiceSearchCriteria searchCriteria, PageRequestCustom pageRequestCustom);

    BranchService adminFindById(BranchServiceSearchByField searchByField);
}
