package com.capstone.realmen.service.plans.others.daily.plan.others.service;

import java.util.List;
import java.util.Objects;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.capstone.realmen.common.request.PageRequestCustom;
import com.capstone.realmen.controller.handler.exceptions.NotFoundException;
import com.capstone.realmen.data.dao.plans.daily.service.DailyPlanServiceDAO;
import com.capstone.realmen.data.dto.plans.daily.service.DailyPlanService;
import com.capstone.realmen.data.dto.plans.daily.service.IDailyPlanServiceMapper;
import com.capstone.realmen.repository.database.plans.daily.service.IDailyPlanServiceRepository;
import com.capstone.realmen.service.plans.others.daily.plan.others.service.data.DailyPlanServiceSearchByField;
import com.capstone.realmen.service.plans.others.daily.plan.others.service.data.DailyPlanServiceSearchCriteria;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DailyPlanServiceQueryService {
    @NonNull
    IDailyPlanServiceRepository dailyPlanServiceRepository;

    @NonNull
    IDailyPlanServiceMapper dailyPlanServiceMapper;

    public List<DailyPlanService> findAllBy(DailyPlanServiceSearchByField searchByField) {
        if (Objects.nonNull(searchByField.dailyPlanId())) {
            return dailyPlanServiceRepository.findAllByDailyPlanId(searchByField.dailyPlanId())
                    .stream()
                    .map(dailyPlanServiceMapper::toDto)
                    .toList();
        }
        if (Objects.nonNull(searchByField.dailyPlanIds())) {
            return dailyPlanServiceRepository.findAllByDailyPlanIdIn(searchByField.dailyPlanIds())
                    .stream()
                    .map(dailyPlanServiceMapper::toDto)
                    .toList();
        }
        return List.of();
    }

    public Page<DailyPlanService> findAll(DailyPlanServiceSearchCriteria searchCriteria,
            PageRequestCustom pageRequestCustom) {
        return dailyPlanServiceRepository.findAll(searchCriteria, pageRequestCustom.pageRequest())
                .map(dailyPlanServiceMapper::toDto);
    }

    public DailyPlanService findById(DailyPlanServiceSearchByField searchByField) {
        DailyPlanServiceDAO foundDailyPlanService = dailyPlanServiceRepository
                .findByDailyPlanServiceId(searchByField.dailyPlanServiceId())
                .orElseThrow(NotFoundException::new);

        return dailyPlanServiceMapper.toDto(foundDailyPlanService);
    }
}
