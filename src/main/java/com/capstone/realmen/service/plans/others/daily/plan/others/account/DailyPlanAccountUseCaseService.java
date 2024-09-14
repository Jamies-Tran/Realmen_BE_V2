package com.capstone.realmen.service.plans.others.daily.plan.others.account;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.capstone.realmen.common.request.PageRequestCustom;
import com.capstone.realmen.data.dto.plans.daily.account.DailyPlanAccount;
import com.capstone.realmen.service.plans.others.daily.plan.others.account.data.DailyPlanAccountSearchByField;
import com.capstone.realmen.service.plans.others.daily.plan.others.account.data.DailyPlanAccountSearchCriteria;
import com.capstone.realmen.service.plans.others.daily.plan.others.account.usecase.app.IAppDailyPlanAccountService;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DailyPlanAccountUseCaseService implements IAppDailyPlanAccountService {
    @NonNull
    DailyPlanAccountQueryService query;

    @Override
    public DailyPlanAccount appFindById(DailyPlanAccountSearchByField searchByField) {
        return query.findById(searchByField);
    }

    @Override
    public Page<DailyPlanAccount> appFindAll(DailyPlanAccountSearchCriteria searchCriteria,
            PageRequestCustom pageRequestCustom) {
        return query.findAll(searchCriteria, pageRequestCustom);
    }
}
