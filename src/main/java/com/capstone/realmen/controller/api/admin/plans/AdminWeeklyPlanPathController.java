package com.capstone.realmen.controller.api.admin.plans;

import org.springframework.web.bind.annotation.RestController;

import com.capstone.realmen.common.response.ValueResponse;
import com.capstone.realmen.controller.api.admin.plans.models.AdminWeeklyPlanResponse;
import com.capstone.realmen.controller.api.admin.plans.models.IAdminWeeklyPlanModelMapper;
import com.capstone.realmen.data.dto.plans.weekly.WeeklyPlan;
import com.capstone.realmen.service.plans.WeeklyPlanUseCaseService;
import com.capstone.realmen.service.plans.data.WeeklyPlanActiveRequire;
import com.capstone.realmen.service.plans.data.WeeklyPlanDuplicateRequire;
import com.capstone.realmen.service.plans.data.WeeklyPlanSearchByField;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdminWeeklyPlanPathController implements IAdminWeeklyPlanPathAPI {
    @NonNull
    WeeklyPlanUseCaseService weeklyPlanUseCaseService;

    @NonNull
    IAdminWeeklyPlanModelMapper weeklyPlanModelMapper;

    @Override
    public void duplicateWeeklyPlan(Long weeklyPlanId) {
        WeeklyPlanDuplicateRequire duplicateRequire = WeeklyPlanDuplicateRequire.of(weeklyPlanId);
        weeklyPlanUseCaseService.adminDuplicateWeeklyPlan(duplicateRequire);
    }

    @Override
    public ValueResponse<AdminWeeklyPlanResponse> active(Long weeklyPlanId) {
        WeeklyPlanActiveRequire activeRequire = WeeklyPlanActiveRequire.of(weeklyPlanId);
        WeeklyPlan activatedWeeklyPlan = weeklyPlanUseCaseService.adminActive(activeRequire);

        return new ValueResponse<>(weeklyPlanModelMapper.toModel(activatedWeeklyPlan));
    }

    @Override
    public ValueResponse<AdminWeeklyPlanResponse> findById(Long weeklyPlanId) {
        WeeklyPlanSearchByField searchByField = WeeklyPlanSearchByField.of(weeklyPlanId);
        WeeklyPlan foundWeeklyPlan = weeklyPlanUseCaseService.adminFindById(searchByField);
        return new ValueResponse<AdminWeeklyPlanResponse>(weeklyPlanModelMapper.toModel(foundWeeklyPlan));
    }
}
