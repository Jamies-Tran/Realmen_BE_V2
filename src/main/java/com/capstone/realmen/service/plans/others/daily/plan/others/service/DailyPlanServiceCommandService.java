package com.capstone.realmen.service.plans.others.daily.plan.others.service;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.capstone.realmen.data.dto.plans.daily.service.DailyPlanService;
import com.capstone.realmen.data.dto.plans.daily.service.IDailyPlanServiceMapper;
import com.capstone.realmen.repository.database.plans.daily.service.DailyPlanServiceEntity;
import com.capstone.realmen.repository.database.plans.daily.service.IDailyPlanServiceRepository;
import com.capstone.realmen.service.branch.others.services.BranchServiceQueryService;
import com.capstone.realmen.service.plans.others.daily.plan.others.service.data.DailyPlanServiceCreateRequire;
import com.capstone.realmen.service.plans.others.daily.plan.others.service.data.DailyPlanServiceDeleteRequire;
import com.capstone.realmen.service.plans.others.daily.plan.others.service.data.DailyPlanServiceUpdateRequire;

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

    @NonNull
    BranchServiceQueryService bsQuery;

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

    public List<DailyPlanService> update(DailyPlanServiceUpdateRequire updateRequire) {
        DailyPlanServiceDeleteRequire deleteRequire = DailyPlanServiceDeleteRequire
            .of(updateRequire.dailyPlanId());
        deleteAll(deleteRequire);
        Long dailyPlanId = updateRequire.dailyPlanId();
        List<DailyPlanServiceEntity> dailyPlanServices = updateRequire.serviceIds()
                .stream()
                .map(serviceId -> DailyPlanService.builder()
                        .dailyPlanId(dailyPlanId)
                        .shopServiceId(serviceId)
                        .build())
                .map(dailyPlanServiceMapper::toEntity)
                .toList();
        List<DailyPlanServiceEntity> newDailyPlanServices = dailyPlanServiceRepository.saveAll(dailyPlanServices);
        return newDailyPlanServices.stream()
                .map(dailyPlanServiceMapper::toDto)
                .toList();
    }

    public void deleteAll(DailyPlanServiceDeleteRequire deleteRequire) {
        dailyPlanServiceRepository.deleteAllByDailyPlanId(deleteRequire.dailyPlanId());
    }
}
