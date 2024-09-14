package com.capstone.realmen.controller.api.app.plans.daily.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.realmen.common.request.PageRequestCustom;
import com.capstone.realmen.common.response.PageImplResponse;
import com.capstone.realmen.controller.api.app.plans.daily.services.models.DailyPlanServiceResponse;
import com.capstone.realmen.controller.api.app.plans.daily.services.models.IDailyPlanServiceModelMapper;
import com.capstone.realmen.service.plans.others.daily.plan.others.service.DailyPlanServiceUseCaseService;
import com.capstone.realmen.service.plans.others.daily.plan.others.service.data.DailyPlanServiceSearchCriteria;

import jakarta.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DailyPlanServiceController implements IDailyPlanServiceAPI {
    @NonNull
    DailyPlanServiceUseCaseService useCase;

    @NonNull
    IDailyPlanServiceModelMapper modelMapper;

    @Override
    public PageImplResponse<DailyPlanServiceResponse> findAll(Long branchId, Long dailyPlanId,List<String> serviceAssignmentCodes,
            @Min(1) Integer current, Integer pageSize) {
        DailyPlanServiceSearchCriteria searchCriteria = DailyPlanServiceSearchCriteria.of(serviceAssignmentCodes, branchId, dailyPlanId);
        PageRequestCustom pageRequestCustom = PageRequestCustom.of(current, pageSize);
        Page<DailyPlanServiceResponse> responses = useCase.appFindAll(searchCriteria, pageRequestCustom)
                .map(modelMapper::toModel);
        
        return new PageImplResponse<>(responses.getContent(), responses.getTotalElements(), responses.getTotalPages(), current, pageSize);
    }
}
