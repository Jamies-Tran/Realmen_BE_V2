package com.capstone.realmen.data.dto.shop.service;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.capstone.realmen.data.dao.shop.service.ShopServiceDAO;
import com.capstone.realmen.repository.database.shop.service.ShopServiceEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ShopServiceMapper {
    ShopServiceEntity toEntity(ShopService dto);

    ShopService toDto(ShopServiceEntity entity);

    ShopService toDto(ShopServiceDAO dao);
}
