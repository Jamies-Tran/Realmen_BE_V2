package com.capstone.realmen.service.plans.others.daily.plan.usecase;

import com.capstone.realmen.data.dto.plans.daily.DailyPlan;
import com.capstone.realmen.service.plans.others.daily.plan.data.DailyPlanUpdateRequire;

public interface IAdminDailyPlanService {
    DailyPlan adminUpdate(DailyPlanUpdateRequire updateRequire);
}
