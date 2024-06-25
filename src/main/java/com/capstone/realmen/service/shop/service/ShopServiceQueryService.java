package com.capstone.realmen.service.shop.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.capstone.realmen.common.request.PageRequestCustom;
import com.capstone.realmen.controller.handler.exceptions.NotFoundException;
import com.capstone.realmen.data.dto.shop.service.ShopService;
import com.capstone.realmen.data.dto.shop.service.ShopServiceMapper;
import com.capstone.realmen.repository.database.shop.service.ShopServiceRepository;
import com.capstone.realmen.service.shop.service.data.ShopServiceSearchByField;
import com.capstone.realmen.service.shop.service.data.ShopServiceSearchCriteria;
import com.capstone.realmen.service.shop.service.others.display.ServiceDisplayQueryService;
import com.capstone.realmen.service.shop.service.others.display.data.ServiceDisplaySearchByField;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ShopServiceQueryService {
    @NonNull
    ShopServiceRepository shopServiceRepository;
    @NonNull
    ServiceDisplayQueryService serviceDisplayQueryService;
    @NonNull
    ShopServiceMapper shopServiceMapper;

    public ShopService findById(ShopServiceSearchByField searchByField) {
        return shopServiceMapper.toDto(
                shopServiceRepository
                        .findByShopServiceId(searchByField.shopServiceId())
                        .orElseThrow(NotFoundException::new))
                .withServiceDisplays(serviceDisplayQueryService
                        .findByShopServiceId(
                                ServiceDisplaySearchByField.ofShopServiceId(searchByField.shopServiceId())));
    }

    public Page<ShopService> findAll(ShopServiceSearchCriteria searchCriteria, PageRequestCustom pageRequestCustom) {
        return shopServiceRepository
                .findAll(searchCriteria.toLowerCase(), pageRequestCustom.pageRequest())
                .map(shopServiceMapper::toDto);
    }
}
