package com.capstone.realmen.service.plans.others.daily.plan.others.service;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.capstone.realmen.data.dto.plans.daily.service.DailyPlanService;
import com.capstone.realmen.data.dto.plans.daily.service.IDailyPlanServiceMapper;
import com.capstone.realmen.repository.database.shop.service.plans.DailyPlanServiceEntity;
import com.capstone.realmen.repository.database.shop.service.plans.IDailyPlanServiceRepository;
import com.capstone.realmen.service.plans.others.daily.plan.others.service.data.DailyPlanServiceCreateRequire;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DailyPlanServiceCommandService {
    @NonNull
    IDailyPlanServiceRepository dailyPlanServiceRepository;

    @NonNull
    IDailyPlanServiceMapper dailyPlanServiceMapper;

    public void createList(DailyPlanServiceCreateRequire createRequire) {
        if (Objects.nonNull(createRequire.dailyPlanServices())) {
            dailyPlanServiceRepository.saveAll(
                    createRequire.dailyPlanServices().stream().map(dailyPlanServiceMapper::toEntity).toList());
        } else {
            List<DailyPlanServiceEntity> newDailyPlanServices = DailyPlanService
                    .of(createRequire.dailyPlanIds(), createRequire.shopServiceIds())
                    .stream()
                    .map(dailyPlanServiceMapper::toEntity)
                    .toList();
            dailyPlanServiceRepository.saveAll(newDailyPlanServices);
        }

    }
}
