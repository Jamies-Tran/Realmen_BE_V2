package com.capstone.realmen.service.plans.others.daily.plan;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.capstone.realmen.common.enums.EDailyPlanStatus;
import com.capstone.realmen.common.request.RequestContext;
import com.capstone.realmen.controller.handler.exceptions.NotFoundException;
import com.capstone.realmen.data.dto.plans.daily.DailyPlan;
import com.capstone.realmen.data.dto.plans.daily.IDailyPlanMapper;
import com.capstone.realmen.data.dto.plans.daily.account.DailyPlanAccount;
import com.capstone.realmen.data.dto.plans.daily.service.DailyPlanService;
import com.capstone.realmen.repository.database.plans.daily.DailyPlanEntity;
import com.capstone.realmen.repository.database.plans.daily.IDailyPlanRepository;
import com.capstone.realmen.service.plans.others.daily.plan.data.DailyPlanCreateRequire;
import com.capstone.realmen.service.plans.others.daily.plan.data.DailyPlanDuplicateRequire;
import com.capstone.realmen.service.plans.others.daily.plan.data.DailyPlanUpdateRequire;
import com.capstone.realmen.service.plans.others.daily.plan.helpers.DailyPlanHelpers;
import com.capstone.realmen.service.plans.others.daily.plan.others.account.DailyPlanAccountCommandService;
import com.capstone.realmen.service.plans.others.daily.plan.others.account.DailyPlanAccountQueryService;
import com.capstone.realmen.service.plans.others.daily.plan.others.account.data.DailyPlanAccountCreateRequire;
import com.capstone.realmen.service.plans.others.daily.plan.others.account.data.DailyPlanAccountSearchByField;
import com.capstone.realmen.service.plans.others.daily.plan.others.account.data.DailyPlanAccountUpdateRequire;
import com.capstone.realmen.service.plans.others.daily.plan.others.service.DailyPlanServiceCommandService;
import com.capstone.realmen.service.plans.others.daily.plan.others.service.DailyPlanServiceQueryService;
import com.capstone.realmen.service.plans.others.daily.plan.others.service.data.DailyPlanServiceCreateRequire;
import com.capstone.realmen.service.plans.others.daily.plan.others.service.data.DailyPlanServiceSearchByField;
import com.capstone.realmen.service.plans.others.daily.plan.others.service.data.DailyPlanServiceUpdateRequire;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DailyPlanCommandService extends DailyPlanHelpers {
        @NonNull
        IDailyPlanRepository dailyPlanRepository;

        @NonNull
        DailyPlanAccountCommandService dailyPlanAccountCommandService;

        @NonNull
        DailyPlanAccountQueryService dailyPlanAccountQueryService;

        @NonNull
        DailyPlanServiceCommandService dailyPlanServiceCommandService;

        @NonNull
        DailyPlanServiceQueryService dailyPlanServiceQueryService;

        @NonNull
        IDailyPlanMapper dailyPlanMapper;

        @NonNull
        RequestContext requestContext;

        public void create(DailyPlanCreateRequire createRequire) {
                List<LocalDateTime> dailyPlanDateList = getDailyPlanDateList(createRequire.pickUpDate());
                List<DailyPlan> dailyPlan = dailyPlanDateList.stream()
                                .map(dailyPlanDate -> DailyPlan.builder()
                                                .weeklyPlanId(createRequire.weeklyPlanId())
                                                .date(dailyPlanDate)
                                                .dailyPlanStatusCode(EDailyPlanStatus.verify(dailyPlanDate).getCode())
                                                .dailyPlanStatusName(EDailyPlanStatus.verify(dailyPlanDate).getName())
                                                .build())
                                .toList();
                List<DailyPlanEntity> savedDailyPlan = dailyPlanRepository
                                .saveAll(dailyPlan.stream()
                                                .map(plan -> dailyPlanMapper.toEntity(plan)
                                                                .setAudit(requestContext.auditCreate()))
                                                .toList());
                List<Long> savedDailyPlanIds = savedDailyPlan.stream()
                                .map(DailyPlanEntity::getDailyPlanId)
                                .toList();
                dailyPlanAccountCommandService.createList(
                                DailyPlanAccountCreateRequire.builder()
                                                .dailyPlanIds(savedDailyPlanIds)
                                                .accountIds(createRequire.accountIds())
                                                .build());
                dailyPlanServiceCommandService.createList(DailyPlanServiceCreateRequire.builder()
                                .dailyPlanIds(savedDailyPlanIds)
                                .shopServiceIds(createRequire.serviceIds())
                                .build());
        }

        public void duplicateByWeeklyPlan(DailyPlanDuplicateRequire duplicateRequire) {
                List<DailyPlanEntity> foundDailyPlans = dailyPlanRepository
                                .findAllByWeeklyPlanId(duplicateRequire.oldWeeklyPlanId());

                switch (duplicateRequire.duplicateType()) {
                        case TO_NEXT_WEEK -> {
                                LocalDateTime lastWeeklyPlanDate = foundDailyPlans.stream()
                                                .sorted(Comparator.comparing(DailyPlanEntity::getDate).reversed())
                                                .map(DailyPlanEntity::getDate)
                                                .findFirst()
                                                .orElse(LocalDateTime.now());

                                foundDailyPlans.forEach(dailyPlan -> {
                                        duplicateDailyPlan(dailyPlan, lastWeeklyPlanDate.plusDays(1),
                                                        duplicateRequire.newWeeklyPlanId());
                                });

                        }
                        case TO_PRESENT -> {
                                foundDailyPlans.forEach(dailyPlan -> {
                                        duplicateDailyPlan(dailyPlan, LocalDateTime.now(),
                                                        duplicateRequire.newWeeklyPlanId());
                                });
                        }
                }

        }

        private void duplicateDailyPlan(
                        DailyPlanEntity oldDailyPlan,
                        LocalDateTime nextWeeklyDate,
                        Long newWeeklyPlanId) {
                LocalDateTime equivalentWeeklyDate = getEquivalentWeeklyDate(oldDailyPlan.getDate(), nextWeeklyDate);
                DailyPlan newDailyPlan = DailyPlan
                                .duplicate(
                                        dailyPlanMapper.toDto(oldDailyPlan)
                                                .withDate(equivalentWeeklyDate)
                                                .withWeeklyPlanId(newWeeklyPlanId)
                                                .withDailyPlanStatusCode(
                                                        EDailyPlanStatus.verify(equivalentWeeklyDate).getCode())
                                                                .withDailyPlanStatusName(
                                                                                EDailyPlanStatus.verify(equivalentWeeklyDate)
                                                                                                .getName()));
                DailyPlanEntity saveDailyPlan = dailyPlanRepository.save(
                                dailyPlanMapper.toEntity(newDailyPlan)
                                                .setAudit(requestContext.auditCreate()));
                duplicateDailyPlanAccount(oldDailyPlan.getDailyPlanId(), saveDailyPlan.getDailyPlanId());
                duplicateDailyPlanService(oldDailyPlan.getDailyPlanId(), saveDailyPlan.getDailyPlanId());
        }

        private void duplicateDailyPlanAccount(Long oldDailyPlanId, Long newDailyPlanId) {
                List<DailyPlanAccount> createdDailyPlanAccount = dailyPlanAccountQueryService.findAllBy(
                                DailyPlanAccountSearchByField.of(oldDailyPlanId));
                List<DailyPlanAccount> newDailyPlanAccount = DailyPlanAccount
                                .duplicate(createdDailyPlanAccount, newDailyPlanId);
                dailyPlanAccountCommandService.createList(
                                DailyPlanAccountCreateRequire.builder().dailyPlanAccounts(newDailyPlanAccount).build());

        }

        private void duplicateDailyPlanService(Long oldDailyPlanId, Long newDailyPlanId) {
                List<DailyPlanService> createdDailyPlanServices = dailyPlanServiceQueryService.findAllBy(
                                DailyPlanServiceSearchByField.of(oldDailyPlanId));
                List<DailyPlanService> newDailyPlanService = DailyPlanService.duplicate(createdDailyPlanServices,
                                newDailyPlanId);
                dailyPlanServiceCommandService.createList(
                                DailyPlanServiceCreateRequire.builder().dailyPlanServices(newDailyPlanService).build());
        }

        public DailyPlan update(DailyPlanUpdateRequire updateRequire) {
                DailyPlanEntity foundDailyPlan = dailyPlanRepository.findById(updateRequire.dailyPlanId())
                        .orElseThrow(NotFoundException::new)
                        .setAudit(requestContext.auditUpdate());
                DailyPlanAccountUpdateRequire staffUpdateRequire = DailyPlanAccountUpdateRequire
                        .of(updateRequire.dailyPlanId(), updateRequire.dailyPlanStaffUpdates());
                DailyPlanServiceUpdateRequire serviceUpdateRequire = DailyPlanServiceUpdateRequire
                        .of(updateRequire.dailyPlanId(), updateRequire.serviceIds());
                List<DailyPlanAccount> dailyPlanAccounts = dailyPlanAccountCommandService.update(staffUpdateRequire);
                List<DailyPlanService> dailyPlanServices = dailyPlanServiceCommandService.update(serviceUpdateRequire);
                DailyPlanEntity newDailyPlan = dailyPlanRepository.save(foundDailyPlan);

                return dailyPlanMapper.toDto(newDailyPlan)
                        .withDailyPlanAccounts(dailyPlanAccounts)
                        .withDailyPlanServices(dailyPlanServices);
        }
}
