package com.capstone.realmen.service.branch.usecase.admin;

import org.springframework.data.domain.Page;

import com.capstone.realmen.common.request.PageRequestCustom;
import com.capstone.realmen.data.dto.branch.Branch;
import com.capstone.realmen.service.branch.data.BranchSearchByField;
import com.capstone.realmen.service.branch.data.BranchSearchCriteria;

public interface IAppBranchService {
    Branch appFindById(BranchSearchByField searchByField);

    Page<Branch> appFindAll(BranchSearchCriteria searchCriteria, PageRequestCustom pageRequestCustom);
}
