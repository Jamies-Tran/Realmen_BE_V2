package com.capstone.realmen.controller.api.admin.shop.category;

import org.springframework.web.bind.annotation.RestController;

import com.capstone.realmen.controller.api.admin.shop.category.models.AdminShopCategoryRequest;
import com.capstone.realmen.controller.api.admin.shop.category.models.IAdminShopCategoryModelMapper;
import com.capstone.realmen.service.shop.category.ShopCategoryUseCaseService;
import com.capstone.realmen.service.shop.category.data.ShopCategoryCreateRequire;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdminShopCategoryController implements IAdminShopCategoryAPI{
    @NonNull
    ShopCategoryUseCaseService shopCategoryUseCaseService;
    @NonNull
    IAdminShopCategoryModelMapper modelMapper;


    @Override
    public void save(AdminShopCategoryRequest shopCategoryRequest) {
        shopCategoryUseCaseService.adminCreate(
            ShopCategoryCreateRequire.builder()
                .shopCategory(modelMapper.toDto(shopCategoryRequest))
                .build()
        );
    }


}
