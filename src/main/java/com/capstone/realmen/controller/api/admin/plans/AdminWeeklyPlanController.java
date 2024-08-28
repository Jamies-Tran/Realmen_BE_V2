package com.capstone.realmen.controller.api.admin.plans;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.realmen.common.request.CustomSort;
import com.capstone.realmen.common.request.PageRequestCustom;
import com.capstone.realmen.common.response.PageImplResponse;
import com.capstone.realmen.common.util.DateTimeHandler;
import com.capstone.realmen.controller.api.admin.plans.models.AdminWeeklyPlanRequest;
import com.capstone.realmen.controller.api.admin.plans.models.AdminWeeklyPlanResponse;
import com.capstone.realmen.controller.api.admin.plans.models.IAdminWeeklyPlanModelMapper;
import com.capstone.realmen.service.plans.WeeklyPlanUseCaseService;
import com.capstone.realmen.service.plans.data.WeeklyPlanCreateRequire;
import com.capstone.realmen.service.plans.data.WeeklyPlanSearchCriteria;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdminWeeklyPlanController implements IAdminWeeklyPlanAPI {
    @NonNull
    WeeklyPlanUseCaseService weeklyPlanUseCaseService;

    @NonNull
    IAdminWeeklyPlanModelMapper modelMapper;

    @Override
    public void save(AdminWeeklyPlanRequest weeklyPlanRequest) {
        weeklyPlanUseCaseService.adminCreateWeeklyPlanDraft(
                WeeklyPlanCreateRequire.of(weeklyPlanRequest));
    }

    @Override
    public PageImplResponse<AdminWeeklyPlanResponse> findAll(
            String search,
            List<LocalDateTime> timeRange,
            String sorter, Integer current, Integer pageSize) {
        WeeklyPlanSearchCriteria searchCriteria = WeeklyPlanSearchCriteria.of(search,
                DateTimeHandler.validateTimeRange(timeRange));
        PageRequestCustom pageRequestCustom = PageRequestCustom.of(current, pageSize, CustomSort.of(sorter));
        Page<AdminWeeklyPlanResponse> responses = weeklyPlanUseCaseService
                .adminFindAll(searchCriteria, pageRequestCustom)
                .map(modelMapper::toModel);

        return new PageImplResponse<>(responses.getContent(), responses.getTotalElements(), responses.getTotalPages(),
                current, pageSize);
    }

}
