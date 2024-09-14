package com.capstone.realmen.controller.api.app.plans.daily.account;

import org.springframework.web.bind.annotation.RestController;

import com.capstone.realmen.common.response.ValueResponse;
import com.capstone.realmen.controller.api.app.plans.daily.account.models.DailyPlanAccountResponse;
import com.capstone.realmen.controller.api.app.plans.daily.account.models.IDailyPlanAccountModelMapper;
import com.capstone.realmen.data.dto.plans.daily.account.DailyPlanAccount;
import com.capstone.realmen.service.plans.others.daily.plan.others.account.DailyPlanAccountUseCaseService;
import com.capstone.realmen.service.plans.others.daily.plan.others.account.data.DailyPlanAccountSearchByField;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DailyPlanAccountPathController implements IDailyPlanAccountPathAPI {
    @NonNull
    DailyPlanAccountUseCaseService useCase;

    @NonNull
    IDailyPlanAccountModelMapper modelMapper;

    @Override
    public ValueResponse<DailyPlanAccountResponse> findById(Long dailyPlanAccountId) {
        DailyPlanAccountSearchByField searchByField = DailyPlanAccountSearchByField
                .ofDailyPlanAccountId(dailyPlanAccountId);
        DailyPlanAccount foundAccount = useCase.appFindById(searchByField);

        return new ValueResponse<>(modelMapper.toModel(foundAccount));
    }
}
