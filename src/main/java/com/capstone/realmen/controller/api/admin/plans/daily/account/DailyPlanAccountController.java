package com.capstone.realmen.controller.api.admin.plans.daily.account;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.capstone.realmen.common.request.PageRequestCustom;
import com.capstone.realmen.common.response.ListResponse;
import com.capstone.realmen.controller.api.admin.plans.daily.account.models.IDailyPlanAccountModelMapper;
import com.capstone.realmen.controller.api.admin.plans.daily.models.DailyPlanAccountResponse;
import com.capstone.realmen.service.plans.others.daily.plan.others.account.DailyPlanAccountUseCaseService;
import com.capstone.realmen.service.plans.others.daily.plan.others.account.data.DailyPlanAccountSearchCriteria;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController("AdminDailyPlanAccountController")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DailyPlanAccountController implements IDailyPlanAccountAPI {
    @NonNull
    DailyPlanAccountUseCaseService useCaseService;

    @NonNull
    IDailyPlanAccountModelMapper modelMapper;

    @Override
    public ListResponse<DailyPlanAccountResponse> findAll(Long dailyPlanId, String staffName,
            List<String> serviceAssignmentCodes) {
        DailyPlanAccountSearchCriteria searchCriteria = DailyPlanAccountSearchCriteria.builder()
                .dailyPlanId(dailyPlanId)
                .search(staffName)
                .professinalTypeCodes(serviceAssignmentCodes)
                .build();
        List<DailyPlanAccountResponse> responses = useCaseService
                .adminFindAll(searchCriteria, PageRequestCustom.unPaged())
                .stream()
                .map(modelMapper::toModel)
                .toList();

        return new ListResponse<>(responses);
    }
}
