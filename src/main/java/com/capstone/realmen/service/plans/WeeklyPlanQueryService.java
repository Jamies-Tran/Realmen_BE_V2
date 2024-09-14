package com.capstone.realmen.service.plans;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.capstone.realmen.common.enums.EDailyPlanStatus;
import com.capstone.realmen.common.request.PageRequestCustom;
import com.capstone.realmen.controller.handler.exceptions.NotFoundException;
import com.capstone.realmen.data.dto.plans.daily.DailyPlan;
import com.capstone.realmen.data.dto.plans.weekly.IWeeklyPlanMapper;
import com.capstone.realmen.data.dto.plans.weekly.WeeklyPlan;
import com.capstone.realmen.repository.database.plans.weekly.IWeeklyPlanRepository;
import com.capstone.realmen.repository.database.plans.weekly.WeeklyPlanEntity;
import com.capstone.realmen.service.plans.data.WeeklyPlanSearchByField;
import com.capstone.realmen.service.plans.data.WeeklyPlanSearchCriteria;
import com.capstone.realmen.service.plans.others.daily.plan.DailyPlanQueryService;
import com.capstone.realmen.service.plans.others.daily.plan.data.DailyPlanSearchByField;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class WeeklyPlanQueryService {
        @NonNull
        IWeeklyPlanRepository repository;

        @NonNull
        DailyPlanQueryService dailyPlanQueryService;

        @NonNull
        IWeeklyPlanMapper mapper;

        public Page<WeeklyPlan> findAll(WeeklyPlanSearchCriteria searchCriteria, PageRequestCustom pageRequestCustom) {
                Page<WeeklyPlan> weeklyPlans = repository.findAll(searchCriteria, pageRequestCustom.pageRequest())
                                .map(mapper::toDto);
                List<Long> weeklyPlanIds = weeklyPlans.stream().map(WeeklyPlan::weeklyPlanId).toList();
                DailyPlanSearchByField searchByField = DailyPlanSearchByField.of(weeklyPlanIds,
                                EDailyPlanStatus.PROCESSING.getCode());
                Map<Long, List<DailyPlan>> dailyPlans = dailyPlanQueryService.findByWeeklyPlanIds(searchByField)
                                .stream().collect(Collectors.groupingBy(DailyPlan::weeklyPlanId));

                return weeklyPlans.map(weeklyPlan -> {
                        List<DailyPlan> getDailyPlans = dailyPlans.computeIfAbsent(weeklyPlan.weeklyPlanId(),
                                        d -> List.of());
                        Integer dailyPlanActives = getDailyPlans.size();

                        return weeklyPlan.withDailyPlanActive(dailyPlanActives);
                });
        }

        public WeeklyPlan findById(WeeklyPlanSearchByField searchByField) {
                WeeklyPlanEntity foundWeeklyPlan = repository.findById(searchByField.weeklyPlanId())
                                .orElseThrow(NotFoundException::new);
                DailyPlanSearchByField dSearchByField = DailyPlanSearchByField
                                .ofWeeklyPlanId(searchByField.weeklyPlanId());
                List<DailyPlan> dailyPlans = dailyPlanQueryService.findByWeeklyPlanIds(dSearchByField);

                return mapper.toDto(foundWeeklyPlan)
                                .withDailyPlans(dailyPlans);
        }
}
