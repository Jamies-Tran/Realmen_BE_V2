package com.capstone.realmen.service.shop.category.usecase.app;

import org.springframework.data.domain.Page;

import com.capstone.realmen.common.request.PageRequestCustom;
import com.capstone.realmen.data.dto.shop.category.ShopCategory;
import com.capstone.realmen.service.shop.category.data.ShopCategorySearchByField;
import com.capstone.realmen.service.shop.category.data.ShopCategorySearchCriteria;

public interface IAppShopCategoryService {
    ShopCategory appFindById(ShopCategorySearchByField searchByField);

    Page<ShopCategory> appFindAll(ShopCategorySearchCriteria searchCriteria, PageRequestCustom pageRequestCustom);
}
