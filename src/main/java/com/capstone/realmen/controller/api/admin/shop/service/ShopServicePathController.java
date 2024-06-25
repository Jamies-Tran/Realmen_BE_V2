package com.capstone.realmen.controller.api.admin.shop.service;

import org.springframework.web.bind.annotation.RestController;

import com.capstone.realmen.common.response.ValueResponse;
import com.capstone.realmen.controller.api.admin.shop.service.models.AdminShopServiceResponse;
import com.capstone.realmen.controller.api.admin.shop.service.models.IAdminShopServiceModelMapper;
import com.capstone.realmen.service.shop.service.ShopServiceUseCaseService;
import com.capstone.realmen.service.shop.service.data.ShopServiceSearchByField;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ShopServicePathController implements IShopServicePathAPI {
    @NonNull
    ShopServiceUseCaseService shopServiceUseCaseService;
    @NonNull
    IAdminShopServiceModelMapper modelMapper;

    @Override
    public ValueResponse<AdminShopServiceResponse> findById(Long shopServiceId) {
        return new ValueResponse<AdminShopServiceResponse>(
                modelMapper.toModel(
                        shopServiceUseCaseService.adminFindById(
                                ShopServiceSearchByField.builder()
                                        .shopServiceId(shopServiceId)
                                        .build())));
    }
}
