package com.capstone.realmen.service.plans.others.daily.plan.usecase;

import org.springframework.data.domain.Page;

import com.capstone.realmen.common.request.PageRequestCustom;
import com.capstone.realmen.data.dto.plans.daily.DailyPlan;
import com.capstone.realmen.service.plans.others.daily.plan.data.DailyPlanSearchByField;
import com.capstone.realmen.service.plans.others.daily.plan.data.DailyPlanSearchCriteria;

public interface IAppDailyPlanService {
    Page<DailyPlan> appFindAll(DailyPlanSearchCriteria searchCriteria, PageRequestCustom pageRequestCustom);

    Page<DailyPlan> appFindForStaff(DailyPlanSearchCriteria searchCriteria, PageRequestCustom pageRequestCustom);

    DailyPlan appFindById(DailyPlanSearchByField searchByField);
}
