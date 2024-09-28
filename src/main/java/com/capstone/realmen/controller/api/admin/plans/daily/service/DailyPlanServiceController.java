package com.capstone.realmen.controller.api.admin.plans.daily.service;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.capstone.realmen.common.request.PageRequestCustom;
import com.capstone.realmen.common.response.ListResponse;
import com.capstone.realmen.controller.api.admin.plans.daily.models.DailyPlanServiceResponse;
import com.capstone.realmen.controller.api.admin.plans.daily.service.models.IDailyPlanServiceModelMapper;
import com.capstone.realmen.service.plans.others.daily.plan.others.service.DailyPlanServiceUseCaseService;
import com.capstone.realmen.service.plans.others.daily.plan.others.service.data.DailyPlanServiceSearchCriteria;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController("AdminDailyPlanService")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DailyPlanServiceController implements IDailyPlanServiceAPI {
    @NonNull
    DailyPlanServiceUseCaseService useCaseService;

    @NonNull
    IDailyPlanServiceModelMapper modelMapper;

    @Override
    public ListResponse<DailyPlanServiceResponse> findByDailyPlanId(Long dailyPlanId, String serviceName) {
        DailyPlanServiceSearchCriteria searchCriteria = DailyPlanServiceSearchCriteria.builder()
                .dailyPlanId(dailyPlanId)
                .search(serviceName)
                .build();

        List<DailyPlanServiceResponse> responses = useCaseService
                .adminFindAll(searchCriteria, PageRequestCustom.unPaged())
                .getContent()
                .stream()
                .map(modelMapper::toModel)
                .toList();

        return new ListResponse<>(responses);
    }

}
