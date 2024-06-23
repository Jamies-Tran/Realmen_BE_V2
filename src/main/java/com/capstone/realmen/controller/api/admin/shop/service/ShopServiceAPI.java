package com.capstone.realmen.controller.api.admin.shop.service;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.capstone.realmen.controller.api.admin.shop.service.models.AdminShopServiceRequest;

import jakarta.validation.Valid;

@RequestMapping("/web/shop-service")
public interface ShopServiceAPI {
    @PostMapping
    void save(@RequestBody @Valid AdminShopServiceRequest serviceRequest);
}
