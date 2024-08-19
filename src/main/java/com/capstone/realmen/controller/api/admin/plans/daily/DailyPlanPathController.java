package com.capstone.realmen.controller.api.admin.plans.daily;

import org.springframework.web.bind.annotation.RestController;

import com.capstone.realmen.common.response.ValueResponse;
import com.capstone.realmen.controller.api.admin.plans.daily.models.DailyPlanRequest;
import com.capstone.realmen.controller.api.admin.plans.daily.models.DailyPlanResponse;
import com.capstone.realmen.controller.api.admin.plans.daily.models.IAdminDailyPlanModelMapper;
import com.capstone.realmen.data.dto.plans.daily.DailyPlan;
import com.capstone.realmen.service.plans.others.daily.plan.DailyPlanUseCaseService;
import com.capstone.realmen.service.plans.others.daily.plan.data.DailyPlanUpdateRequire;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DailyPlanPathController implements IDailyPlanPathAPI {
    @NonNull
    DailyPlanUseCaseService useCaseService;

    @NonNull
    IAdminDailyPlanModelMapper modelMapper;

    @Override
    public ValueResponse<DailyPlanResponse> update(Long dailyPlanId, DailyPlanRequest request) {
        DailyPlanUpdateRequire updateRequire = DailyPlanUpdateRequire
                .of(dailyPlanId, request);
        DailyPlan dailyPlan = useCaseService.adminUpdate(updateRequire);
        return new ValueResponse<DailyPlanResponse>(modelMapper.toModel(dailyPlan));
    }

}
