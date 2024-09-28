package com.capstone.realmen.controller.api.admin.plans.daily.service.models;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.capstone.realmen.controller.api.admin.plans.daily.models.DailyPlanServiceResponse;
import com.capstone.realmen.data.dto.plans.daily.service.DailyPlanService;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, implementationName = "AdminDailyPlanService")
public interface IDailyPlanServiceModelMapper {
    DailyPlanServiceResponse toModel(DailyPlanService dto);
}
