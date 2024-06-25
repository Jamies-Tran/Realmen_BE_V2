package com.capstone.realmen.service.shop.service.usecase;

import org.springframework.data.domain.Page;

import com.capstone.realmen.common.request.PageRequestCustom;
import com.capstone.realmen.data.dto.shop.service.ShopService;
import com.capstone.realmen.service.shop.service.data.ShopServiceCreateRequire;
import com.capstone.realmen.service.shop.service.data.ShopServiceSearchByField;
import com.capstone.realmen.service.shop.service.data.ShopServiceSearchCriteria;

public interface IAdminShopServiceService {
    void adminCreateShopService(ShopServiceCreateRequire createRequire);

    ShopService adminFindById(ShopServiceSearchByField searchByField);

    Page<ShopService> adminFindAll(ShopServiceSearchCriteria searchCriteria, PageRequestCustom pageRequestCustom);
}
