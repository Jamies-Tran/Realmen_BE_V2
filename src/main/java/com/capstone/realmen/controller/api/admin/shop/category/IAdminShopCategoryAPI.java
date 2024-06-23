package com.capstone.realmen.controller.api.admin.shop.category;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.capstone.realmen.common.response.PageImplResponse;
import com.capstone.realmen.controller.api.admin.shop.category.models.AdminShopCategoryRequest;

@RequestMapping("/web/shop/category")
public interface IAdminShopCategoryAPI {
    @PostMapping
    @PreAuthorize("hasAnyRole({'ROLE_SHOPOWNER','ROLE_BRANCHMANAGER'})")
    void save(@RequestBody AdminShopCategoryRequest shopCategoryRequest);
    
    
}
