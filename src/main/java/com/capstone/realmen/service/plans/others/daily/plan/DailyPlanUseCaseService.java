package com.capstone.realmen.service.plans.others.daily.plan;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capstone.realmen.common.request.PageRequestCustom;
import com.capstone.realmen.common.request.RequestContext;
import com.capstone.realmen.data.dto.account.Account;
import com.capstone.realmen.data.dto.plans.daily.DailyPlan;
import com.capstone.realmen.service.plans.others.daily.plan.data.DailyPlanSearchByField;
import com.capstone.realmen.service.plans.others.daily.plan.data.DailyPlanSearchCriteria;
import com.capstone.realmen.service.plans.others.daily.plan.data.DailyPlanUpdateRequire;
import com.capstone.realmen.service.plans.others.daily.plan.usecase.IAdminDailyPlanService;
import com.capstone.realmen.service.plans.others.daily.plan.usecase.IAppDailyPlanService;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DailyPlanUseCaseService implements IAdminDailyPlanService, IAppDailyPlanService {
    @NonNull
    DailyPlanCommandService dailyPlanCommandService;

    @NonNull
    DailyPlanQueryService dailyPlanQueryService;

    @NonNull
    RequestContext requestContext;

    @Override
    @Transactional
    public DailyPlan adminUpdate(DailyPlanUpdateRequire updateRequire) {
        return dailyPlanCommandService.update(updateRequire);
    }

    @Override
    public Page<DailyPlan> appFindAll(DailyPlanSearchCriteria searchCriteria, PageRequestCustom pageRequestCustom) {
        return dailyPlanQueryService.findAll(searchCriteria, pageRequestCustom);
    }

    @Override
    public Page<DailyPlan> appFindForStaff(DailyPlanSearchCriteria searchCriteria,
            PageRequestCustom pageRequestCustom) {
        Account account = requestContext.getAccount();
        return dailyPlanQueryService.findAll(searchCriteria.withAccountId(account.accountId()), pageRequestCustom);
    }

    @Override
    public DailyPlan appFindById(DailyPlanSearchByField searchByField) {
        return dailyPlanQueryService.findById(searchByField);
    }

    @Override
    public DailyPlan adminFindById(DailyPlanSearchByField searchByField) {
        return dailyPlanQueryService.findById(searchByField);
    }

}
