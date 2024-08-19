package com.capstone.realmen.service.plans.others.daily.plan.usecase;

import java.util.List;

import com.capstone.realmen.data.dto.plans.daily.DailyPlan;

public interface IAppDailyPlanService {
    List<DailyPlan> appFindByUserLogin();
}
