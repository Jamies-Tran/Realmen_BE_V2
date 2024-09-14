package com.capstone.realmen.service.branch.others.services.usecase.app;

import org.springframework.data.domain.Page;

import com.capstone.realmen.common.request.PageRequestCustom;
import com.capstone.realmen.data.dto.branch.service.BranchService;
import com.capstone.realmen.service.branch.others.services.data.BranchServiceSearchByField;
import com.capstone.realmen.service.branch.others.services.data.BranchServiceSearchCriteria;

public interface IAppBranchServiceService {
    Page<BranchService> appFindAll(BranchServiceSearchCriteria searchCriteria, PageRequestCustom pageRequestCustom);

    BranchService appFindById(BranchServiceSearchByField searchByField);
}
