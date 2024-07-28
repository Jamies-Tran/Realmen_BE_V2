package com.capstone.realmen.data.dto.plans.daily;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.capstone.realmen.repository.database.plans.daily.DailyPlanEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IDailyPlanMapper {
    DailyPlanEntity toEntity(DailyPlan dto);

    DailyPlan toDto(DailyPlanEntity entity);

}
