package com.capstone.realmen.data.dto.plans.daily.account;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.capstone.realmen.repository.database.account.plans.DailyPlanAccountEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IDailyPlanAccountMapper {
    DailyPlanAccountEntity toEntity(DailyPlanAccount dto);
}
