package com.capstone.realmen.controller.api.admin.plans.daily.account.models;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.capstone.realmen.controller.api.admin.plans.daily.models.DailyPlanAccountResponse;
import com.capstone.realmen.data.dto.plans.daily.account.DailyPlanAccount;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, implementationName = "AdminDailyPlanAccountMapper")
public interface IDailyPlanAccountModelMapper {
    DailyPlanAccountResponse toModel(DailyPlanAccount dto);

}
