package com.capstone.realmen.service.shop.category;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.capstone.realmen.common.request.PageRequestCustom;
import com.capstone.realmen.controller.handler.exceptions.NotFoundException;
import com.capstone.realmen.data.dto.shop.category.ShopCategory;
import com.capstone.realmen.data.dto.shop.category.ShopCategoryMapper;
import com.capstone.realmen.data.dto.shop.service.ShopService;
import com.capstone.realmen.repository.database.shop.category.ShopCategoryRepository;
import com.capstone.realmen.service.shop.category.data.ShopCategorySearchByField;
import com.capstone.realmen.service.shop.category.data.ShopCategorySearchCriteria;
import com.capstone.realmen.service.shop.service.ShopServiceQueryService;
import com.capstone.realmen.service.shop.service.data.ShopServiceSearchCriteria;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ShopCategoryQueryService {
    @NonNull
    ShopCategoryRepository shopCategoryRepository;
    @NonNull
    ShopServiceQueryService shopServiceQueryService;
    @NonNull
    ShopCategoryMapper shopCategoryMapper;

    public ShopCategory findById(ShopCategorySearchByField searchByField) {
        return shopCategoryMapper.toDto(
                shopCategoryRepository.findById(searchByField.shopCategoryId())
                        .orElseThrow(NotFoundException::new));
    }

    public Page<ShopCategory> findAll(ShopCategorySearchCriteria searchCriteria,
            PageRequestCustom pageRequestCustom) {
        List<ShopService> shopServices = shopServiceQueryService
                .findAll(ShopServiceSearchCriteria.ofDefault(),
                        PageRequestCustom.of(1, searchCriteria.serviceNumber()))
                .getContent();
        return shopCategoryRepository.findAll(searchCriteria, pageRequestCustom.pageRequest())
                .map(category -> shopCategoryMapper.toDto(category)
                        .withShopServices(shopServices));
    }
}
