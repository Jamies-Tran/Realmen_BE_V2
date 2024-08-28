package com.capstone.realmen.service.plans.usecase;

import org.springframework.data.domain.Page;

import com.capstone.realmen.common.request.PageRequestCustom;
import com.capstone.realmen.data.dto.plans.weekly.WeeklyPlan;
import com.capstone.realmen.service.plans.data.WeeklyPlanActiveRequire;
import com.capstone.realmen.service.plans.data.WeeklyPlanCreateRequire;
import com.capstone.realmen.service.plans.data.WeeklyPlanDuplicateRequire;
import com.capstone.realmen.service.plans.data.WeeklyPlanSearchByField;
import com.capstone.realmen.service.plans.data.WeeklyPlanSearchCriteria;

public interface IAdminWeeklyPlanService {
    void adminCreateWeeklyPlanDraft(WeeklyPlanCreateRequire createRequire);

    void adminDuplicateWeeklyPlan(WeeklyPlanDuplicateRequire duplicateRequire);

    WeeklyPlan adminActive(WeeklyPlanActiveRequire activeRequire);

    Page<WeeklyPlan> adminFindAll(WeeklyPlanSearchCriteria searchCriteria, PageRequestCustom pageRequestCustom);

    WeeklyPlan adminFindById(WeeklyPlanSearchByField searchByField);
}