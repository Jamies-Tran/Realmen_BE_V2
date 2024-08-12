package com.capstone.realmen.controller.api.admin.plans.daily.models;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.capstone.realmen.data.dto.plans.daily.DailyPlan;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAdminDailyPlanModelMapper {
    DailyPlanResponse toModel(DailyPlan dto);
}
