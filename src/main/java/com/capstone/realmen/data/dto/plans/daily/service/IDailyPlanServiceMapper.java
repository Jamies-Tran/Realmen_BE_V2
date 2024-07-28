package com.capstone.realmen.data.dto.plans.daily.service;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.capstone.realmen.data.dao.plans.daily.service.DailyPlanServiceDAO;
import com.capstone.realmen.repository.database.shop.service.plans.DailyPlanServiceEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IDailyPlanServiceMapper {
    DailyPlanServiceEntity toEntity(DailyPlanService dto);

    DailyPlanService toDto(DailyPlanServiceDAO dao);
}
