package com.capstone.realmen.service.plans.others.daily.plan;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Service;

import com.capstone.realmen.common.enums.EDailyPlanStatus;
import com.capstone.realmen.common.request.RequestContext;
import com.capstone.realmen.common.util.DateTimeHandler;
import com.capstone.realmen.controller.handler.exceptions.InvalidRequest;
import com.capstone.realmen.controller.handler.exceptions.NotFoundException;
import com.capstone.realmen.data.dto.common.DayInWeek;
import com.capstone.realmen.data.dto.plans.daily.DailyPlan;
import com.capstone.realmen.data.dto.plans.daily.IDailyPlanMapper;
import com.capstone.realmen.data.dto.plans.daily.account.DailyPlanAccount;
import com.capstone.realmen.data.dto.plans.daily.service.DailyPlanService;
import com.capstone.realmen.repository.database.plans.daily.DailyPlanEntity;
import com.capstone.realmen.repository.database.plans.daily.IDailyPlanRepository;
import com.capstone.realmen.service.plans.others.daily.plan.data.DailyPlanActiveRequire;
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
                List<LocalDate> dailyPlanDateList = getDailyPlanDateList(createRequire.pickUpDate());
                dailyPlanDateList.forEach(date -> {
                        Boolean isExist = dailyPlanRepository.existByDate(date);
                        if(isExist) {
                                throw new InvalidRequest("Ngày %s đã có kế hoạch hoạt động"
                                        .formatted(date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
                        }
                });
                EDailyPlanStatus defaultStatus = getDailyPlanStatus(createRequire.accounts(), createRequire.services());
                List<DailyPlan> dailyPlan = dailyPlanDateList.stream()
                                .map(dailyPlanDate -> {
                                        DayInWeek dayInWeek = DateTimeHandler.getDayInWeek(dailyPlanDate);
                                        EDailyPlanStatus status = EDailyPlanStatus.verify(dailyPlanDate, defaultStatus);
                                        return DailyPlan.builder()
                                                        .weeklyPlanId(createRequire.weeklyPlanId())
                                                        .date(dailyPlanDate)
                                                        .dayInWeekCode(dayInWeek.dayInWeekCode())
                                                        .dayInWeekName(dayInWeek.dayInWeekName())
                                                        .dailyPlanStatusCode(status.getCode())
                                                        .dailyPlanStatusName(status.getName())
                                                        .build();
                                })
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
                                                .accounts(createRequire.accounts())
                                                .build());
                dailyPlanServiceCommandService.createList(DailyPlanServiceCreateRequire.builder()
                                .dailyPlanIds(savedDailyPlanIds)
                                .branchServices(createRequire.services())
                                .build());
        }

        public void duplicateByWeeklyPlan(DailyPlanDuplicateRequire duplicateRequire) {
                Long newestWeeklyPlanId = dailyPlanRepository.getNewestWeeklyPlanId();
                List<DailyPlanEntity> foundDailyPlans = dailyPlanRepository
                                .findAllByWeeklyPlanId(newestWeeklyPlanId);

                foundDailyPlans.forEach(dailyPlan -> {
                        duplicateDailyPlan(
                                        dailyPlan,
                                        LocalDate.now(),
                                        duplicateRequire.newWeeklyPlanId());
                });
        }

        private void duplicateDailyPlan(
                        DailyPlanEntity oldDailyPlan,
                        LocalDate nextWeeklyDate,
                        Long newWeeklyPlanId) {
                LocalDate equivalentWeeklyDate = getEquivalentWeeklyDate(oldDailyPlan.getDate(), nextWeeklyDate);
                EDailyPlanStatus defaultStatus = EDailyPlanStatus.findByCode(oldDailyPlan.getDailyPlanStatusCode());
                DailyPlan newDailyPlan = DailyPlan
                                .duplicate(
                                                dailyPlanMapper.toDto(oldDailyPlan)
                                                                .withDate(equivalentWeeklyDate)
                                                                .withWeeklyPlanId(newWeeklyPlanId)
                                                                .withDailyPlanStatusCode(
                                                                                EDailyPlanStatus.verify(
                                                                                                equivalentWeeklyDate,
                                                                                                defaultStatus)
                                                                                                .getCode())
                                                                .withDailyPlanStatusName(
                                                                                EDailyPlanStatus.verify(
                                                                                                equivalentWeeklyDate,
                                                                                                defaultStatus)
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
                verifyUpdate(updateRequire.staffUpdates(), updateRequire.serviceUpdates());
                DailyPlanEntity foundDailyPlan = dailyPlanRepository.findById(updateRequire.dailyPlanId())
                                .orElseThrow(NotFoundException::new)
                                .setAudit(requestContext.auditUpdate());
                DailyPlanAccountUpdateRequire staffUpdateRequire = DailyPlanAccountUpdateRequire
                                .of(updateRequire.dailyPlanId(), updateRequire.staffUpdates());
                DailyPlanServiceUpdateRequire serviceUpdateRequire = DailyPlanServiceUpdateRequire
                                .of(updateRequire.dailyPlanId(), updateRequire.serviceUpdates());
                List<DailyPlanAccount> dailyPlanAccounts = dailyPlanAccountCommandService.update(staffUpdateRequire);
                List<DailyPlanService> dailyPlanServices = dailyPlanServiceCommandService.update(serviceUpdateRequire);
                DailyPlanEntity newDailyPlan = dailyPlanRepository.save(foundDailyPlan
                        .withDailyPlanStatusCode(EDailyPlanStatus.PROCESSING.getCode()))
                        .withDailyPlanStatusName(EDailyPlanStatus.PROCESSING.getName());

                return dailyPlanMapper.toDto(newDailyPlan)
                                .withDailyPlanAccounts(dailyPlanAccounts)
                                .withDailyPlanServices(dailyPlanServices);
        }

        public List<DailyPlan> active(DailyPlanActiveRequire activeRequire) {
                List<DailyPlanEntity> dailyPlans = dailyPlanRepository
                                .findAllByWeeklyPlanId(activeRequire.weeklyPlanId());
                List<Long> dailyPlanIds = dailyPlans.stream()
                                .map(DailyPlanEntity::getDailyPlanId)
                                .toList();
                DailyPlanAccountSearchByField aSearchByField = DailyPlanAccountSearchByField.of(dailyPlanIds);
                DailyPlanServiceSearchByField sSearchByField = DailyPlanServiceSearchByField.of(dailyPlanIds);
                List<DailyPlanAccount> accounts = dailyPlanAccountQueryService.findAllBy(aSearchByField);
                List<DailyPlanService> services = dailyPlanServiceQueryService.findAllBy(sSearchByField);
                List<DailyPlanEntity> activateDailyPlans = dailyPlans.stream()
                                .map(dailyPlan -> {
                                        List<DailyPlanAccount> getAccounts = daGroupingBy(accounts)
                                                        .get(dailyPlan.getDailyPlanId());
                                        List<DailyPlanService> getServices = dsGroupingBy(services)
                                                        .get(dailyPlan.getDailyPlanId());
                                        if (Objects.nonNull(getAccounts) && Objects.nonNull(getServices)) {
                                                return dailyPlan
                                                                .withDailyPlanStatusCode(
                                                                                EDailyPlanStatus.PROCESSING.getCode())
                                                                .withDailyPlanStatusName(
                                                                                EDailyPlanStatus.PROCESSING.getName());
                                        } else {
                                                return dailyPlan
                                                                .withDailyPlanStatusCode(
                                                                                EDailyPlanStatus.DISABLED.getCode())
                                                                .withDailyPlanStatusName(
                                                                                EDailyPlanStatus.DISABLED.getName());
                                        }
                                }).toList();
                return dailyPlanRepository.saveAll(activateDailyPlans)
                                .stream()
                                .map(dailyPlan -> {
                                        List<DailyPlanAccount> getAccounts = daGroupingBy(accounts)
                                                        .computeIfAbsent(dailyPlan.getDailyPlanId(), a -> List.of());
                                        List<DailyPlanService> getServices = dsGroupingBy(services)
                                                        .computeIfAbsent(dailyPlan.getDailyPlanId(), s -> List.of());
                                        return dailyPlanMapper.toDto(dailyPlan)
                                                        .withDailyPlanAccounts(getAccounts)
                                                        .withDailyPlanServices(getServices);
                                })
                                .toList();
        }
}
