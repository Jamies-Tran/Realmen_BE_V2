package com.capstone.realmen.controller.api.admin.shop.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.capstone.realmen.common.response.ValueResponse;
import com.capstone.realmen.controller.api.admin.shop.service.models.AdminShopServiceResponse;

@RequestMapping("/web/shop-service/{shopServiceId}")
public interface IShopServicePathAPI {
    @GetMapping
    @PreAuthorize("hasAnyRole({'ROLE_SHOPOWNER','ROLE_BRANCHMANAGER', 'ROLE_RECEPTIONIST'})")
    ValueResponse<AdminShopServiceResponse> findById(@PathVariable Long shopServiceId);
}
