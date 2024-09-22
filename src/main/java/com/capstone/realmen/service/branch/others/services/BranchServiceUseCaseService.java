package com.capstone.realmen.service.branch.others.services;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.capstone.realmen.common.request.PageRequestCustom;
import com.capstone.realmen.data.dto.branch.service.BranchService;
import com.capstone.realmen.service.branch.others.services.data.BranchServiceSearchByField;
import com.capstone.realmen.service.branch.others.services.data.BranchServiceSearchCriteria;
import com.capstone.realmen.service.branch.others.services.usecase.admin.IAdminBranchServiceService;
import com.capstone.realmen.service.branch.others.services.usecase.app.IAppBranchServiceService;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BranchServiceUseCaseService implements IAppBranchServiceService, IAdminBranchServiceService {
    @NonNull
    BranchServiceQueryService query;

    @Override
    public Page<BranchService> appFindAll(BranchServiceSearchCriteria searchCriteria,
            PageRequestCustom pageRequestCustom) {
        return query.findAll(searchCriteria, pageRequestCustom);
    }

    @Override
    public BranchService appFindById(BranchServiceSearchByField searchByField) {
        return query.findById(searchByField);
    }

    @Override
    public Page<BranchService> adminFindAll(BranchServiceSearchCriteria searchCriteria,
            PageRequestCustom pageRequestCustom) {
        return query.findAll(searchCriteria, pageRequestCustom);
    }

    @Override
    public BranchService adminFindById(BranchServiceSearchByField searchByField) {
        return query.findById(searchByField);
    }

}
