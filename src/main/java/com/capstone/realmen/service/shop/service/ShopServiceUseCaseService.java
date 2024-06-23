package com.capstone.realmen.service.shop.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capstone.realmen.service.shop.service.data.ShopServiceCreateRequire;
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
    
    @Override
    @Transactional
    public void adminCreateShopService(ShopServiceCreateRequire createRequire) {
        shopServiceCommandService.create(createRequire);
    }
    
}
