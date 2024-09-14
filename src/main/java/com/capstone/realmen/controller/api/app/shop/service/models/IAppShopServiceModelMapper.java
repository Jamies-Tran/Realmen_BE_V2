package com.capstone.realmen.controller.api.app.shop.service.models;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.capstone.realmen.data.dto.branch.service.BranchService;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAppShopServiceModelMapper {
    AppShopServiceResponse toModel(BranchService dto);
}
