package com.capstone.realmen.controller.api.app.plans.daily.services.models;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.capstone.realmen.data.dto.plans.daily.service.DailyPlanService;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, implementationName = "AppDailyPlanServiceMapper")
public interface IDailyPlanServiceModelMapper {
    DailyPlanServiceResponse toModel(DailyPlanService dto);
}
