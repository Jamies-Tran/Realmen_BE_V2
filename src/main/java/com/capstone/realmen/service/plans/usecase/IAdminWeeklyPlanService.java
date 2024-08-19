package com.capstone.realmen.service.plans.usecase;

import com.capstone.realmen.data.dto.plans.weekly.WeeklyPlan;
import com.capstone.realmen.service.plans.data.WeeklyPlanActiveRequire;
import com.capstone.realmen.service.plans.data.WeeklyPlanCreateRequire;
import com.capstone.realmen.service.plans.data.WeeklyPlanDuplicateRequire;

public interface IAdminWeeklyPlanService {
    void adminCreateWeeklyPlanDraft(WeeklyPlanCreateRequire createRequire);

    void adminDuplicateWeeklyPlan(WeeklyPlanDuplicateRequire duplicateRequire);

    WeeklyPlan adminActive(WeeklyPlanActiveRequire activeRequire);
}