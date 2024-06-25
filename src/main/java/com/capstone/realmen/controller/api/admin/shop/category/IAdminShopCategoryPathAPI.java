package com.capstone.realmen.controller.api.admin.shop.category;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.capstone.realmen.common.response.ValueResponse;
import com.capstone.realmen.controller.api.admin.shop.category.models.AdminShopCategoryResponse;

@RequestMapping("/web/shop/category/{categoryId}")
public interface IAdminShopCategoryPathAPI {
    @GetMapping
    ValueResponse<AdminShopCategoryResponse> findById(@PathVariable Long categoryId);
}
