package com.capstone.realmen.service.shop.category;

import org.springframework.stereotype.Service;

import com.capstone.realmen.service.shop.category.data.ShopCategoryCreateRequire;
import com.capstone.realmen.service.shop.category.usecase.admin.IAdminShopCategoryService;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ShopCategoryUseCaseService implements IAdminShopCategoryService {
    @NonNull
    ShopCategoryCommandService shopCategoryCommandService;

    @Override
    public void adminCreate(ShopCategoryCreateRequire createRequire) {
        shopCategoryCommandService.create(createRequire);
    }
}
