package com.capstone.realmen.controller.api.admin.shop.category;

import org.springframework.web.bind.annotation.RestController;

import com.capstone.realmen.common.response.ValueResponse;
import com.capstone.realmen.controller.api.admin.shop.category.models.AdminShopCategoryResponse;
import com.capstone.realmen.controller.api.admin.shop.category.models.IAdminShopCategoryModelMapper;
import com.capstone.realmen.service.shop.category.ShopCategoryUseCaseService;
import com.capstone.realmen.service.shop.category.data.ShopCategorySearchByField;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdminShopCategoryPathController implements IAdminShopCategoryPathAPI {
    @NonNull
    ShopCategoryUseCaseService shopCategoryUseCaseService;
    @NonNull
    IAdminShopCategoryModelMapper modelMapper;

    @Override
    public ValueResponse<AdminShopCategoryResponse> findById(Long categoryId) {

        return new ValueResponse<AdminShopCategoryResponse>(
                modelMapper.toModel(
                        shopCategoryUseCaseService.adminFindById(ShopCategorySearchByField
                                .builder()
                                .shopCategoryId(categoryId)
                                .build())));
    }

}
