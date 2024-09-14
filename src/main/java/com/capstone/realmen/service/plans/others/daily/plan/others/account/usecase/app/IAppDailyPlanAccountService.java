package com.capstone.realmen.service.plans.others.daily.plan.others.account.usecase.app;

import org.springframework.data.domain.Page;

import com.capstone.realmen.common.request.PageRequestCustom;
import com.capstone.realmen.data.dto.plans.daily.account.DailyPlanAccount;
import com.capstone.realmen.service.plans.others.daily.plan.others.account.data.DailyPlanAccountSearchByField;
import com.capstone.realmen.service.plans.others.daily.plan.others.account.data.DailyPlanAccountSearchCriteria;

public interface IAppDailyPlanAccountService {
    DailyPlanAccount appFindById(DailyPlanAccountSearchByField searchByField);

    Page<DailyPlanAccount> appFindAll(DailyPlanAccountSearchCriteria searchCriteria, PageRequestCustom pageRequestCustom);
}
