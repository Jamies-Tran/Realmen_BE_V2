package com.capstone.realmen.data.dto.plans.weekly;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.capstone.realmen.repository.database.plans.weekly.WeeklyPlanEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IWeeklyPlanMapper {
    WeeklyPlanEntity toEntity(WeeklyPlan dto);    

    WeeklyPlan toDto(WeeklyPlanEntity toEntity);
}
