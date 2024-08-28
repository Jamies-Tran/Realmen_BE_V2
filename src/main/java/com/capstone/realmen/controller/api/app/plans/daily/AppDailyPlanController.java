package com.capstone.realmen.controller.api.app.plans.daily;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.realmen.common.request.PageRequestCustom;
import com.capstone.realmen.common.response.ListResponse;
import com.capstone.realmen.controller.api.app.plans.daily.models.DailyPlanResponse;
import com.capstone.realmen.controller.api.app.plans.daily.models.IDailyPlanModelMapper;
import com.capstone.realmen.data.dto.plans.daily.DailyPlan;
import com.capstone.realmen.service.plans.others.daily.plan.DailyPlanUseCaseService;
import com.capstone.realmen.service.plans.others.daily.plan.data.DailyPlanSearchCriteria;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AppDailyPlanController implements IDailyPlanAPI{
    @NonNull
    DailyPlanUseCaseService useCaseService;

    @NonNull
    IDailyPlanModelMapper modelMapper;

    @Override
    public ListResponse<DailyPlanResponse> findAll(List<LocalDateTime> timeRange, Long accountId) {
        DailyPlanSearchCriteria searchCriteria = DailyPlanSearchCriteria.of(timeRange, accountId);
        PageRequestCustom pageRequestCustom = PageRequestCustom.unPaged();
        Page<DailyPlan> dailyPlans = useCaseService.appFindAll(searchCriteria, pageRequestCustom);
        List<DailyPlanResponse> responses = dailyPlans.getContent().stream()
            .map(modelMapper::toModel)
            .toList();
        
        return new ListResponse<>(responses);
    }

    @Override
    public ListResponse<DailyPlanResponse> findAllForStaff(List<LocalDateTime> timeRange) {
        DailyPlanSearchCriteria searchCriteria = DailyPlanSearchCriteria.of(timeRange);
        PageRequestCustom pageRequestCustom = PageRequestCustom.unPaged();
        Page<DailyPlan> dailyPlans = useCaseService.appFindForStaff(searchCriteria, pageRequestCustom);
        List<DailyPlanResponse> responses = dailyPlans.getContent().stream()
            .map(modelMapper::toModel)
            .toList();
        
        return new ListResponse<>(responses);
    }

}
