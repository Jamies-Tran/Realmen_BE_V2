package com.capstone.realmen.data.dto.plans.daily.account;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.capstone.realmen.data.dao.plans.daily.account.DailyPlanAccountDAO;
import com.capstone.realmen.repository.database.plans.daily.account.DailyPlanAccountEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IDailyPlanAccountMapper {
    DailyPlanAccountEntity toEntity(DailyPlanAccount dto);

    DailyPlanAccount toDto(DailyPlanAccountEntity entity);

    DailyPlanAccount toDto(DailyPlanAccountDAO dao);
}
