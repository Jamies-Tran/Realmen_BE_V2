package com.capstone.realmen.service.plans.others.daily.plan.usecase;

import org.springframework.data.domain.Page;

import com.capstone.realmen.common.request.PageRequestCustom;
import com.capstone.realmen.data.dto.plans.daily.DailyPlan;
import com.capstone.realmen.service.plans.others.daily.plan.data.DailyPlanSearchByField;
import com.capstone.realmen.service.plans.others.daily.plan.data.DailyPlanSearchCriteria;
import com.capstone.realmen.service.plans.others.daily.plan.data.DailyPlanUpdateRequire;

public interface IAdminDailyPlanService {
    DailyPlan adminUpdate(DailyPlanUpdateRequire updateRequire);

    DailyPlan adminFindById(DailyPlanSearchByField searchByField);

    Page<DailyPlan> adminFindAll(DailyPlanSearchCriteria searchCriteria, PageRequestCustom pageRequestCustom);
}
