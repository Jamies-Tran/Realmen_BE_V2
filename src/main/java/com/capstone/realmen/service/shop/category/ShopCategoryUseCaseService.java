package com.capstone.realmen.service.shop.category;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.capstone.realmen.common.request.PageRequestCustom;
import com.capstone.realmen.data.dto.shop.category.ShopCategory;
import com.capstone.realmen.service.shop.category.data.ShopCategoryCreateRequire;
import com.capstone.realmen.service.shop.category.data.ShopCategorySearchByField;
import com.capstone.realmen.service.shop.category.data.ShopCategorySearchCriteria;
import com.capstone.realmen.service.shop.category.usecase.admin.IAdminShopCategoryService;
import com.capstone.realmen.service.shop.category.usecase.app.IAppShopCategoryService;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ShopCategoryUseCaseService implements IAdminShopCategoryService, IAppShopCategoryService {
    @NonNull
    ShopCategoryCommandService shopCategoryCommandService;
    @NonNull
    ShopCategoryQueryService shopCategoryQueryService;

    @Override
    public void adminCreate(ShopCategoryCreateRequire createRequire) {
        shopCategoryCommandService.create(createRequire);
    }

    @Override
    public ShopCategory appFindById(ShopCategorySearchByField searchByField) {
        return shopCategoryQueryService.findById(searchByField);
    }

    @Override
    public Page<ShopCategory> appFindAll(ShopCategorySearchCriteria searchCriteria,
            PageRequestCustom pageRequestCustom) {
        return shopCategoryQueryService.findAll(searchCriteria, pageRequestCustom);
    }

    @Override
    public ShopCategory adminFindById(ShopCategorySearchByField searchByField) {
        return shopCategoryQueryService.findById(searchByField);
    }

    @Override
    public Page<ShopCategory> adminFindAll(ShopCategorySearchCriteria searchCriteria,
            PageRequestCustom pageRequestCustom) {
        return shopCategoryQueryService.findAll(searchCriteria, pageRequestCustom);
    }
}
