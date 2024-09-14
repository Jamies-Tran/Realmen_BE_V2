package com.capstone.realmen.controller.api.admin.shop.service.models;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.capstone.realmen.data.dto.branch.service.BranchService;
import com.capstone.realmen.data.dto.shop.service.ShopService;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAdminShopServiceModelMapper {
    @Mapping(target = "serviceDisplays", ignore = true)
    ShopService toDto(AdminShopServiceRequest model);

    AdminShopServiceResponse toModel(ShopService dto);

    AdminShopServiceResponse toModel(BranchService dto);
}
