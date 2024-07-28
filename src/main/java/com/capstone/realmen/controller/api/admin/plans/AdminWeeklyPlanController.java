package com.capstone.realmen.controller.api.admin.plans;

import org.springframework.web.bind.annotation.RestController;

import com.capstone.realmen.controller.api.admin.plans.models.AdminDuplicatePlanRequest;
import com.capstone.realmen.controller.api.admin.plans.models.AdminWeeklyPlanRequest;
import com.capstone.realmen.service.plans.WeeklyPlanUseCaseService;
import com.capstone.realmen.service.plans.data.WeeklyPlanCreateRequire;
import com.capstone.realmen.service.plans.data.WeeklyPlanDuplicateRequire;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdminWeeklyPlanController implements IAdminWeeklyPlanAPI {
    @NonNull
    WeeklyPlanUseCaseService weeklyPlanUseCaseService;

    @Override
    public void save(AdminWeeklyPlanRequest weeklyPlanRequest) {
        weeklyPlanUseCaseService.adminCreateWeeklyPlanDraft(
            WeeklyPlanCreateRequire.of(weeklyPlanRequest)
        );
    }

    @Override
    public void duplicatePlanToNextWeek(@Valid AdminDuplicatePlanRequest duplicatePlanRequest) {
        weeklyPlanUseCaseService
            .adminDuplicatePlanToNextWeek(WeeklyPlanDuplicateRequire.of(duplicatePlanRequest));
    }

    @Override
    public void duplicatePlanToPresent(@Valid AdminDuplicatePlanRequest duplicatePlanRequest) {
        weeklyPlanUseCaseService
            .adminDuplicatePlanToNextWeek(WeeklyPlanDuplicateRequire.of(duplicatePlanRequest));
    }
}
