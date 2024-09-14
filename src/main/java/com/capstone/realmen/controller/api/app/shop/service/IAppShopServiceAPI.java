package com.capstone.realmen.controller.api.app.shop.service;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.capstone.realmen.common.response.PageImplResponse;
import com.capstone.realmen.controller.api.app.shop.service.models.AppShopServiceResponse;

@RequestMapping("/mobile/shop-service")
public interface IAppShopServiceAPI {
    @GetMapping
    PageImplResponse<AppShopServiceResponse> findAll(
            @RequestParam(required = false, value = "search", defaultValue = "") String search,
            @RequestParam(required = false, value = "branchId", defaultValue = "") Long branchId,
            @RequestParam(required = false, value = "shopCategoryId", defaultValue = "") Long shopCategoryId,
            @RequestParam(required = false, value = "shopServicePriceRange", defaultValue = "") List<Long> shopServicePriceRange,
            @RequestParam(required = false, value = "assignmentTypeCode", defaultValue = "") String assignmentTypeCode,
            @RequestParam(required = false, value = "current", defaultValue = "1") Integer current,
            @RequestParam(required = false, value = "pageSize", defaultValue = "20") Integer pageSize);
}
