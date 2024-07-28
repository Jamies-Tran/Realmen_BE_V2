package com.capstone.realmen.service.shop.service.usecase;

import org.springframework.data.domain.Page;

import com.capstone.realmen.common.request.PageRequestCustom;
import com.capstone.realmen.data.dto.shop.service.ShopService;
import com.capstone.realmen.service.shop.service.data.ShopServiceSearchByField;
import com.capstone.realmen.service.shop.service.data.ShopServiceSearchCriteria;

public interface IAppShopServiceService {
    ShopService appFindById(ShopServiceSearchByField searchByField);

    Page<ShopService> appFindAll(ShopServiceSearchCriteria searchCriteria, PageRequestCustom pageRequestCustom);
}
