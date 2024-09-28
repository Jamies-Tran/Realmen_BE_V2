package com.capstone.realmen.controller.api.admin.plans.daily;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.capstone.realmen.common.enums.EBookingStatus;
import com.capstone.realmen.common.enums.EDailyPlanStatus;
import com.capstone.realmen.common.request.PageRequestCustom;
import com.capstone.realmen.common.response.ListResponse;
import com.capstone.realmen.controller.api.admin.plans.daily.models.DailyPlanResponse;
import com.capstone.realmen.controller.api.admin.plans.daily.models.IAdminDailyPlanModelMapper;
import com.capstone.realmen.service.plans.others.daily.plan.DailyPlanUseCaseService;
import com.capstone.realmen.service.plans.others.daily.plan.data.DailyPlanSearchCriteria;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DailyPlanController implements IDailyPlanAPI{
    @NonNull
    DailyPlanUseCaseService useCaseService;

    @NonNull
    IAdminDailyPlanModelMapper modelMapper;

    @Override
    public ListResponse<DailyPlanResponse> findByDate(LocalDateTime date) {
        List<String> availableStatusCodes = EDailyPlanStatus.getAvailableStatus()
            .stream()
            .map(EDailyPlanStatus::getCode)
            .toList();
        DailyPlanSearchCriteria searchCriteria = DailyPlanSearchCriteria.builder()
            .date(date.toLocalDate())
            .statusCodes(availableStatusCodes)
            .build();
        List<DailyPlanResponse> responses = useCaseService
            .adminFindAll(searchCriteria, PageRequestCustom.unPaged())
            .getContent()
            .stream()
            .map(modelMapper::toModel)
            .toList();

        return new ListResponse<>(responses);
    }


}
