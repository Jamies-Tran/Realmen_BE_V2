package com.capstone.realmen.service.plans;

import java.util.List;

import org.springframework.stereotype.Service;

import com.capstone.realmen.common.enums.EWeeklyPlanStatus;
import com.capstone.realmen.common.request.PageRequestCustom;
import com.capstone.realmen.common.request.RequestContext;
import com.capstone.realmen.controller.handler.exceptions.NotFoundException;
import com.capstone.realmen.data.dto.account.Account;
import com.capstone.realmen.data.dto.plans.daily.DailyPlan;
import com.capstone.realmen.data.dto.plans.weekly.IWeeklyPlanMapper;
import com.capstone.realmen.data.dto.plans.weekly.WeeklyPlan;
import com.capstone.realmen.data.dto.shop.service.ShopService;
import com.capstone.realmen.repository.database.plans.weekly.IWeeklyPlanRepository;
import com.capstone.realmen.repository.database.plans.weekly.WeeklyPlanEntity;
import com.capstone.realmen.service.account.AccountQueryService;
import com.capstone.realmen.service.account.data.AccountSearchCriteria;
import com.capstone.realmen.service.plans.data.WeeklyPlanActiveRequire;
import com.capstone.realmen.service.plans.data.WeeklyPlanCreateRequire;
import com.capstone.realmen.service.plans.data.WeeklyPlanDuplicateRequire;
import com.capstone.realmen.service.plans.others.daily.plan.DailyPlanCommandService;
import com.capstone.realmen.service.plans.others.daily.plan.DailyPlanQueryService;
import com.capstone.realmen.service.plans.others.daily.plan.data.DailyPlanActiveRequire;
import com.capstone.realmen.service.plans.others.daily.plan.data.DailyPlanCreateRequire;
import com.capstone.realmen.service.plans.others.daily.plan.data.DailyPlanDuplicateRequire;
import com.capstone.realmen.service.shop.service.ShopServiceQueryService;
import com.capstone.realmen.service.shop.service.data.ShopServiceSearchCriteria;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class WeeklyPlanCommandService {
        @NonNull
        IWeeklyPlanRepository weeklyPlanRepository;

        @NonNull
        IWeeklyPlanMapper weeklyPlanMapper;

        @NonNull
        AccountQueryService accountQueryService;

        @NonNull
        ShopServiceQueryService shopServiceQueryService;

        @NonNull
        DailyPlanCommandService dailyPlanCommandService;

        @NonNull
        DailyPlanQueryService dailyPlanQueryService;

        @NonNull
        RequestContext requestContext;

        public void create(WeeklyPlanCreateRequire createRequire) {
                Long branchId = requestContext.getAccount().branchId();
                WeeklyPlan newWeeklyPlan = WeeklyPlan.builder()
                                .branchId(branchId)
                                .weeklyPlanStatusCode(EWeeklyPlanStatus.DRAFT.getCode())
                                .weeklyPlanStatusName(EWeeklyPlanStatus.DRAFT.getName())
                                .build();
                WeeklyPlanEntity saveWeeklyPlan = weeklyPlanRepository.save(
                                weeklyPlanMapper.toEntity(newWeeklyPlan)
                                                .setAudit(requestContext.auditCreate()));
                List<Long> accountIds = accountQueryService
                                .findAll(AccountSearchCriteria.filterStaffOnBranch(branchId),
                                                PageRequestCustom.unPaged())
                                .map(Account::accountId)
                                .toList();
                List<Long> serviceIds = shopServiceQueryService
                                .findAll(ShopServiceSearchCriteria.filterBranch(branchId), PageRequestCustom.unPaged())
                                .map(ShopService::shopServiceId)
                                .toList();
                dailyPlanCommandService.create(
                                DailyPlanCreateRequire.builder()
                                                .weeklyPlanId(saveWeeklyPlan.getWeeklyPlanId())
                                                .accountIds(accountIds)
                                                .serviceIds(serviceIds)
                                                .pickUpDate(createRequire.pickUpDate())
                                                .build());
        }

        public void duplicate(WeeklyPlanDuplicateRequire duplicateRequire) {
                WeeklyPlanEntity foundWeeklyPlan = getWeeklyPlanById(duplicateRequire.weeklyPlanId());
                WeeklyPlan newWeeklyPlan = WeeklyPlan.duplicate(weeklyPlanMapper.toDto(foundWeeklyPlan));
                WeeklyPlanEntity savedWeeklyPlan = weeklyPlanRepository.save(weeklyPlanMapper.toEntity(newWeeklyPlan)
                                .setAudit(requestContext.auditCreate()))
                                .setAudit(requestContext.auditCreate());

                DailyPlanDuplicateRequire dPlanDuplicateRequire = DailyPlanDuplicateRequire
                                .of(foundWeeklyPlan.getWeeklyPlanId(), savedWeeklyPlan.getWeeklyPlanId());
                dailyPlanCommandService.duplicateByWeeklyPlan(dPlanDuplicateRequire);
        }

        public WeeklyPlan active(WeeklyPlanActiveRequire activeRequire) {
                WeeklyPlanEntity foundWeeklyPlan = getWeeklyPlanById(activeRequire.weeklyPlanId());
                DailyPlanActiveRequire dActiveRequire = DailyPlanActiveRequire.of(foundWeeklyPlan.getWeeklyPlanId());
                List<DailyPlan> dailyPlans = dailyPlanCommandService.active(dActiveRequire);
                WeeklyPlanEntity activateWeeklyPlan = foundWeeklyPlan
                        .withWeeklyPlanStatusCode(EWeeklyPlanStatus.PROCESSING.getCode())
                        .withWeeklyPlanStatusName(EWeeklyPlanStatus.PROCESSING.getName());
                WeeklyPlanEntity updateWeeklyPlan = weeklyPlanRepository.save(activateWeeklyPlan);
                return weeklyPlanMapper.toDto(updateWeeklyPlan)
                        .withDailyPlans(dailyPlans); 
        }

        private WeeklyPlanEntity getWeeklyPlanById(Long weeklyPlanId) {
                return weeklyPlanRepository
                                .findById(weeklyPlanId)
                                .orElseThrow(() -> new NotFoundException("Không tìm thấy kế hoạch tuần"));
        }
}
