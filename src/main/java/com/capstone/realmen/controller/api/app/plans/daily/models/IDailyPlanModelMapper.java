package com.capstone.realmen.controller.api.app.plans.daily.models;

import org.mapstruct.Mapper;

import com.capstone.realmen.data.dto.plans.daily.DailyPlan;

@Mapper(componentModel = "spring", implementationName = "AppDailyPlan")
public interface IDailyPlanModelMapper {
    DailyPlanResponse toModel(DailyPlan dto);
}
