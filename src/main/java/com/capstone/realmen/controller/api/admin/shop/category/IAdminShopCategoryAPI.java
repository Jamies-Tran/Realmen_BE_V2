package com.capstone.realmen.controller.api.admin.shop.category;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.capstone.realmen.common.response.PageImplResponse;
import com.capstone.realmen.controller.api.admin.shop.category.models.AdminShopCategoryRequest;
import com.capstone.realmen.controller.api.admin.shop.category.models.AdminShopCategoryResponse;

@RequestMapping("/web/shop/category")
public interface IAdminShopCategoryAPI {
    @PostMapping
    @PreAuthorize("hasAnyRole({'ROLE_SHOPOWNER','ROLE_BRANCHMANAGER'})")
    void save(@RequestBody AdminShopCategoryRequest shopCategoryRequest);

    @GetMapping
    @PreAuthorize("hasAnyRole({'ROLE_SHOPOWNER','ROLE_BRANCHMANAGER', 'ROLE_RECEPTIONIST'})")
    PageImplResponse<AdminShopCategoryResponse> findAll(
            @RequestParam(required = false, value = "search", defaultValue = "") String search,
            @RequestParam(required = false, value = "serviceNumber", defaultValue = "5") Integer serviceNumber,
            @RequestParam(required = false, value = "current", defaultValue = "1") Integer current,
            @RequestParam(required = false, value = "pageSize", defaultValue = "20") Integer pageSize);
}
