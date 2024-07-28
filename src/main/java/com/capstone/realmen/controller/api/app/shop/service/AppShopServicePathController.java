package com.capstone.realmen.controller.api.app.shop.service;

import org.springframework.web.bind.annotation.RestController;

import com.capstone.realmen.common.response.ValueResponse;
import com.capstone.realmen.controller.api.app.shop.service.models.AppShopServiceResponse;
import com.capstone.realmen.controller.api.app.shop.service.models.IAppShopServiceModelMapper;
import com.capstone.realmen.data.dto.shop.service.ShopService;
import com.capstone.realmen.service.shop.service.ShopServiceUseCaseService;
import com.capstone.realmen.service.shop.service.data.ShopServiceSearchByField;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AppShopServicePathController implements IAppShopServicePathAPI{
    @NonNull
    ShopServiceUseCaseService shopServiceUseCaseService;

    @NonNull
    IAppShopServiceModelMapper shopServiceModelMapper;

    @Override
    public ValueResponse<AppShopServiceResponse> findById(Long shopServiceId) {
        ShopService foundShopService = shopServiceUseCaseService.appFindById(ShopServiceSearchByField.of(shopServiceId));

        return new ValueResponse<>(shopServiceModelMapper.toModel(foundShopService));
    }
}
