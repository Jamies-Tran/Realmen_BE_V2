package com.capstone.realmen.controller.api.admin.shop.service;

import org.springframework.web.bind.annotation.RestController;

import com.capstone.realmen.controller.api.admin.shop.service.displays.models.ServiceDisplayModelMapper;
import com.capstone.realmen.controller.api.admin.shop.service.models.AdminShopServiceModelMapper;
import com.capstone.realmen.controller.api.admin.shop.service.models.AdminShopServiceRequest;
import com.capstone.realmen.service.shop.service.ShopServiceUseCaseService;
import com.capstone.realmen.service.shop.service.data.ShopServiceCreateRequire;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ShopServiceController implements ShopServiceAPI {
    @NonNull
    ShopServiceUseCaseService shopServiceUseCaseService;
    @NonNull
    AdminShopServiceModelMapper shopServiceModelMapper;
    @NonNull
    ServiceDisplayModelMapper serviceDisplayModelMapper;

    @Override
    public void save(@Valid AdminShopServiceRequest serviceRequest) {
        shopServiceUseCaseService.adminCreateShopService(
                ShopServiceCreateRequire.builder()
                        .shopService(shopServiceModelMapper.toDto(serviceRequest))
                        .serviceDisplays(serviceRequest
                                .serviceDisplays().stream()
                                .map(serviceDisplayModelMapper::toDto)
                                .toList())
                        .build());
    }

}
