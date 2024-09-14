package com.capstone.realmen.controller.api.app.shop.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.capstone.realmen.common.response.ValueResponse;
import com.capstone.realmen.controller.api.app.shop.service.models.AppShopServiceResponse;

@RequestMapping("/mobile/shop-service/{shopServiceId}")
public interface IAppShopServicePathAPI {
    @GetMapping
    ValueResponse<AppShopServiceResponse> findById(
        @PathVariable Long shopServiceId,
        @RequestParam Long branchId);
}
