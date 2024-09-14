package com.capstone.realmen.controller.api.app.plans.daily.account.models;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.capstone.realmen.data.dto.plans.daily.account.DailyPlanAccount;

@Mapper(
    componentModel = "spring", 
    unmappedTargetPolicy = ReportingPolicy.IGNORE, 
    implementationName = "AppDailyPlanAccount"
)
public interface IDailyPlanAccountModelMapper {
    DailyPlanAccountResponse toModel(DailyPlanAccount dto);
}
