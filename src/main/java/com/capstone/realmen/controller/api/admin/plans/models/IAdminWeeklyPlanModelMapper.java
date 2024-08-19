package com.capstone.realmen.controller.api.admin.plans.models;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.capstone.realmen.data.dto.plans.weekly.WeeklyPlan;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAdminWeeklyPlanModelMapper {
    AdminWeeklyPlanResponse toModel(WeeklyPlan dto);

}
