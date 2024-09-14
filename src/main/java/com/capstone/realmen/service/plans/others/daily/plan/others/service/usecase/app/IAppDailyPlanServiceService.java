package com.capstone.realmen.service.plans.others.daily.plan.others.service.usecase.app;

import org.springframework.data.domain.Page;

import com.capstone.realmen.common.request.PageRequestCustom;
import com.capstone.realmen.data.dto.plans.daily.service.DailyPlanService;
import com.capstone.realmen.service.plans.others.daily.plan.others.service.data.DailyPlanServiceSearchByField;
import com.capstone.realmen.service.plans.others.daily.plan.others.service.data.DailyPlanServiceSearchCriteria;

public interface IAppDailyPlanServiceService {
    DailyPlanService appFindById(DailyPlanServiceSearchByField searchByField);   

    Page<DailyPlanService> appFindAll(DailyPlanServiceSearchCriteria searchCriteria, PageRequestCustom pageRequestCustom);
}
