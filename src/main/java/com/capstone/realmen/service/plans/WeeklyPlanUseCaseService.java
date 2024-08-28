package com.capstone.realmen.service.plans;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capstone.realmen.common.request.PageRequestCustom;
import com.capstone.realmen.data.dto.plans.weekly.WeeklyPlan;
import com.capstone.realmen.service.plans.data.WeeklyPlanActiveRequire;
import com.capstone.realmen.service.plans.data.WeeklyPlanCreateRequire;
import com.capstone.realmen.service.plans.data.WeeklyPlanDuplicateRequire;
import com.capstone.realmen.service.plans.data.WeeklyPlanSearchByField;
import com.capstone.realmen.service.plans.data.WeeklyPlanSearchCriteria;
import com.capstone.realmen.service.plans.usecase.IAdminWeeklyPlanService;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class WeeklyPlanUseCaseService implements IAdminWeeklyPlanService {
    @NonNull
    WeeklyPlanCommandService weeklyPlanCommandService;

    @NonNull
    WeeklyPlanQueryService weeklyPlanQueryService;

    @Override
    @Transactional
    public void adminCreateWeeklyPlanDraft(WeeklyPlanCreateRequire createRequire) {
        weeklyPlanCommandService.create(createRequire);
    }

    @Override
    @Transactional
    public void adminDuplicateWeeklyPlan(WeeklyPlanDuplicateRequire weeklyPlanDuplicateRequire) {
        weeklyPlanCommandService.duplicate(weeklyPlanDuplicateRequire);
    }

    @Override
    @Transactional
    public WeeklyPlan adminActive(WeeklyPlanActiveRequire activeRequire) {
        return weeklyPlanCommandService.active(activeRequire);
    }

    @Override
    public Page<WeeklyPlan> adminFindAll(WeeklyPlanSearchCriteria searchCriteria, PageRequestCustom pageRequestCustom) {
        return weeklyPlanQueryService.findAll(searchCriteria, pageRequestCustom);
    }

    @Override
    public WeeklyPlan adminFindById(WeeklyPlanSearchByField searchByField) {
        return weeklyPlanQueryService.findById(searchByField);
    }

}
