package com.capstone.realmen.service.shop.category.usecase.admin;

import org.springframework.data.domain.Page;

import com.capstone.realmen.common.request.PageRequestCustom;
import com.capstone.realmen.data.dto.shop.category.ShopCategory;
import com.capstone.realmen.service.shop.category.data.ShopCategoryCreateRequire;
import com.capstone.realmen.service.shop.category.data.ShopCategorySearchByField;
import com.capstone.realmen.service.shop.category.data.ShopCategorySearchCriteria;

public interface IAdminShopCategoryService {
    void adminCreate(ShopCategoryCreateRequire createRequire);

    ShopCategory adminFindById(ShopCategorySearchByField searchByField);

    Page<ShopCategory> adminFindAll(ShopCategorySearchCriteria searchCriteria, PageRequestCustom pageRequestCustom);
}
