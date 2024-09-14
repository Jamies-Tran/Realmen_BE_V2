package com.capstone.realmen.service.plans.others.daily.plan.others.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.capstone.realmen.common.request.PageRequestCustom;
import com.capstone.realmen.data.dto.plans.daily.service.DailyPlanService;
import com.capstone.realmen.service.plans.others.daily.plan.others.service.data.DailyPlanServiceSearchByField;
import com.capstone.realmen.service.plans.others.daily.plan.others.service.data.DailyPlanServiceSearchCriteria;
import com.capstone.realmen.service.plans.others.daily.plan.others.service.usecase.app.IAppDailyPlanServiceService;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DailyPlanServiceUseCaseService implements IAppDailyPlanServiceService {
    @NonNull
    DailyPlanServiceQueryService query;

    @Override
    public DailyPlanService appFindById(DailyPlanServiceSearchByField searchByField) {
        return query.findById(searchByField);
    }

    @Override
    public Page<DailyPlanService> appFindAll(DailyPlanServiceSearchCriteria searchCriteria,
            PageRequestCustom pageRequestCustom) {
        return query.findAll(searchCriteria, pageRequestCustom);
    }
}
