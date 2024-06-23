package com.capstone.realmen.service.shop.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.capstone.realmen.common.request.PageRequestCustom;
import com.capstone.realmen.controller.handler.exceptions.NotFoundException;
import com.capstone.realmen.data.dto.shop.service.ShopService;
import com.capstone.realmen.data.dto.shop.service.ShopServiceMapper;
import com.capstone.realmen.repository.database.shop.service.ShopServiceRepository;
import com.capstone.realmen.service.shop.service.data.ShopServiceSearchByField;
import com.capstone.realmen.service.shop.service.data.ShopServiceSearchCriteria;

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
    ShopServiceMapper shopServiceMapper;

    public ShopService findById(ShopServiceSearchByField searchByField) {
        return shopServiceMapper.toDto(
            shopServiceRepository.findById(searchByField.shopServiceId())
                .orElseThrow(NotFoundException::new)
        );
    }

    public Page<ShopService> findAll(ShopServiceSearchCriteria searchCriteria, PageRequestCustom pageRequestCustom) {
        return shopServiceRepository.findAll(searchCriteria, pageRequestCustom.pageRequest())
            .map(shopServiceMapper::toDto);
    }
}
