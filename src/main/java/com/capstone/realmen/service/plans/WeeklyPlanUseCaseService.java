package com.capstone.realmen.service.plans;

import org.springframework.stereotype.Service;

import com.capstone.realmen.service.plans.data.WeeklyPlanCreateRequire;
import com.capstone.realmen.service.plans.data.WeeklyPlanDuplicateRequire;
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

    @Override
    public void adminCreateWeeklyPlanDraft(WeeklyPlanCreateRequire createRequire) {
        weeklyPlanCommandService.create(createRequire);
    }

    @Override
    public void adminDuplicatePlanToNextWeek(WeeklyPlanDuplicateRequire weeklyPlanDuplicateRequire) {
        weeklyPlanCommandService.duplicate(weeklyPlanDuplicateRequire.nextWeek());
    }

    @Override
    public void adminDuplicatePlanToPresent(WeeklyPlanDuplicateRequire weeklyPlanDuplicateRequire) {
        weeklyPlanCommandService.duplicate(weeklyPlanDuplicateRequire.present());
    }

    
}
