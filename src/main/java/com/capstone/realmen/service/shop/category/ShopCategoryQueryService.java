package com.capstone.realmen.service.shop.category;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.capstone.realmen.common.request.PageRequestCustom;
import com.capstone.realmen.controller.handler.exceptions.NotFoundException;
import com.capstone.realmen.data.dto.shop.category.ShopCategory;
import com.capstone.realmen.data.dto.shop.category.ShopCategoryMapper;
import com.capstone.realmen.data.dto.shop.service.ShopService;
import com.capstone.realmen.repository.database.shop.category.IShopCategoryRepository;
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
    IShopCategoryRepository shopCategoryRepository;
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
        Map<Long, List<ShopService>> shopServices = shopServiceQueryService
                .findAll(ShopServiceSearchCriteria.ofDefault(),
                        PageRequestCustom.of(1, searchCriteria.serviceNumber()))
                .getContent().stream()
                .collect(Collectors.groupingBy(ShopService::shopCategoryId));        
        return shopCategoryRepository
                .findAll(searchCriteria.toLowerCase(), pageRequestCustom.pageRequest())
                .map(category -> shopCategoryMapper.toDto(category)
                        .withShopServices(shopServices.computeIfAbsent(category.getShopCategoryId(), s -> List.of())));
    }
}
