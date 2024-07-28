package com.capstone.realmen.service.plans.usecase;

import com.capstone.realmen.service.plans.data.WeeklyPlanCreateRequire;
import com.capstone.realmen.service.plans.data.WeeklyPlanDuplicateRequire;

public interface IAdminWeeklyPlanService {
    void adminCreateWeeklyPlanDraft(WeeklyPlanCreateRequire createRequire);

    void adminDuplicatePlanToNextWeek(WeeklyPlanDuplicateRequire weeklyPlanDuplicateRequire);

    void adminDuplicatePlanToPresent(WeeklyPlanDuplicateRequire weeklyPlanDuplicateRequire);
}