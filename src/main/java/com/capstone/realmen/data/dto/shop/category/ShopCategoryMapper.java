package com.capstone.realmen.data.dto.shop.category;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.capstone.realmen.repository.database.shop.category.ShopCategoryEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ShopCategoryMapper {
    ShopCategoryEntity toEntity(ShopCategory dto);
}
