package com.capstone.realmen.service.plans.others.daily.plan;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capstone.realmen.data.dto.plans.daily.DailyPlan;
import com.capstone.realmen.service.plans.others.daily.plan.data.DailyPlanUpdateRequire;
import com.capstone.realmen.service.plans.others.daily.plan.usecase.IAdminDailyPlanService;
import com.capstone.realmen.service.plans.others.daily.plan.usecase.IAppDailyPlanService;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DailyPlanUseCaseService implements IAdminDailyPlanService, IAppDailyPlanService {
    @NonNull
    DailyPlanCommandService dailyPlanCommandService;

    @NonNull
    DailyPlanQueryService dailyPlanQueryService;

    @Override
    @Transactional
    public DailyPlan adminUpdate(DailyPlanUpdateRequire updateRequire) {
        return dailyPlanCommandService.update(updateRequire);
    }

    @Override
    public List<DailyPlan> appFindByUserLogin() {
        return dailyPlanQueryService.findByUserLogin();
    }

}
