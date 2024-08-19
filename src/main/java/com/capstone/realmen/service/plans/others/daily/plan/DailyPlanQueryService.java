package com.capstone.realmen.service.plans.others.daily.plan;

import java.util.List;

import org.springframework.stereotype.Service;

import com.capstone.realmen.common.request.RequestContext;
import com.capstone.realmen.data.dto.account.Account;
import com.capstone.realmen.data.dto.plans.daily.DailyPlan;
import com.capstone.realmen.data.dto.plans.daily.IDailyPlanMapper;
import com.capstone.realmen.repository.database.plans.daily.IDailyPlanRepository;
import com.capstone.realmen.service.plans.others.daily.plan.data.DailyPlanSearchCriteria;
import com.capstone.realmen.service.plans.others.daily.plan.others.service.DailyPlanServiceQueryService;

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
    RequestContext requestContext;

    public List<DailyPlan> findAll(DailyPlanSearchCriteria searchCriteria) {
        return dailyPlanRepository.findAllByWeeklyPlanId(searchCriteria.weeklyPlanId())
                .stream()
                .map(dailyPlanMapper::toDto)
                .toList();
    }

    public List<DailyPlan> findByUserLogin() {
        Account userLogin = requestContext.getAccount();
        Long staffId = userLogin.accountId();
        Long branchId = userLogin.branchId();
        return dailyPlanRepository.findAllByStaffIdAndBranchId(staffId, branchId)
                .stream()
                .map(dailyPlanMapper::toDto)
                .toList();
    }
}
