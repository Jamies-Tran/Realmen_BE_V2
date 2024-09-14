package com.capstone.realmen.controller.api.app.plans.daily.account;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.realmen.common.request.PageRequestCustom;
import com.capstone.realmen.common.response.PageImplResponse;
import com.capstone.realmen.controller.api.app.plans.daily.account.models.DailyPlanAccountResponse;
import com.capstone.realmen.controller.api.app.plans.daily.account.models.IDailyPlanAccountModelMapper;
import com.capstone.realmen.service.plans.others.daily.plan.others.account.DailyPlanAccountUseCaseService;
import com.capstone.realmen.service.plans.others.daily.plan.others.account.data.DailyPlanAccountSearchCriteria;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DailyPlanAccountController implements IDailyPlanAccountAPI {
    @NonNull
    DailyPlanAccountUseCaseService useCase;

    @NonNull
    IDailyPlanAccountModelMapper modelMapper;

    @Override
    public PageImplResponse<DailyPlanAccountResponse> findAll(
            Long dailyPlanId,
            List<String> professionalTypeCodes,
            Integer current,
            Integer pageSize) {
        DailyPlanAccountSearchCriteria searchCriteria = DailyPlanAccountSearchCriteria
                .of(dailyPlanId, professionalTypeCodes);
        PageRequestCustom pageRequestCustom = PageRequestCustom.of(current, pageSize);
        Page<DailyPlanAccountResponse> responses = useCase.appFindAll(searchCriteria, pageRequestCustom)
                .map(modelMapper::toModel);

        return new PageImplResponse<>(responses.getContent(), responses.getTotalElements(), responses.getTotalPages(),
                current, pageSize);
    }
}
