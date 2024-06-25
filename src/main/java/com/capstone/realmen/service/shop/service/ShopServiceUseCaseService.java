package com.capstone.realmen.service.shop.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capstone.realmen.common.request.PageRequestCustom;
import com.capstone.realmen.data.dto.shop.service.ShopService;
import com.capstone.realmen.service.shop.service.data.ShopServiceCreateRequire;
import com.capstone.realmen.service.shop.service.data.ShopServiceSearchByField;
import com.capstone.realmen.service.shop.service.data.ShopServiceSearchCriteria;
import com.capstone.realmen.service.shop.service.usecase.IAdminShopServiceService;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ShopServiceUseCaseService implements IAdminShopServiceService{
    @NonNull
    ShopServiceCommandService shopServiceCommandService;
    @NonNull
    ShopServiceQueryService shopServiceQueryService;
    
    @Override
    @Transactional
    public void adminCreateShopService(ShopServiceCreateRequire createRequire) {
        shopServiceCommandService.create(createRequire);
    }

    @Override
    public Page<ShopService> adminFindAll(ShopServiceSearchCriteria searchCriteria,
            PageRequestCustom pageRequestCustom) {
        return shopServiceQueryService.findAll(searchCriteria, pageRequestCustom);
    }

    @Override
    public ShopService adminFindById(ShopServiceSearchByField searchByField) {
        return shopServiceQueryService.findById(searchByField);
    }
    
}
