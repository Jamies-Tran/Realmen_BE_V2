package com.capstone.realmen.controller.api.admin.shop.category.models;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.capstone.realmen.data.dto.shop.category.ShopCategory;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAdminShopCategoryModelMapper {
    ShopCategory toDto(AdminShopCategoryRequest model);

    AdminShopCategoryResponse toModel(ShopCategory dto);
}
