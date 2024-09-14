package com.capstone.realmen.controller.api.app.plans.daily.models;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.capstone.realmen.data.dto.plans.daily.DailyPlan;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, implementationName = "AppDailyPlanModelMapper")
public interface IDailyPlanModelMapper {
    DailyPlanResponse toModel(DailyPlan dto);
}
