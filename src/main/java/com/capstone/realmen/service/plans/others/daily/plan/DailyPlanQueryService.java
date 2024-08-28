package com.capstone.realmen.service.plans.others.daily.plan;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.capstone.realmen.common.request.PageRequestCustom;
import com.capstone.realmen.common.request.RequestContext;
import com.capstone.realmen.controller.handler.exceptions.NotFoundException;
import com.capstone.realmen.data.dto.plans.daily.DailyPlan;
import com.capstone.realmen.data.dto.plans.daily.IDailyPlanMapper;
import com.capstone.realmen.data.dto.plans.daily.account.DailyPlanAccount;
import com.capstone.realmen.data.dto.plans.daily.service.DailyPlanService;
import com.capstone.realmen.repository.database.plans.daily.DailyPlanEntity;
import com.capstone.realmen.repository.database.plans.daily.IDailyPlanRepository;
import com.capstone.realmen.service.plans.others.daily.plan.data.DailyPlanSearchByField;
import com.capstone.realmen.service.plans.others.daily.plan.data.DailyPlanSearchCriteria;
import com.capstone.realmen.service.plans.others.daily.plan.others.account.DailyPlanAccountQueryService;
import com.capstone.realmen.service.plans.others.daily.plan.others.account.data.DailyPlanAccountSearchByField;
import com.capstone.realmen.service.plans.others.daily.plan.others.service.DailyPlanServiceQueryService;
import com.capstone.realmen.service.plans.others.daily.plan.others.service.data.DailyPlanServiceSearchByField;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DailyPlanQueryService {
    @NonNull
    IDailyPlanRepository dailyPlanRepository;

    @NonNull
    IDailyPlanMapper dailyPlanMapper;

    @NonNull
    DailyPlanServiceQueryService dailyPlanServiceQueryService;

    @NonNull
    DailyPlanAccountQueryService dailyPlanAccountQueryService;

    @NonNull
    RequestContext requestContext;

    public List<DailyPlan> findAll(DailyPlanSearchCriteria searchCriteria) {
        return dailyPlanRepository.findAllByWeeklyPlanId(searchCriteria.weeklyPlanId())
                .stream()
                .map(dailyPlanMapper::toDto)
                .toList();
    }

    public Page<DailyPlan> findAll(DailyPlanSearchCriteria searchCriteria, PageRequestCustom pageRequestCustom) {

        return dailyPlanRepository.findAll(searchCriteria, pageRequestCustom.pageRequest())
                .map(dailyPlanMapper::toDto);
    }

    public DailyPlan findById(DailyPlanSearchByField searchByField) {
        DailyPlanEntity dailyPlan = dailyPlanRepository.findById(searchByField.dailyPlanId())
                .orElseThrow(NotFoundException::new);

        DailyPlanAccountSearchByField aSearchByField = DailyPlanAccountSearchByField
                .of(searchByField.dailyPlanId());
        DailyPlanServiceSearchByField sSearchByField = DailyPlanServiceSearchByField
                .of(searchByField.dailyPlanId());
        List<DailyPlanAccount> accounts = dailyPlanAccountQueryService.findAllBy(aSearchByField);
        List<DailyPlanService> services = dailyPlanServiceQueryService.findAllBy(sSearchByField);

        return dailyPlanMapper.toDto(dailyPlan)
                .withDailyPlanAccounts(accounts)
                .withDailyPlanServices(services);
    }

    public List<DailyPlan> findByWeeklyPlanIds(DailyPlanSearchByField searchByField) {
        return dailyPlanRepository.findByWeeklyPlanIds(searchByField)
                .stream()
                .map(dailyPlanMapper::toDto)
                .toList();
    }
}
