package com.capstone.realmen.service.plans.others.daily.plan;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.capstone.realmen.data.dto.plans.daily.DailyPlan;
import com.capstone.realmen.data.dto.plans.daily.IDailyPlanMapper;
import com.capstone.realmen.repository.database.plans.daily.DailyPlanEntity;
import com.capstone.realmen.repository.database.plans.daily.IDailyPlanRepository;
import com.capstone.realmen.service.plans.others.daily.plan.data.DailyPlanCreateRequire;
import com.capstone.realmen.service.plans.others.daily.plan.helpers.DailyPlanHelpers;
import com.capstone.realmen.service.plans.others.daily.plan.others.account.DailyPlanAccountCommandService;
import com.capstone.realmen.service.plans.others.daily.plan.others.account.data.DailyPlanAccountCreateRequire;

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
    IDailyPlanMapper dailyPlanMapper;

    public void create(DailyPlanCreateRequire createRequire) {
        List<LocalDateTime> dailyPlanDateList = getDailyPlanDateList(createRequire.pickUpDate(),
                createRequire.dailyPlanCreateType());
        List<DailyPlan> dailyPlan = dailyPlanDateList.stream()
                .map(dailyPlanDate -> DailyPlan.builder()
                        .weeklyPlanId(createRequire.weeklyPlanId())
                        .date(dailyPlanDate)
                        .build())
                .toList();
        List<DailyPlanEntity> savedDailyPlan = dailyPlanRepository
            .saveAll(dailyPlan.stream().map(dailyPlanMapper::toEntity).toList());
        List<Long> savedDailyPlanIds = savedDailyPlan.stream()
            .map(DailyPlanEntity::getDailyPlanId)
            .toList();
        dailyPlanAccountCommandService.createList(
            DailyPlanAccountCreateRequire.builder()
                .dailyPlanIds(savedDailyPlanIds)
                .accountIds(createRequire.accountIds())
                .build()
        );
    }
}
