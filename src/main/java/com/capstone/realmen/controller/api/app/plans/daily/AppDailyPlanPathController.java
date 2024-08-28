package com.capstone.realmen.controller.api.app.plans.daily;

import org.springframework.web.bind.annotation.RestController;

import com.capstone.realmen.common.response.ValueResponse;
import com.capstone.realmen.controller.api.app.plans.daily.models.DailyPlanResponse;
import com.capstone.realmen.controller.api.app.plans.daily.models.IDailyPlanModelMapper;
import com.capstone.realmen.data.dto.plans.daily.DailyPlan;
import com.capstone.realmen.service.plans.others.daily.plan.DailyPlanUseCaseService;
import com.capstone.realmen.service.plans.others.daily.plan.data.DailyPlanSearchByField;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AppDailyPlanPathController implements IDailyPlanPathAPI {
    @NonNull
    DailyPlanUseCaseService useCaseService;

    @NonNull
    IDailyPlanModelMapper modelMapper;

    @Override
    public ValueResponse<DailyPlanResponse> findById(Long dailyPlanId) {
        DailyPlanSearchByField searchByField = DailyPlanSearchByField.of(dailyPlanId);
        DailyPlan dailyPlan = useCaseService.appFindById(searchByField);
        return new ValueResponse<DailyPlanResponse>(modelMapper.toModel(dailyPlan));
    }
}
