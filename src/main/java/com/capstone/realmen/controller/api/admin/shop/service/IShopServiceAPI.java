package com.capstone.realmen.controller.api.admin.shop.service;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.capstone.realmen.common.response.PageImplResponse;
import com.capstone.realmen.controller.api.admin.shop.service.models.AdminShopServiceRequest;
import com.capstone.realmen.controller.api.admin.shop.service.models.AdminShopServiceResponse;

import jakarta.validation.Valid;

@RequestMapping("/web/shop-service")
public interface IShopServiceAPI {
    @PostMapping
    void save(@RequestBody @Valid AdminShopServiceRequest serviceRequest);

    @GetMapping
    PageImplResponse<AdminShopServiceResponse> findAll(
        @RequestParam(required = false, value = "search", defaultValue = "") String search,
        @RequestParam(required = false, value = "shopCategoryId", defaultValue = "") Long shopCategoryId,
        @RequestParam(required = false, value = "shopServicePriceRange", defaultValue = "") List<Long> shopServicePriceRange,
        @RequestParam(required = false, value = "current", defaultValue = "1") Integer current,
        @RequestParam(required = false, value = "pageSize", defaultValue = "20") Integer pageSize
    );
}
